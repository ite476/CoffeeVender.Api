package com.coffeevender.api.v1.order

import com.coffeevender.api.v1.order.dto.response.CoffeeMenuOrderResponseV1
import com.coffeevender.api.v1.order.dto.response.CoffeeMenuResponseV1
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.TestConfig
import com.coffeevender.core.order.service.OrderCommandService
import com.coffeevender.core.order.service.OrderQueryService
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

@WebMvcTest(CoffeeOrderV1Controller::class)
@Import(TestConfig::class)
class CoffeeOrderV1ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var orderCommandService: OrderCommandService

    @MockBean
    private lateinit var orderQueryService: OrderQueryService

    @Test
    fun `주문 목록 조회 성공 테스트`() {
        // given
        val userId = "testuser"

        // when & then
        mockMvc.perform(
            get("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("주문 목록을 불러왔습니다."))
            .andExpect(jsonPath("$.body").isArray)
            .andExpect(jsonPath("$.body[0].id").value(1))
            .andExpect(jsonPath("$.body[0].menu.id").value(1))
            .andExpect(jsonPath("$.body[0].menu.name").value("아메리카노"))
            .andExpect(jsonPath("$.body[0].menu.pricePoint").value(5000))
            .andExpect(jsonPath("$.body[0].isPurchased").value(false))
    }

    @Test
    fun `주문 상세 조회 성공 테스트`() {
        // given
        val userId = "testuser"
        val orderId = 1L

        // when & then
        mockMvc.perform(
            get("/api/v1/order/$orderId")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("주문 정보를 불러왔습니다."))
            .andExpect(jsonPath("$.body.id").value(1))
            .andExpect(jsonPath("$.body.menu.id").value(1))
            .andExpect(jsonPath("$.body.menu.name").value("아메리카노"))
            .andExpect(jsonPath("$.body.menu.pricePoint").value(5000))
            .andExpect(jsonPath("$.body.isPurchased").value(false))
    }

    @Test
    fun `주문 상세 조회 - 다양한 주문 ID 테스트`() {
        // given
        val userId = "testuser"
        val orderIds = listOf(1L, 2L, 3L, 5L, 10L)

        orderIds.forEach { orderId ->
            // when & then
            mockMvc.perform(
                get("/api/v1/order/$orderId")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("userId", userId)
            )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.message").value("주문 정보를 불러왔습니다."))
                .andExpect(jsonPath("$.body.id").value(1))
                .andExpect(jsonPath("$.body.menu.id").value(1))
                .andExpect(jsonPath("$.body.menu.name").value("아메리카노"))
                .andExpect(jsonPath("$.body.menu.pricePoint").value(5000))
                .andExpect(jsonPath("$.body.isPurchased").value(false))
        }
    }

    @Test
    fun `주문 결제 성공 테스트`() {
        // given
        val userId = "testuser"
        val orderId = 1L

        // when & then
        mockMvc.perform(
            post("/api/v1/order/$orderId/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(header().string("Location", "/order/1"))
            .andExpect(jsonPath("$.message").value("주문 결제가 완료되었습니다."))
            .andExpect(jsonPath("$.body").isEmpty)
    }

    @Test
    fun `주문 결제 - 다양한 주문 ID 테스트`() {
        // given
        val userId = "testuser"
        val orderIds = listOf(1L, 2L, 3L, 5L, 10L)

        orderIds.forEach { orderId ->
            // when & then
            mockMvc.perform(
                post("/api/v1/order/$orderId/purchase")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("userId", userId)
            )
                .andExpect(status().isOk)
                .andExpect(header().string("Location", "/order/1"))
                .andExpect(jsonPath("$.message").value("주문 결제가 완료되었습니다."))
                .andExpect(jsonPath("$.body").isEmpty)
        }
    }

    @Test
    fun `주문 결제 - 응답 구조 검증 테스트`() {
        // given
        val userId = "testuser"
        val orderId = 1L

        // when & then
        mockMvc.perform(
            post("/api/v1/order/$orderId/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists("Location"))
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.body").exists())
    }
} 