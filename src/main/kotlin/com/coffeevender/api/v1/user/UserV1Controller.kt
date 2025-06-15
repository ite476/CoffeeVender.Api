package com.coffeevender.api.v1.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import com.coffeevender.api.v1.user.dto.request.UserV1JoinRequest
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "회원 API")
class CoffeeUserV1Controller {
    
    @Operation(
        summary = "회원 가입",
        description = "회원 가입하는 API",
        responses = [
            ApiResponse(responseCode = "201", description = "회원 가입 완료"),
            ApiResponse(
                responseCode = "409",
                description = "서버 상태 충돌",
                content = [Content(
                    mediaType = "application/json",
                    examples = [ExampleObject(
                        name = "AlreadyTakenUserId",
                        summary = "아이디 이미 존재함",
                        value = """
                        { 
                            "message": "아이디가 이미 존재합니다.",
                            "body": null
                        }
                        """
                    )]
                )]
            ),
            ApiResponse(
                responseCode = "422",
                description = "데이터 형식 오류",
                content = [Content(
                    mediaType = "application/json",
                    examples = [
                        ExampleObject(
                            name = "InvalidUserIdFormat-Length",
                            summary = "아이디 형식 오류 - 길이",
                            value = """
                            { 
                                "message": "아이디는 4자 이상 12자 이하여야 합니다.",
                                "body": null
                            }
                            """
                        ),
                        ExampleObject(
                            name = "InvalidUserIdFormat-SpecialCharacter",
                            summary = "아이디 형식 오류 - 특수문자",
                            value = """
                            { 
                                "message": "아이디는 영문 대소문자 및 숫자로만 구성되어야 합니다.",
                                "body": null
                            }
                            """
                        ),
                        ExampleObject(
                            name = "InvalidPasswordFormat-Length",
                            summary = "비밀번호 형식 오류 - 길이",
                            value = """
                            { 
                                "message": "비밀번호는 8자 이상 16자 이하여야 합니다.",
                                "body": null
                            }
                            """
                        ),
                        ExampleObject(
                            name = "InvalidPasswordFormat-SpecialCharacter",
                            summary = "비밀번호 형식 오류 - 특수문자 포함",
                            value = """
                            { 
                                "message": "비밀번호는 영문 대소문자, 숫자, 특수문자가 각각 한 개 이상씩 포함되어야 합니다.",
                                "body": null
                            }
                            """
                        ),
                    ]
                )]
            ),
        ]
    )
    @PostMapping("/join")
    fun join(@RequestBody request: UserV1JoinRequest): ResponseEntity<ApiResponseV1<Any>> {
        // TODO: 구현 필요
        return ResponseEntity
            .status(201)
            .body(
                ApiResponseV1(message = "회원 가입되었습니다."))
    }
}

