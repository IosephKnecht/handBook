package com.example.aamezencev.handbook.presentation.loader.interactor

import android.database.Cursor
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
import java.io.InputStream

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService,
                       private val sessionInitializer: SessionInitializer<*>,
                       private val sharedPreferenceService: SharedPreferenceService) :
    AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {

    private val compositeSubscription = CompositeDisposable()
    private var lastSuccessInit: DatabaseInfo? = null

    override fun copyDatabase(cursor: Cursor, inputSteam: InputStream?) {
        compositeSubscription.add(discardResult(
            databaseLoaderService.copyDatabase(inputSteam)
                .flatMap { _ ->
                    sessionInitializer.initSesseion()
                        .flatMap { session ->
                            if (lastSuccessInit == null || lastSuccessInit?.uri != cursor.notificationUri) {
                                sessionInitializer.initSesseion()
                                    .doOnNext { AppDelegate.daoSession = session as DaoSession }
                                    .flatMap { databaseLoaderService.parseMetaData(cursor) }
                                    .doAfterNext { sharedPreferenceService.saveDatabaseName(it.name) }
                            } else Observable.just(lastSuccessInit)
                        }
                }) { listener, result ->
            result.data {
                lastSuccessInit = this
                cacheFilePath(this)
                listener!!.onCopyDatabase(this)
            }
            result.throwable { lastSuccessInit = null }
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