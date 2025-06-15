package com.coffeevender.api.v1.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 가입 요청")
data class UserJoinRequestV1(
    @Schema(description = "아이디", example = "test")
    val id: String,
    @Schema(description = "비밀번호", example = "Test1234!")
    val password: String
) 