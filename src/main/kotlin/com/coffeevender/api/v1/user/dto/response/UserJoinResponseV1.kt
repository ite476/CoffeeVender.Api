package com.coffeevender.api.v1.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 가입 응답")
data class UserJoinResponseV1(
    @Schema(description = "회원 ID", example = "test")
    val id: String
) 