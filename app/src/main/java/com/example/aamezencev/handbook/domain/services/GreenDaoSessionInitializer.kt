package com.example.aamezencev.handbook.domain.services

import android.content.Context
import com.example.aamezencev.handbook.application.Constants
import com.example.aamezencev.handbook.data.db.DaoMaster
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import io.reactivex.Observable
import javax.inject.Inject

class GreenDaoSessionInitializer @Inject constructor(private val applicationContext: Context) : SessionInitializer<DaoSession> {
    override fun initSesseion(): Observable<DaoSession> {
        return Observable.create {
            try {
                val helper = DaoMaster.DevOpenHelper(applicationContext, Constants.DATABASE_NAME)
                val dataBase = helper.writableDb
                it.onNext(DaoMaster(dataBase).newSession())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }
}