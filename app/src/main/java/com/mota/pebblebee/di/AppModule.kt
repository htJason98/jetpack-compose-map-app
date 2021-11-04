package com.mota.pebblebee.di

import com.mota.pebblebee.data.remote.PebblebeeApi
import com.mota.pebblebee.domain.repository.PebblebeeRepository
import com.mota.pebblebee.domain.repository.PebblebeeRepositoryImpl
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
        return PebblebeeRepositoryImpl(api)
    }
}