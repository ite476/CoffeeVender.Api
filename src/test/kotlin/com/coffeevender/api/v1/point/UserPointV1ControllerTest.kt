package com.coffeevender.api.v1.point

import com.coffeevender.api.v1.point.dto.request.ChargePointRequestV1
import com.coffeevender.api.v1.point.dto.response.PointBalanceResponseV1
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.TestConfig
import com.coffeevender.core.point.service.PointCommandService
import com.coffeevender.core.point.service.PointQueryService
import com.fasterxml.jackson.databind.ObjectMapper
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

@WebMvcTest(UserPointV1Controller::class)
@Import(TestConfig::class)
class UserPointV1ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var pointCommandService: PointCommandService

    @MockBean
    private lateinit var pointQueryService: PointQueryService

    @Test
    fun `포인트 충전 성공 테스트`() {
        // given
        val userId = "testuser"
        val request = ChargePointRequestV1(chargeAmountWon = 10000)

        // when & then
        mockMvc.perform(
            post("/api/v1/point/charge")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("포인트 충전에 성공했습니다."))
            .andExpect(jsonPath("$.body").isEmpty)
    }

    @Test
    fun `포인트 충전 - 다양한 금액 테스트`() {
        // given
        val userId = "testuser"
        val amounts = listOf(1000, 5000, 10000, 50000)

        amounts.forEach { amount ->
            val request = ChargePointRequestV1(chargeAmountWon = amount)
            
            // when & then
            mockMvc.perform(
                post("/api/v1/point/charge")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("userId", userId)
                    .content(objectMapper.writeValueAsString(request))
            )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.message").value("포인트 충전에 성공했습니다."))
                .andExpect(jsonPath("$.body").isEmpty)
        }
    }

    @Test
    fun `포인트 잔액 조회 성공 테스트`() {
        // given
        val userId = "testuser"

        // when & then
        mockMvc.perform(
            get("/api/v1/point")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.message").value("포인트 잔고를 불러왔습니다."))
            .andExpect(jsonPath("$.body.availablePoint").value(5000))
    }

    @Test
    fun `포인트 잔액 조회 - 응답 구조 검증 테스트`() {
        // given
        val userId = "testuser"

        // when & then
        mockMvc.perform(
            get("/api/v1/point")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", userId)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.body").exists())
            .andExpect(jsonPath("$.body.availablePoint").exists())
            .andExpect(jsonPath("$.body.availablePoint").isNumber)
    }
} 