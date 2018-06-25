package com.example.aamezencev.handbook.application

import android.app.Application
import com.example.aamezencev.handbook.data.db.DaoMaster
import com.example.aamezencev.handbook.data.db.DaoSession

class AppDelegate : Application() {
    companion object {
        var appComponent: AppComponent? = null
        var presentationComponent: PresentationComponent? = null
        var businessComponent: BusinessComponent? = null
    }

    private lateinit var daoSession: DaoSession

    override fun onCreate() {
        super.onCreate()
        initDataBase()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this, daoSession))
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

    private fun initDataBase() {
        val helper = DaoMaster.DevOpenHelper(this, "handBook-db")
        val dataBase = helper.writableDb
        daoSession = DaoMaster(dataBase).newSession()
    }
}