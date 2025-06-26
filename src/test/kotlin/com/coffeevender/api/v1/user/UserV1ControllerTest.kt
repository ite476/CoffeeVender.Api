package com.coffeevender.api.v1.user

import com.coffeevender.api.v1.user.dto.request.UserJoinRequestV1
import com.coffeevender.api.v1.user.dto.response.UserJoinResponseV1
import com.coffeevender.api.v1.common.dto.response.ApiResponseV1
import com.coffeevender.api.v1.TestConfig
import com.coffeevender.core.user.service.UserCommandService
import com.coffeevender.core.user.service.UserQueryService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(CoffeeUserV1Controller::class)
@Import(TestConfig::class)
class UserV1ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var userCommandService: UserCommandService

    @MockBean
    private lateinit var userQueryService: UserQueryService

    @Test
    fun `회원 가입 성공 테스트`() {
        // given
        val request = UserJoinRequestV1(
            id = "testuser",
            password = "Test1234!"
        )

        // when & then
        mockMvc.perform(
            post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.message").value("회원 가입되었습니다."))
            .andExpect(jsonPath("$.body.id").value("testuser"))
    }

    @Test
    fun `회원 가입 - 다양한 입력값 테스트`() {
        // given
        val testCases = listOf(
            UserJoinRequestV1(id = "user123", password = "Test1234!"),
            UserJoinRequestV1(id = "coffee_lover", password = "Coffee123!"),
            UserJoinRequestV1(id = "test123", password = "Password123!")
        )

        testCases.forEach { request ->
            // when & then
            mockMvc.perform(
                post("/api/v1/user/join")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.message").value("회원 가입되었습니다."))
                .andExpect(jsonPath("$.body.id").value(request.id))
        }
    }

    @Test
    fun `회원 가입 - 요청 본문 검증 테스트`() {
        // given
        val request = UserJoinRequestV1(
            id = "validuser",
            password = "ValidPass123!"
        )

        // when & then
        mockMvc.perform(
            post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.body").exists())
            .andExpect(jsonPath("$.body.id").value(request.id))
    }
} 