package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.services.DataBaseService
import dagger.Module
import dagger.Provides

@Module
class BusinessModule {
    @Provides
    @BusinessScope
    fun provideDataBaseService(daoSession: DaoSession): DataBaseService {
        return DataBaseService(daoSession)
    }
}