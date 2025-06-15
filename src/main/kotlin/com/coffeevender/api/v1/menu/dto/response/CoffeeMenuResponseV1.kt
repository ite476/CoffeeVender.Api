package com.coffeevender.api.v1.menu.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "커피 메뉴 응답")
data class CoffeeMenuResponseV1(
    @Schema(description = "커피 메뉴 ID", example = "1")
    val id: Int,
    @Schema(description = "커피 메뉴 이름", example = "아메리카노")
    val name: String,
    @Schema(description = "커피 메뉴 가격(포인트)", example = "5000")
    val pricePoint: Int
)