package com.coffeevender.core.point.error

import com.coffeevender.core.common.DomainError

sealed class PointError(
    override val message: String) : DomainError{

    data class NegativePointChargeAttempted(
        val userId: Long,
        val point: Int) 
    : PointError("사용자 코드 $userId 의 포인트 충전 시도 값이 $point 원 이므로 충전을 완료할 수 없습니다.")

    data class NegativePointUseAttempted(
        val userId: Long,
        val point: Int) 
    : PointError("사용자 코드 $userId 의 포인트 사용 시도 값이 $point 원 이므로 사용을 완료할 수 없습니다.")

    data class UserNotFound(
        val userId: Long) 
    : PointError("사용자 코드 $userId 에 해당하는 사용자가 없습니다.")
}