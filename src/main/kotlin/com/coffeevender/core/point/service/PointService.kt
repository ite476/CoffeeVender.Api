package com.coffeevender.core.point.service

import com.coffeevender.core.common.ServiceResult
import com.coffeevender.core.point.dto.PointBalanceDto
import com.coffeevender.core.point.error.PointError

interface PointCommandService {
    fun chargePoint(userId: Long, won: Int)
    : ServiceResult<Unit, PointError>

    fun usePoint(userId: Long, point: Int)
    : ServiceResult<Unit, PointError>
}

interface PointQueryService {
    fun readPointBalance(userId: Long)
    : ServiceResult<PointBalanceDto, PointError>
}