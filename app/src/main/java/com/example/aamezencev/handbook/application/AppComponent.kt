package com.example.aamezencev.handbook.application

import android.content.Context
import com.example.aamezencev.handbook.data.db.DaoSession
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    fun getApplicationContext(): Context

    fun getDaoSession(): DaoSession
}