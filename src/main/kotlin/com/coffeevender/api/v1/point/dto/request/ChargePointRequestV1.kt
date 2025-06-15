package com.coffeevender.api.v1.point.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class ChargePointRequestV1(
    @Schema(description = "충전 금액(원)", example = "10000")
    val chargeAmountWon: Int
) 