package com.coffeevender.api.v1.order

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order API", description = "주문 API")
class CoffeeOrderV1Controller {

    @Operation(
        summary = "주문 목록 조회",
        description = "주문 목록을 조회하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
        ]
    )
    @GetMapping
    fun getOrderList(
        @RequestHeader("userId") userId: String
    ): ResponseEntity<Map<String, Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf(
            "message" to "주문 목록을 불러왔습니다.",
            "body" to listOf(
                mapOf(
                    "id" to 1,
                    "menu" to mapOf(
                        "id" to 1,
                        "name" to "아메리카노",
                        "pricePoint" to 5000
                    ),
                    "isPurchased" to false
                )
            )
        ))
    }

    @Operation(
        summary = "주문 상세 조회",
        description = "주문 상세 정보를 조회하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
            ApiResponse(responseCode = "404", description = "주문 없음"),
        ]
    )
    @GetMapping("/{id}")
    fun getOrderDetail(
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<Map<String, Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf(
            "message" to "주문 정보를 불러왔습니다.",
            "body" to mapOf(
                "id" to 1,
                "menu" to mapOf(
                    "id" to 1,
                    "name" to "아메리카노",
                    "pricePoint" to 5000
                ),
                "isPurchased" to false
            )
        ))
    }

    @Operation(
        summary = "주문 결제",
        description = "주문을 결제하는 API",
        responses = [
            ApiResponse(responseCode = "201", description = "주문 결제 완료"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패"),   
            ApiResponse(responseCode = "404", description = "주문 없음"),
            ApiResponse(responseCode = "409", description = "잔여 포인트 부족"),
        ]
    )
    @PostMapping("/{id}/purchase")
    fun purchaseOrder(
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<Map<String, String>> {
        // TODO: 구현 필요
        return ResponseEntity.ok()
            .header("Location", "/order/1")
            .body(mapOf("message" to "주문 결제가 완료되었습니다."))
    }
} 