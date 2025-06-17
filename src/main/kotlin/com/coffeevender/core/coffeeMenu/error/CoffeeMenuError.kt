package com.coffeevender.core.coffeeMenu.error

import com.coffeevender.core.common.DomainError

sealed class CoffeeMenuError(override val message: String) : DomainError{

    data class CoffeeMenuNotFound(val menuId: Long) 
    : CoffeeMenuError("메뉴 코드 $menuId 에 해당하는 메뉴가 없습니다.")

    data class PricePointCannotBeNegative(val pricePoint: Int) 
    : CoffeeMenuError("메뉴 가격 포인트 값이 $pricePoint 원 이므로 메뉴를 생성할 수 없습니다.")
}