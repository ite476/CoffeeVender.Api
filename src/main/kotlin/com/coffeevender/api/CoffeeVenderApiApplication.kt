package com.coffeevender.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeVenderApiApplication

fun main(args: Array<String>) {
	runApplication<CoffeeVenderApiApplication>(*args)
}
