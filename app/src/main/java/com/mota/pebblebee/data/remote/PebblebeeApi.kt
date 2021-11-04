package com.mota.pebblebee.data.remote

import com.mota.pebblebee.data.dto.UserDto
import com.mota.pebblebee.domain.model.User
import retrofit2.http.GET

interface PebblebeeApi {

    @GET("/v1/users")
    suspend fun getUser(): UserDto

}