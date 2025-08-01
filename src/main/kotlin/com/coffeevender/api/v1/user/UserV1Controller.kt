package com.coffeevender.api.v1.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.user.dto.request.UserJoinRequestV1
import com.coffeevender.api.v1.user.dto.response.UserJoinResponseV1
import com.coffeevender.core.user.service.UserCommandService
import com.coffeevender.core.user.dto.UserCreateCommandDto
import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.user.error.UserError
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "회원 API")
class UserV1Controller(
    private val userCommandService: UserCommandService
) {
    @Operation(
        summary = "회원 가입",
        description = "회원 가입하는 API",
        responses = [
            ApiResponse(
                responseCode = "201", 
                description = "회원 가입 완료",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = UserJoinResponseV1::class),
                        examples = [
                            ExampleObject(
                                name = "Success",
                                summary = "성공",
                                value = """
                                { 
                                    "message": "회원 가입되었습니다.",
                                    "body": {
                                        "id": "test"
                                    }
                                }
                                """
                            )
                        ]
                    )
                ]
            ),
            ApiResponse(
                responseCode = "409",
                description = "서버 상태 충돌",
                content = [
                    Content(
                        mediaType = "application/json",
                        examples = [
                            ExampleObject(
                                name = "AlreadyTakenUserId",
                                summary = "아이디 이미 존재함",
                                value = """
                                { 
                                    "message": "아이디가 이미 존재합니다.",
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
                    )
                ]
            ),
        ]
    )
    @PostMapping("/join")
    fun join(@RequestBody request: UserJoinRequestV1): ResponseEntity<ApiResponseV1<UserJoinResponseV1>> {
        val command = UserCreateCommandDto(
            loginId = request.id,
            password = request.password
        )
        return when(val result = userCommandService.createUser(command)) {
            is ServiceResult.Success -> {
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponseV1(
                        message = "회원 가입되었습니다.",
                        body = UserJoinResponseV1(id = request.id)
                    ))
            }
            is ServiceResult.Failure -> {
                when(result.error) {
                    is UserError.UserAlreadyExists -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(ApiResponseV1(message = "아이디가 이미 존재합니다.", body = null))
                    is UserError.UserLoginIdNotValid -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(ApiResponseV1(message = "아이디는 4자 이상 12자 이하여야 하며, 영문 대소문자 및 숫자로만 구성되어야 합니다.", body = null))
                    is UserError.UserPasswordNotValid -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(ApiResponseV1(message = "비밀번호는 8자 이상 16자 이하여야 하며, 영문 대소문자, 숫자, 특수문자가 각각 한 개 이상씩 포함되어야 합니다.", body = null))
                    else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ApiResponseV1(message = "알 수 없는 오류가 발생했습니다.", body = null))
                }
            }
        }
    }
}

