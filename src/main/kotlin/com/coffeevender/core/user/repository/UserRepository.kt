package com.coffeevender.core.user.repository

import com.coffeevender.core.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByLoginId(loginId: String): User?
    fun existsByLoginId(loginId: String): Boolean
} 