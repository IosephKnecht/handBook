package com.example.aamezencev.handbook.application.di

import android.content.Context
import android.net.Uri
import com.example.aamezencev.handbook.data.UriTypeHierarchyAdapter
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.*
import com.google.gson.Gson
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
    fun provideDatabaseLoaderService(applicationContext: Context): DatabaseLoaderService {
        return DatabaseLoaderService(applicationContext)
    }

    @Provides
    @BusinessScope
    fun provideSessionInitializer(applicationContext: Context): SessionInitializer<DaoSession> {
        return GreenDaoSessionInitializer(applicationContext)
    }

    @Provides
    @BusinessScope
    fun provideSharedPreferenceService(applicationContext: Context, gson: Gson): SharedPreferenceService {
        return SharedPreferenceService(applicationContext, gson)
    }

    @Provides
    @BusinessScope
    fun provideGson(uriAdapter: UriTypeHierarchyAdapter): Gson {
        return Gson()
            .newBuilder()
            .registerTypeAdapter(Uri::class.java, uriAdapter)
            .create()
    }

    @Provides
    @BusinessScope
    fun provideUriAdapter() = UriTypeHierarchyAdapter()

    @Provides
    @BusinessScope
    fun provideFileManagerService(applicationContext: Context): FileManagerService {
        return FileManagerService(applicationContext)
    }
}