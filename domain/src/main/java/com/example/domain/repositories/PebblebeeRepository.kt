package com.example.domain.repositories

import com.example.domain.entities.User

interface PebblebeeRepository {
    suspend fun getUser(): User
}