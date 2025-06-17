package com.coffeevender.core.user.dto

import java.time.OffsetDateTime

data class UserDto(
    val id: Long,
    val loginId: String,
    val createdAt: OffsetDateTime
)