package com.coffeevender.api.v1.menu

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@RestController
@RequestMapping("/api/v1/menu/coffee")
@Tag(name = "Coffee Menu API", description = "커피 메뉴 API")
class CoffeeMenuV1Controller {
    
    @Operation(
        summary = "커피 메뉴 목록 조회",
        description = "커피 메뉴 목록을 조회하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "404", description = "데이터 없음")
        ]
    )
    @GetMapping("")
    fun getCoffeeMenuList(): ResponseEntity<Map<String, Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf(
            "message" to "메뉴 목록을 불러왔습니다.",
            "body" to listOf(
                mapOf(
                    "id" to 1,
                    "name" to "아메리카노",
                    "pricePoint" to 5000
                )
            )
        ))
    }

    @Operation(
        summary = "커피 인기 메뉴 목록 조회",
        description = "커피 인기 메뉴 목록을 조회하는 API",
        responses = [
            ApiResponse(responseCode = "200", description = "성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "404", description = "데이터 없음")
        ]
    )
    @GetMapping("/favorite")
    fun getFavoriteCoffeeMenuList(): ResponseEntity<Map<String, Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(mapOf(
            "message" to "인기 메뉴 목록을 불러왔습니다.",
            "body" to listOf(
                mapOf(
                    "menu" to mapOf(
                        "id" to 1,
                        "name" to "아메리카노",
                        "pricePoint" to 5000
                    ),
                    "soldCount" to 15
                )
            )
        ))
    }

    @Operation(
        summary = "커피 메뉴 주문",
        description = "커피 메뉴를 주문하는 API",
        responses = [
            ApiResponse(responseCode = "201", description = "주문 완료"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "422", description = "없는 주문 메뉴"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
        ]
    )
    @PostMapping("/{id}/order")
    fun orderCoffee(
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<Map<String, String>> {
        // TODO: 구현 필요
        return ResponseEntity.status(201)
            .header("Location", "/order/1")
            .body(mapOf("message" to "주문이 완료되었습니다."))
    }
} 