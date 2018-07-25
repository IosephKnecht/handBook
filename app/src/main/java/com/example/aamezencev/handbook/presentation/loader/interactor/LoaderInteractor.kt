package com.example.aamezencev.handbook.presentation.loader.interactor

import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.GreenDaoSessionInitializer
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import java.io.InputStream

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService,
                       private val sessionInitializer: SessionInitializer<*>) :
        AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {

    override fun copyDatabase(inputSteam: InputStream?) {
        discardResult(databaseLoaderService.copyDatabase(inputSteam)
                .flatMap { sessionInitializer.initSesseion() }) { listener, result ->
            AppDelegate.daoSession = result as DaoSession
            listener!!.onCopyDatabase(true)
        }
    }
}