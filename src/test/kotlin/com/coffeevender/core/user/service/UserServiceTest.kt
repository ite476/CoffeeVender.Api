package com.coffeevender.core.user.service

import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.user.dto.UserCreateCommandDto
import com.coffeevender.core.user.dto.UserDto
import com.coffeevender.core.user.error.UserError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.whenever
import org.mockito.Mockito.verify
import org.assertj.core.api.Assertions.assertThat
import java.time.OffsetDateTime

@DisplayName("UserService 테스트")
class UserServiceTest {

    @Mock
    private lateinit var userCommandService: UserCommandService

    @Mock
    private lateinit var userQueryService: UserQueryService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    fun `회원가입이 성공적으로 완료된다`() {
        // given
        val command = UserCreateCommandDto(
            loginId = "testuser",
            password = "password123"
        )
        val expectedUserId = 1L

        whenever(userCommandService.createUser(command))
            .thenReturn(ServiceResult.Success(expectedUserId))

        // when
        val result = userCommandService.createUser(command)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Success::class.java)
        assertThat((result as ServiceResult.Success).data).isEqualTo(expectedUserId)
        verify(userCommandService).createUser(command)
    }

    @Test
    @DisplayName("중복된 로그인 ID로 회원가입 시도 시 실패 테스트")
    fun `중복된 로그인 ID로 회원가입 시도 시 실패한다`() {
        // given
        val command = UserCreateCommandDto(
            loginId = "existinguser",
            password = "password123"
        )
        val expectedError = UserError.UserAlreadyExists("existinguser")

        whenever(userCommandService.createUser(command))
            .thenReturn(ServiceResult.Failure(expectedError))

        // when
        val result = userCommandService.createUser(command)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Failure::class.java)
        assertThat((result as ServiceResult.Failure).error).isEqualTo(expectedError)
        verify(userCommandService).createUser(command)
    }

    @Test
    @DisplayName("잘못된 로그인 ID 형식으로 회원가입 시도 시 실패 테스트")
    fun `잘못된 로그인 ID 형식으로 회원가입 시도 시 실패한다`() {
        // given
        val command = UserCreateCommandDto(
            loginId = "invalid@user",
            password = "password123"
        )
        val expectedError = UserError.UserLoginIdNotValid("invalid@user")

        whenever(userCommandService.createUser(command))
            .thenReturn(ServiceResult.Failure(expectedError))

        // when
        val result = userCommandService.createUser(command)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Failure::class.java)
        assertThat((result as ServiceResult.Failure).error).isEqualTo(expectedError)
        verify(userCommandService).createUser(command)
    }

    @Test
    @DisplayName("잘못된 비밀번호 형식으로 회원가입 시도 시 실패 테스트")
    fun `잘못된 비밀번호 형식으로 회원가입 시도 시 실패한다`() {
        // given
        val command = UserCreateCommandDto(
            loginId = "testuser",
            password = "123"
        )
        val expectedError = UserError.UserPasswordNotValid("123")

        whenever(userCommandService.createUser(command))
            .thenReturn(ServiceResult.Failure(expectedError))

        // when
        val result = userCommandService.createUser(command)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Failure::class.java)
        assertThat((result as ServiceResult.Failure).error).isEqualTo(expectedError)
        verify(userCommandService).createUser(command)
    }

    @Test
    @DisplayName("사용자 조회 성공 테스트")
    fun `사용자 조회가 성공적으로 완료된다`() {
        // given
        val userId = 1L
        val expectedUser = UserDto(
            id = userId,
            loginId = "testuser",
            createdAt = OffsetDateTime.now()
        )

        whenever(userQueryService.findUserById(userId))
            .thenReturn(ServiceResult.Success(expectedUser))

        // when
        val result = userQueryService.findUserById(userId)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Success::class.java)
        assertThat((result as ServiceResult.Success).data).isEqualTo(expectedUser)
        verify(userQueryService).findUserById(userId)
    }

    @Test
    @DisplayName("존재하지 않는 사용자 조회 시 실패 테스트")
    fun `존재하지 않는 사용자 조회 시 실패한다`() {
        // given
        val userId = 999L
        val expectedError = UserError.UserNotFound(userId)

        whenever(userQueryService.findUserById(userId))
            .thenReturn(ServiceResult.Failure(expectedError))

        // when
        val result = userQueryService.findUserById(userId)

        // then
        assertThat(result).isInstanceOf(ServiceResult.Failure::class.java)
        assertThat((result as ServiceResult.Failure).error).isEqualTo(expectedError)
        verify(userQueryService).findUserById(userId)
    }
} 