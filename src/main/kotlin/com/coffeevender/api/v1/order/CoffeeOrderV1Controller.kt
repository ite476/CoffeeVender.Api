package com.coffeevender.api.v1.order

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.order.dto.response.CoffeeMenuOrderResponseV1
import com.coffeevender.api.v1.order.dto.response.CoffeeMenuResponseV1

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order API", description = "주문 API")
class CoffeeOrderV1Controller {

    @Operation(
        summary = "주문 목록 조회",
        description = "주문 목록을 조회하는 API",
        responses = [
            ApiResponse(
                responseCode = "200", 
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CoffeeMenuOrderResponseV1::class),
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "주문 목록을 불러왔습니다.",
                                    "body": [
                                        {
                                            "id": 1,
                                            "menu": {
                                                "id": 1,
                                                "name": "아메리카노",
                                                "pricePoint": 5000
                                            },
                                            "isPurchased": false
                                        }
                                    ]
                                }
                                """
                            ),
                            ExampleObject(
                                name = "Success-NoOrder",
                                summary = "성공 - 주문 없음",
                                value = """
                                { 
                                    "message": "주문 목록을 불러왔습니다.",
                                    "body": []
                                }
                                """
                            ),
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "401", 
                description = "인증 실패",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "AuthenticationFailed",
                                summary = "인증 실패",
                                value = """
                                { 
                                    "message": "회원 인증에 실패했습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
        ]
    )
    @GetMapping
    fun getOrderList(
        @RequestHeader("userId") userId: String
    ): ResponseEntity<ApiResponseV1<Iterable<CoffeeMenuOrderResponseV1>>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(
            ApiResponseV1(
                message = "주문 목록을 불러왔습니다.",
                body = listOf(
                    CoffeeMenuOrderResponseV1(
                        id = 1,
                        menu = CoffeeMenuResponseV1(
                            id = 1,
                            name = "아메리카노",
                            pricePoint = 5000
                        ),
                        isPurchased = false
                    )
                )
            )
        )
    }

    @Operation(
        summary = "주문 상세 조회",
        description = "주문 상세 정보를 조회하는 API",
        responses = [
            ApiResponse(
                responseCode = "200", 
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CoffeeMenuOrderResponseV1::class),
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "주문 정보를 불러왔습니다.",
                                    "body": {
                                        "id": 1,
                                        "menu": {
                                            "id": 1,
                                            "name": "아메리카노",
                                            "pricePoint": 5000
                                        },
                                        "isPurchased": false
                                    }
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "401", 
                description = "인증 실패",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "AuthenticationFailed",
                                summary = "인증 실패",
                                value = """
                                { 
                                    "message": "회원 인증에 실패했습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404", 
                description = "주문 없음",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "OrderNotFound",
                                summary = "주문 없음",
                                value = """
                                { 
                                    "message": "주문 정보를 불러오지 못했습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getOrderDetail(
        @Schema(description = "주문 ID", example = "1")
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<ApiResponseV1<CoffeeMenuOrderResponseV1>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(
            ApiResponseV1(
                message = "주문 정보를 불러왔습니다.",
                body = CoffeeMenuOrderResponseV1(
                    id = 1,
                    menu = CoffeeMenuResponseV1(
                        id = 1,
                        name = "아메리카노",
                        pricePoint = 5000
                    ),
                    isPurchased = false
                )
            )
        )
    }

    @Operation(
        summary = "주문 결제",
        description = "주문을 결제하는 API",
        responses = [
            ApiResponse(
                responseCode = "201", 
                description = "주문 결제 완료",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "주문 결제가 완료되었습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "401", 
                description = "인증 실패",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "AuthenticationFailed",
                                summary = "인증 실패",
                                value = """
                                { 
                                    "message": "회원 인증에 실패했습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404", 
                description = "주문 없음",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "OrderNotFound",
                                summary = "주문 없음",
                                value = """
                                { 
                                    "message": "주문 정보를 불러오지 못했습니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "409", 
                description = "잔여 포인트 부족",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "InsufficientPoint",
                                summary = "잔여 포인트 부족",
                                value = """
                                { 
                                    "message": "잔여 포인트가 부족합니다.",
                                    "body": null
                                }
                                """
                            )
                        ]
                    )
                ]
            )
        ]
    )
    @PostMapping("/{id}/purchase")
    fun purchaseOrder(
        @Schema(description = "주문 ID", example = "1")
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<ApiResponseV1<Any>> {
        // TODO: 구현 필요
        return ResponseEntity.ok()
            .header("Location", "/order/1")
            .body(
                ApiResponseV1(message = "주문 결제가 완료되었습니다.")
            )
    }
} 