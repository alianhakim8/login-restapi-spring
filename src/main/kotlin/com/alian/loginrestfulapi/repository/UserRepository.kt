package com.alian.loginrestfulapi.repository

import com.alian.loginrestfulapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1")
    fun findByEmail(email: String): Optional<User>
}