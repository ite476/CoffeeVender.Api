package com.coffeevender.core.common

sealed class ServiceResult<out T, out E> {
    data class Success<out T>(val data: T) : ServiceResult<T, Nothing>()
    data class Failure<out E>(val error: E) : ServiceResult<Nothing, E>()
}