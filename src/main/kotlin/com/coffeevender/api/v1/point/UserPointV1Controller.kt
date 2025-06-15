package com.coffeevender.api.v1.point

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@RestController
@RequestMapping("/api/v1/point")
@Tag(name = "Point API", description = "포인트 API")
class UserPointV1Controller {
    
    @Operation(
        summary = "포인트 충전",
        description = "포인트를 충전하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
            ApiResponse(responseCode = "422", description = "포인트 충전 금액 부족"),
        ]
    )
    @PostMapping("/charge")
    fun chargePoint(
        @RequestHeader("userId") userId: String,
        @RequestBody request: ChargePointRequest
    ): ResponseEntity<Map<String, String>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf("message" to "포인트 충전에 성공했습니다."))
    }

    @Operation(
        summary = "포인트 잔액 조회",
        description = "포인트 잔액을 조회하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
        ]
    )
    @GetMapping
    fun getPointBalance(
        @RequestHeader("userId") userId: String
    ): ResponseEntity<Map<String, Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf(
            "message" to "포인트 잔고를 불러왔습니다.",
            "body" to mapOf("availablePoint" to 5000)
        ))
    }
}

data class ChargePointRequest(
    val chargeAmountWon: Int
) 