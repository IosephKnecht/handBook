package com.example.aamezencev.handbook.application.di

import android.content.Context
import com.example.aamezencev.handbook.data.db.DaoSession
import dagger.Module
import dagger.Provides

@Module
class AppModule(val applicationContext: Context) {
    @Provides
    @AppScope
    fun provideApplicationContext() = applicationContext
}