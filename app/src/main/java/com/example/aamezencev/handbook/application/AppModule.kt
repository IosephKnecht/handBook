package com.example.aamezencev.handbook.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val applicationContext: Context) {
    @Provides
    @AppScope
    fun provideApplicationContext() = applicationContext
}