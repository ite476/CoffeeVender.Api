package com.coffeevender.api.v1.point

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject

@RestController
@RequestMapping("/api/v1/point")
@Tag(name = "Point API", description = "포인트 API")
class UserPointV1Controller {
    
    @Operation(
        summary = "포인트 충전",
        description = "포인트를 충전하는 API",
        responses = [
            ApiResponse(
                responseCode = "200", 
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "포인트 충전에 성공했습니다.",
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
                responseCode = "422", 
                description = "포인트 충전 금액 부족",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "InsufficientPoint",
                                summary = "포인트 충전 금액 부족",
                                value = """
                                { 
                                    "message": "포인트 충전 금액은 0 이상의 정수여야 합니다.",
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
            ApiResponse(
                responseCode = "200", 
                description = "성공",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "포인트 잔고를 불러왔습니다.",
                                    "body": {
                                        "availablePoint": 5000
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