package com.example.data.repository

import com.example.data.api.PebblebeeApi
import com.example.domain.entities.User
import com.example.domain.repositories.PebblebeeRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: PebblebeeApi
): PebblebeeRepository {

    override suspend fun getUser(): User {
        return api.getUser()
    }
}