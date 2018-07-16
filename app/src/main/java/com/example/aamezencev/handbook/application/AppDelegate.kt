package com.example.aamezencev.handbook.application

import android.app.Application
import android.util.Log
import com.example.aamezencev.handbook.data.db.DaoMaster
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.DataBaseOpenHelper
import com.hypertrack.hyperlog.HyperLog

class AppDelegate : Application() {
    companion object {
        var appComponent: AppComponent? = null
        var presentationComponent: PresentationComponent? = null
        var businessComponent: BusinessComponent? = null
    }

    private lateinit var daoSession: DaoSession

    override fun onCreate() {
        super.onCreate()
        DataBaseOpenHelper.copyDataBase(this)
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
        initLogger()
    }

    private fun initDataBase() {
        val helper = DaoMaster.DevOpenHelper(this, DataBaseOpenHelper.DATABASE_NAME)
        val dataBase = helper.writableDb
        daoSession = DaoMaster(dataBase).newSession()
    }

    private fun initLogger() {
        HyperLog.initialize(this)
        HyperLog.setLogLevel(Log.WARN)
    }
}