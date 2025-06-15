package com.coffeevender.api.v1.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "회원 API")
class CoffeeUserV1Controller {
    
    @Operation(
        summary = "회원 가입",
        description = "회원 가입하는 API",
        responses = [
            ApiResponse(responseCode = "201", description = "회원 가입 완료"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "409", description = "이미 존재하는 아이디"),
            ApiResponse(responseCode = "422", description = "아이디 형식 오류"),
            ApiResponse(responseCode = "422", description = "비밀번호 형식 오류"),
        ]
    )
    @PostMapping("/join")
    fun join(@RequestBody request: JoinRequest): ResponseEntity<Map<String, String>> {
        // TODO: 구현 필요
        return ResponseEntity.status(201).body(mapOf("message" to "회원 가입되었습니다."))
    }
}

data class JoinRequest(
    val id: String,
    val password: String
) 