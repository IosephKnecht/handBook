package com.example.aamezencev.handbook.application

import android.content.Context
import com.example.aamezencev.handbook.data.db.DaoSession
import dagger.Module
import dagger.Provides

@Module
class AppModule(val applicationContext: Context,
                val daoSession: DaoSession) {
    @Provides
    @AppScope
    fun provideApplicationContext() = applicationContext

    @Provides
    @AppScope
    fun provideDaoSession() = daoSession
}