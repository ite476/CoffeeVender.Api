package com.coffeevender.core.order.error

import com.coffeevender.core.common.DomainError

sealed class OrderError(
    override val message: String) : DomainError{

    data class OrderNotFound(
        val orderId: Long) 
    : OrderError("주문 코드 $orderId 에 해당하는 주문이 없습니다.")

    data class NotPersonalOrder(
        val userId: Long, 
        val orderId: Long) 
    : OrderError("주문 코드 $orderId 에 해당하는 주문은 사용자 $userId 의 주문이 아닙니다.")

    data class UserNotFound(
        val userId: Long) 
    : OrderError("사용자 코드 $userId 에 해당하는 사용자가 없습니다.")

    data class MenuNotFound(
        val menuId: Long) 
    : OrderError("메뉴 코드 $menuId 에 해당하는 메뉴가 없습니다.")

    data class NotEnoughPoint(
        val userId: Long,
        val point: Int) 
    : OrderError("사용자 코드 $userId 의 포인트 잔액이 $point 원 이므로 주문을 완료할 수 없습니다.")

    data class OrderAlreadyPurchased(
        val orderId: Long) 
    : OrderError("주문 코드 $orderId 에 해당하는 주문은 이미 구매되었습니다.")
}