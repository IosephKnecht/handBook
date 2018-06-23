package com.example.aamezencev.handbook.application

import android.app.Application

class AppDelegate : Application() {
    companion object {
        var appComponent: AppComponent? = null
        var presentationComponent: PresentationComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        presentationComponent = DaggerPresentationComponent.builder()
                .presentationModule(PresentationModule())
                .build()
    }
}