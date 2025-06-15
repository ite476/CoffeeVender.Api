package com.coffeevender.api.v1.menu.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "인기 커피 메뉴 응답")
data class FavoriteCoffeeMenuResponseV1(
    @Schema(description = "커피 메뉴")
    val menu: CoffeeMenuResponseV1,
    @Schema(description = "판매 수량", example = "15")
    val soldCount: Int
)