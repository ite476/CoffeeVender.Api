package com.coffeevender.core.user.service

import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.user.dto.UserCreateCommandDto
import com.coffeevender.core.user.dto.UserDto
import com.coffeevender.core.user.error.UserError

interface UserCommandService {
    fun createUser(command: UserCreateCommandDto)
    : ServiceResult<Long, UserError> // userId
}

interface UserQueryService {
    fun findUserById(userId: Long)
    : ServiceResult<UserDto, UserError>
}