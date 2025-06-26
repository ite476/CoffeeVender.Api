package com.coffeevender.api.v1.menu

import com.coffeevender.api.v1.menu.dto.response.CoffeeMenuResponseV1
import com.coffeevender.api.v1.menu.dto.response.FavoriteCoffeeMenuResponseV1
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.TestConfig
import com.coffeevender.core.coffeeMenu.service.CoffeeMenuCommandService
import com.coffeevender.core.coffeeMenu.service.CoffeeMenuQueryService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(CoffeeMenuV1Controller::class)
@Import(TestConfig::class)
class CoffeeMenuV1ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var coffeeMenuCommandService: CoffeeMenuCommandService

    @MockBean
    private lateinit var coffeeMenuQueryService: CoffeeMenuQueryService

    @Test
    fun `커피 메뉴 목록 조회 성공 테스트`() {
        // when & then
        mockMvc.perform(
            get("/api/v1/menu/coffee")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("메뉴 목록을 불러왔습니다."))
            .andExpect(jsonPath("$.body").isArray)
            .andExpect(jsonPath("$.body[0].id").value(1))
            .andExpect(jsonPath("$.body[0].name").value("아메리카노"))
            .andExpect(jsonPath("$.body[0].pricePoint").value(5000))
    }

    @Test
    fun `커피 인기 메뉴 목록 조회 성공 테스트`() {
        // when & then
        mockMvc.perform(
            get("/api/v1/menu/coffee/favorite")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("인기 메뉴 목록을 불러왔습니다."))
            .andExpect(jsonPath("$.body").isArray)
            .andExpect(jsonPath("$.body[0].menu.id").value(1))
            .andExpect(jsonPath("$.body[0].menu.name").value("아메리카노"))
            .andExpect(jsonPath("$.body[0].menu.pricePoint").value(5000))
            .andExpect(jsonPath("$.body[0].soldCount").value(15))
    }

    @Test
    fun `커피 메뉴 주문 성공 테스트`() {
        // given
        val userId = "testuser"
        val menuId = 1L

        // when & then
        mockMvc.perform(
            post("/api/v1/menu/coffee/$menuId/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isCreated)
            .andExpect(header().string("Location", "/order/1"))
            .andExpect(jsonPath("$.message").value("주문이 완료되었습니다."))
            .andExpect(jsonPath("$.body").isEmpty)
    }

    @Test
    fun `커피 메뉴 주문 - 다양한 메뉴 ID 테스트`() {
        // given
        val userId = "testuser"
        val menuIds = listOf(1L, 2L, 3L)

        menuIds.forEach { menuId ->
            // when & then
            mockMvc.perform(
                post("/api/v1/menu/coffee/$menuId/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("userId", userId)
            )
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", "/order/1"))
                .andExpect(jsonPath("$.message").value("주문이 완료되었습니다."))
                .andExpect(jsonPath("$.body").isEmpty)
        }
    }

    @Test
    fun `커피 메뉴 주문 - 헤더 검증 테스트`() {
        // given
        val userId = "testuser"
        val menuId = 1L

        // when & then
        mockMvc.perform(
            post("/api/v1/menu/coffee/$menuId/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists("Location"))
            .andExpect(jsonPath("$.message").exists())
    }
} 