package com.example.aamezencev.handbook.application.di

import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.FileManagerService
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import dagger.Component

@Component(modules = [BusinessModule::class], dependencies = [AppComponent::class])
@BusinessScope
interface BusinessComponent {
    fun getDataBaseService(): DataBaseService
    fun getDatabaseLoaderService(): DatabaseLoaderService
    fun getSessionInitializer(): SessionInitializer<DaoSession>
    fun getSharedPreferenceService(): SharedPreferenceService
    fun getFileManagerService(): FileManagerService
}