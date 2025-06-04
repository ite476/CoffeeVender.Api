package com.coffeevender.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sample")
@Tag(name = "Sample API", description = "샘플 API 엔드포인트")
class SampleController {

    @Operation(
        summary = "샘플 가져오기",
        description = "샘플 데이터를 가져오는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "404", description = "데이터 없음")
        ]
    )
    @PostMapping("/{id}")
    fun postSample(
        @PathVariable id: Int,
        // 헤더의 경우 1나 1나 매칭해야 함 ㅅㄱ 
        @RequestHeader("X-Request-Id") requestId: String,
        @RequestHeader("X-Auth-Token") authToken: String,
        @ModelAttribute("query") query: SampleQueryDto,
        @RequestBody body: SampleBodyDto
    ): ResponseEntity<SampleResponseDto> {
        return ResponseEntity.ok(
            SampleResponseDto(
                message = "Hello", 
                id = id))
    }
}

// 쿼리 파라미터 DTO
@Schema(description = "쿼리 파라미터")
data class SampleQueryDto(
    @field:Schema(description = "필터", example = "filter")
    val filter: String?,

    @field:Schema(description = "페이지", example = "0")
    val page: Int = 0
)

// 바디 DTO
@Schema(description = "바디")
data class SampleBodyDto(
    @field:Schema(description = "이름", example = "홍길동")
    val name: String,

    @field:Schema(description = "나이", example = "20")
    val age: Int
)

// 응답 DTO
@Schema(description = "응답")
data class SampleResponseDto(
    
    @field:Schema(description = "메시지", example = "Hello")
    val message: String,

    @field:Schema(description = "ID", example = "1")
    val id: Int
)
