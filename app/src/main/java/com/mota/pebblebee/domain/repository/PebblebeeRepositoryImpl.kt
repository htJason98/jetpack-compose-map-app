package com.mota.pebblebee.domain.repository

import com.mota.pebblebee.data.dto.UserDto
import com.mota.pebblebee.data.remote.PebblebeeApi
import javax.inject.Inject

class PebblebeeRepositoryImpl @Inject constructor(
    private val api: PebblebeeApi
): PebblebeeRepository {

    override suspend fun getUser(): UserDto {
        return api.getUser()
    }
}