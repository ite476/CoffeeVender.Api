package com.coffeevender.api.v1.point.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class PointBalanceResponseV1(
    @Schema(description = "포인트 잔액") 
    val availablePoint: Int
) 