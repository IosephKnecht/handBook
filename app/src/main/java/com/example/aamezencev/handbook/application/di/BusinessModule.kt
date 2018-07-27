package com.example.aamezencev.handbook.application.di

import android.content.Context
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.GreenDaoSessionInitializer
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

    @Provides
    @BusinessScope
    fun provideSessionInitializer(applicationContext: Context): SessionInitializer<DaoSession> {
        return GreenDaoSessionInitializer(applicationContext)
    }
}