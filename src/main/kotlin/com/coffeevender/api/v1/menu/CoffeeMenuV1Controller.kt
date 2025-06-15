package com.coffeevender.api.v1.menu

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.menu.dto.response.CoffeeMenuResponseV1
import com.coffeevender.api.v1.menu.dto.response.FavoriteCoffeeMenuResponseV1

@RestController
@RequestMapping("/api/v1/menu/coffee")
@Tag(name = "Coffee Menu API", description = "커피 메뉴 API")
class CoffeeMenuV1Controller {
    
    @Operation(
        summary = "커피 메뉴 목록 조회",
        description = "커피 메뉴 목록을 조회하는 API",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CoffeeMenuResponseV1::class),
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "메뉴 목록을 불러왔습니다.",
                                    "body": [
                                        {
                                            "id": 1,
                                            "name": "아메리카노",
                                            "pricePoint": 5000
                                        }
                                    ]
                                }
                                """
                            ),
                            ExampleObject(
                                name = "Success-NoMenu",
                                summary = "성공 - 메뉴 없음",
                                value = """
                                { 
                                    "message": "메뉴 목록을 불러왔습니다.",
                                    "body": []
                                }
                                """
                            ),
                        ]
                    )
                ]
            ),
        ]
    )
    @GetMapping("")
    fun getCoffeeMenuList(): ResponseEntity<ApiResponseV1<Iterable<CoffeeMenuResponseV1>>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(
            ApiResponseV1(
                message = "메뉴 목록을 불러왔습니다.",
                body = listOf(
                    CoffeeMenuResponseV1(
                        id = 1,
                        name = "아메리카노",
                        pricePoint = 5000
                    )
                )
            )
        )
    }

    @Operation(
        summary = "커피 인기 메뉴 목록 조회",
        description = "커피 인기 메뉴 목록을 조회하는 API",
        responses = [
            ApiResponse(
                responseCode = "200", 
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = FavoriteCoffeeMenuResponseV1::class),
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "인기 메뉴 목록을 불러왔습니다.",
                                    "body": [
                                        {
                                            "menu": {
                                                "id": 1,
                                                "name": "아메리카노",
                                                "pricePoint": 5000
                                            },
                                            "soldCount": 15
                                        }
                                    ]
                                }
                                """
                            ),
                        ]
                    )
                ]
            ),
        ]
    )
    @GetMapping("/favorite")
    fun getFavoriteCoffeeMenuList(): ResponseEntity<ApiResponseV1<Iterable<FavoriteCoffeeMenuResponseV1>>> {
        // TODO: 구현 필요
        return ResponseEntity.ok(
            ApiResponseV1(
                message = "인기 메뉴 목록을 불러왔습니다.",
                body = listOf(
                    FavoriteCoffeeMenuResponseV1(
                        menu = CoffeeMenuResponseV1(
                            id = 1,
                            name = "아메리카노",
                            pricePoint = 5000
                        ),
                        soldCount = 15
                    )
                )
            )
        )
    }

    @Operation(
        summary = "커피 메뉴 주문",
        description = "커피 메뉴를 주문하는 API",
        responses = [
            ApiResponse(
                responseCode = "201", 
                description = "주문 완료",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "주문이 완료되었습니다.",
                                    "body": null
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
            ApiResponse(
                responseCode = "422", 
                description = "데이터 형식 오류",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "OrderMenuNotFound",
                                summary = "없는 메뉴 코드",
                                value = """
                                { 
                                    "message": "올바르지 않은 메뉴 코드입니다.",
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
    @PostMapping("/{id}/order")
    fun orderCoffee(
        @Schema(description = "커피 메뉴 ID", example = "1")
        @PathVariable id: Long,
        @RequestHeader("userId") userId: String
    ): ResponseEntity<ApiResponseV1<Any>> {
        // TODO: 구현 필요
        return ResponseEntity.status(201)
            .header("Location", "/order/1")
            .body(
                ApiResponseV1(
                    message = "주문이 완료되었습니다.",
                    body = null
                )
            )
    }
} 