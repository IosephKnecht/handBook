package com.example.aamezencev.handbook.application.di

import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import dagger.Module
import dagger.Provides

@Module
class BusinessModule {
    @Provides
    @BusinessScope
    fun provideDataBaseService(): DataBaseService {
        return DataBaseService()
    }

    @Provides
    @BusinessScope
    fun provideDatabaseLoaderService(): DatabaseLoaderService {
        return DatabaseLoaderService()
    }
}