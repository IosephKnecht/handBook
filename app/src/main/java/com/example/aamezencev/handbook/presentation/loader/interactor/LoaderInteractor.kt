package com.example.aamezencev.handbook.presentation.loader.interactor

import android.net.Uri
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo
import com.example.aamezencev.handbook.domain.common.SessionInitializer
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService,
                       private val sessionInitializer: SessionInitializer<*>,
                       private val sharedPreferenceService: SharedPreferenceService) :
    AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {

    private val compositeSubscription = CompositeDisposable()
    private var lastSuccessInit: DatabaseInfo? = null

    override fun copyDatabase(uri: Uri) {
        val observable = if (lastSuccessInit == null || lastSuccessInit?.uri != uri) {
            databaseLoaderService.copy(uri)
                .flatMap {
                    sessionInitializer.initSesseion()
                        .doOnNext { session -> AppDelegate.daoSession = session as DaoSession }
                        .flatMap { _ -> databaseLoaderService.parseMetaData(uri) }
                        .doAfterNext { info -> sharedPreferenceService.saveDatabaseName(info.name) }
                }
        } else Observable.just(lastSuccessInit)

        compositeSubscription.add(discardResult(observable) { listener, result ->
            result.data {
                lastSuccessInit = this
                cacheFilePath(this)
                listener!!.onObtainFilePath(this)
            }
        })
    }

    override fun getCachedFilePath() = sharedPreferenceService.getFilePathList()

    override fun removeFilepath(databaseInfo: DatabaseInfo) {
        sharedPreferenceService.removeFilePath(databaseInfo)
    }

    private fun cacheFilePath(databaseInfo: DatabaseInfo) {
        sharedPreferenceService.saveUniqueFilePath(databaseInfo)
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }
}