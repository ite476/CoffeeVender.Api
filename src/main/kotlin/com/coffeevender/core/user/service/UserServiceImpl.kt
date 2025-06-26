package com.coffeevender.core.user.service

import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.user.dto.UserCreateCommandDto
import com.coffeevender.core.user.dto.UserDto
import com.coffeevender.core.user.entity.User
import com.coffeevender.core.user.error.UserError
import com.coffeevender.core.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.regex.Pattern

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserCommandService, UserQueryService {

    companion object {
        private val LOGIN_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,20}$")
        private val PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$")
    }

    @Transactional
    override fun createUser(command: UserCreateCommandDto): ServiceResult<Long, UserError> {
        // 입력값 검증
        val validationResult = validateUserInput(command)
        if (validationResult is ServiceResult.Failure) {
            return validationResult
        }

        // 중복 사용자 확인
        if (userRepository.existsByLoginId(command.loginId)) {
            return ServiceResult.Failure(UserError.UserAlreadyExists(command.loginId))
        }

        // 비밀번호 암호화
        val encodedPassword = passwordEncoder.encode(command.password)

        // 사용자 생성
        val user = User(
            loginId = command.loginId,
            password = encodedPassword
        )

        val savedUser = userRepository.save(user)
        return ServiceResult.Success(savedUser.id)
    }

    override fun findUserById(userId: Long): ServiceResult<UserDto, UserError> {
        val user = userRepository.findById(userId).orElse(null)
            ?: return ServiceResult.Failure(UserError.UserNotFound(userId))

        return ServiceResult.Success(
            UserDto(
                id = user.id,
                loginId = user.loginId,
                createdAt = user.createdAt
            )
        )
    }

    private fun validateUserInput(command: UserCreateCommandDto): ServiceResult<Long, UserError> {
        // 로그인 ID 검증
        if (!LOGIN_ID_PATTERN.matcher(command.loginId).matches()) {
            return ServiceResult.Failure(UserError.UserLoginIdNotValid(command.loginId))
        }

        // 비밀번호 검증
        if (!PASSWORD_PATTERN.matcher(command.password).matches()) {
            return ServiceResult.Failure(UserError.UserPasswordNotValid(command.password))
        }

        return ServiceResult.Success(0L) // 임시 값, 실제로는 사용되지 않음
    }
} 