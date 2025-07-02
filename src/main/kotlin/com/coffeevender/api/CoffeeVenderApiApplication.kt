package com.coffeevender.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.coffeevender"])
@EntityScan(basePackages = ["com.coffeevender.core"])
class CoffeeVenderApiApplication

fun main(args: Array<String>) {
	runApplication<CoffeeVenderApiApplication>(*args)
}
