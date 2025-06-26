package com.coffeevender.api.v1

import com.coffeevender.core.coffeeMenu.service.CoffeeMenuCommandService
import com.coffeevender.core.coffeeMenu.service.CoffeeMenuQueryService
import com.coffeevender.core.order.service.OrderCommandService
import com.coffeevender.core.order.service.OrderQueryService
import com.coffeevender.core.point.service.PointCommandService
import com.coffeevender.core.point.service.PointQueryService
import com.coffeevender.core.user.service.UserCommandService
import com.coffeevender.core.user.service.UserQueryService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.mockito.Mockito.mock

@TestConfiguration
class TestConfig {

    @Bean
    @Primary
    fun mockUserCommandService(): UserCommandService {
        return mock(UserCommandService::class.java)
    }

    @Bean
    @Primary
    fun mockUserQueryService(): UserQueryService {
        return mock(UserQueryService::class.java)
    }

    @Bean
    @Primary
    fun mockCoffeeMenuCommandService(): CoffeeMenuCommandService {
        return mock(CoffeeMenuCommandService::class.java)
    }

    @Bean
    @Primary
    fun mockCoffeeMenuQueryService(): CoffeeMenuQueryService {
        return mock(CoffeeMenuQueryService::class.java)
    }

    @Bean
    @Primary
    fun mockOrderCommandService(): OrderCommandService {
        return mock(OrderCommandService::class.java)
    }

    @Bean
    @Primary
    fun mockOrderQueryService(): OrderQueryService {
        return mock(OrderQueryService::class.java)
    }

    @Bean
    @Primary
    fun mockPointCommandService(): PointCommandService {
        return mock(PointCommandService::class.java)
    }

    @Bean
    @Primary
    fun mockPointQueryService(): PointQueryService {
        return mock(PointQueryService::class.java)
    }
} 