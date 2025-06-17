package com.coffeevender.core.order.dto

import java.time.OffsetDateTime

data class OrderDto(
    val id: Long,
    val userId: Long,
    val menuId: Long,
    val status: OrderStatus,
    val createdAt: OffsetDateTime
)

enum class OrderStatus {
    PENDING,
    COMPLETED
}