package com.example.aamezencev.handbook.presentation.loader.interactor

import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import io.reactivex.disposables.CompositeDisposable
import java.io.InputStream

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService,
                       private val sessionInitializer: SessionInitializer<*>) :
        AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {

    private val compositeSubscription = CompositeDisposable()

    override fun copyDatabase(inputSteam: InputStream?) {
        compositeSubscription.add(discardResult(databaseLoaderService.copyDatabase(inputSteam)
                .flatMap { sessionInitializer.initSesseion() }) { listener, result ->
            AppDelegate.daoSession = result as DaoSession
            listener!!.onCopyDatabase(true)
        })
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }
}