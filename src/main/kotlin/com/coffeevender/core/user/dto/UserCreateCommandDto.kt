package com.coffeevender.core.user.dto

data class UserCreateCommandDto(
    val loginId: String,
    val password: String,
)