package com.alian.loginrestfulapi.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @NotEmpty
    val name: String,

    @NotEmpty
    @Column(unique = true)
    val email: String,

    @NotEmpty
    var password: String,

    @CreatedDate
    var createdAt: Date,

    @LastModifiedBy
    val updatedAt: Date
)
