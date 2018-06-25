package com.example.aamezencev.handbook.application

import android.app.Application

class AppDelegate : Application() {
    companion object {
        var appComponent: AppComponent? = null
        var presentationComponent: PresentationComponent? = null
        var businessComponent: BusinessComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        businessComponent = DaggerBusinessComponent.builder()
                .appComponent(appComponent)
                .businessModule(BusinessModule())
                .build()

        presentationComponent = DaggerPresentationComponent.builder()
                .presentationModule(PresentationModule())
                .businessComponent(businessComponent)
                .build()
    }
}