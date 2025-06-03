package com.example.coffevenderapi.repository

import org.springframework.stereotype.Repository

@Repository
class CoffeeRepository {

    fun countCoffees(): Int {
        // 실제로는 JPA나 DB 호출하는 부분
        // 예시로 하드코딩 반환
        return 42
    }
}
