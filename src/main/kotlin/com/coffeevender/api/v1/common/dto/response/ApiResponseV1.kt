package com.coffeevender.api.v1.common.dto.response

data class ApiResponseV1<T>(
    val message: String = "요청 성공",
    val body: T? = null
)
