package com.example.aamezencev.handbook.application

import android.content.Context
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    fun getApplicationContext(): Context
}