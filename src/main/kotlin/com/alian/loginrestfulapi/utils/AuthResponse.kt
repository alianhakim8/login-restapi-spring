package com.alian.loginrestfulapi.utils

import com.alian.loginrestfulapi.models.User
import lombok.experimental.SuperBuilder

@SuperBuilder
data class AuthResponse(
    val isSuccessful: Boolean,
    val message: String,
    val user: User? = null
)
