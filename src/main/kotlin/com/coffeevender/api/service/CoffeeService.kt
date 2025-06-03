package com.example.coffevenderapi.service

import com.example.coffevenderapi.repository.CoffeeRepository
import org.springframework.stereotype.Service

@Service
class CoffeeService(val coffeeRepository: CoffeeRepository) {

    fun getCoffeeCount(): Int {
        return coffeeRepository.countCoffees()
    }
}
