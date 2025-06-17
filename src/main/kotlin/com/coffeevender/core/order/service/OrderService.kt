package com.coffeevender.core.order.service

import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.order.dto.OrderDto
import com.coffeevender.core.order.error.OrderError

// Command 인터페이스
interface OrderCommandService {
    fun createOrder(userId: Long, menuId: Long)
    : ServiceResult<Long, OrderError> // orderId

    fun purchaseOrder(userId: Long, orderId: Long)
    : ServiceResult<Long, OrderError> // orderId
}

// Query 인터페이스
interface OrderQueryService {
    fun findOrderById(orderId: Long)
    : ServiceResult<OrderDto, OrderError>

    fun findOrdersByUserId(userId: Long)
    : ServiceResult<List<OrderDto>, OrderError>

    fun isPersonalOrder(userId: Long, orderId: Long)
    : ServiceResult<Boolean, OrderError>
}