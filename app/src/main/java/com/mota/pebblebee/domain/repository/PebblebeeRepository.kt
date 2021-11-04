package com.mota.pebblebee.domain.repository

import com.mota.pebblebee.data.dto.UserDto

interface PebblebeeRepository {

    suspend fun getUser(): UserDto
}