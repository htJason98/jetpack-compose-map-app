package com.mota.presentation.di

import com.example.data.api.PebblebeeApi
import com.example.data.repository.RepositoryImpl
import com.example.domain.repositories.PebblebeeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    @Singleton
    fun providePebblebeeApi(): PebblebeeApi {
        return Retrofit
            .Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PebblebeeApi::class.java)
    }

    @Singleton
    fun provideUserRepository(api: PebblebeeApi): PebblebeeRepository {
        return RepositoryImpl(api = api)
    }
}