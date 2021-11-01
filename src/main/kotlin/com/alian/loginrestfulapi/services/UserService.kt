package com.alian.loginrestfulapi.services

import com.alian.loginrestfulapi.models.User
import com.alian.loginrestfulapi.repository.UserRepository
import com.alian.loginrestfulapi.utils.AuthResponse
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.ResponseStatus
import java.sql.Timestamp
import java.time.Instant

class UserService(private val repository: UserRepository) {

    fun login(email: String, password: String): ResponseEntity<AuthResponse> {
        val user = repository.findByEmail(email)
        return if (user.isPresent) {
            val encoder = BCryptPasswordEncoder()
            val passwordFromDb = user.get().password
            return if (encoder.matches(password, passwordFromDb)) {
//                AuthResponse(
//                    true,
//                    "login successful",
//                    User(
//                        user.get().id,
//                        user.get().name,
//                        user.get().email,
//                        user.get().password,
//                        user.get().createdAt,
//                        user.get().updatedAt
//                    )
//                )
                ResponseEntity.ok(
                    AuthResponse(
                        true,
                        "login successful",
                        User(
                            user.get().id,
                            user.get().name,
                            user.get().email,
                            user.get().password,
                            user.get().createdAt,
                            user.get().updatedAt
                        )
                    )
                )
            } else {

                ResponseEntity(
                    AuthResponse(
                        false, "password incorrect", null
                    ), HttpStatus.BAD_REQUEST
                )
            }
        } else {
            ResponseEntity(
                AuthResponse(
                    false, "email not exists", null
                ), HttpStatus.BAD_REQUEST
            )
        }
    }

    fun register(newUser: User): AuthResponse {
        val user = repository.findByEmail(newUser.email)
        return if (user.isPresent) {
            AuthResponse(false, "email already exists", null)
        } else {
            val encoder = BCryptPasswordEncoder()
            val hashedPassword = encoder.encode(newUser.password)
            val createdAt = Timestamp.from(Instant.now())
            newUser.password = hashedPassword
            newUser.createdAt = createdAt
            repository.save(newUser)
            AuthResponse(true, "register successful", newUser)
        }
    }
}