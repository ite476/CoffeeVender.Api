package com.coffeevender.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
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
        @RequestParam query: SampleQueryDto,
        @RequestHeader header: SampleHeaderDto,
        @RequestBody body: SampleBodyDto
    ): ResponseEntity<SampleResponseDto> {
        return ResponseEntity.ok(SampleResponseDto("Hello", id))
    }
}

// 쿼리 파라미터 DTO
data class SampleQueryDto(
    val filter: String?,
    val page: Int = 0
)

// 헤더 DTO
data class SampleHeaderDto(
    @RequestHeader("X-Request-Id") val requestId: String,
    @RequestHeader("X-Auth-Token") val authToken: String
)

// 바디 DTO
data class SampleBodyDto(
    val name: String,
    val age: Int
)

// 응답 DTO
data class SampleResponseDto(
    val message: String,
    val id: Int
)
