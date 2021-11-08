package com.example.data.repository

import com.example.domain.entities.User

interface RemoteRepository {

    suspend fun getUser(): User
}