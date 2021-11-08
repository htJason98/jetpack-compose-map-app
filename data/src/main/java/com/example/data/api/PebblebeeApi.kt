package com.example.data.api

import com.example.domain.entities.User
import retrofit2.http.GET

interface PebblebeeApi {

    @GET("/v1/users")
    suspend fun getUser(): User

}