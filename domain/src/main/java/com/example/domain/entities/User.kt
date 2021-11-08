package com.example.domain.entities

data class User(
    val email: String,
    val uuid: String,
    val id: String,
    val lastName: String,
    val firstName: String,
    val joinDate: String,
    val cachedAt: String,
    val imageUrl: String
)
