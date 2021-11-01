package com.alian.loginrestfulapi.controllers

import com.alian.loginrestfulapi.models.User
import com.alian.loginrestfulapi.repository.UserRepository
import com.alian.loginrestfulapi.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class UserController(repository: UserRepository) {

    private val service = UserService(repository)

    @PostMapping("login")
    fun login(
        @RequestParam email: String,
        @RequestParam password: String
    ) = service.login(email, password)

    @PostMapping("register")
    fun register(
        @RequestBody user: User
    ) = service.register(user)

}