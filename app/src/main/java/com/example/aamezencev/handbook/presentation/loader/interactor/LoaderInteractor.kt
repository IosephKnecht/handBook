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
import io.reactivex.disposables.CompositeDisposable
import java.io.InputStream

class LoaderInteractor(private val databaseLoaderService: DatabaseLoaderService,
                       private val sessionInitializer: SessionInitializer<*>,
                       private val sharedPreferenceService: SharedPreferenceService) :
        AbstractInteractor<LoaderContract.Listener>(), LoaderContract.Interactor {

    private val compositeSubscription = CompositeDisposable()

    override fun copyDatabase(uri: Uri, inputSteam: InputStream?) {
        compositeSubscription.add(discardResult(databaseLoaderService.copyDatabase(inputSteam)
                .flatMap {
                    sessionInitializer.initSesseion()
                            .doOnNext { AppDelegate.daoSession = it as DaoSession }
                }
                .flatMap { databaseLoaderService.parseMetaData(uri) }) { listener, result ->
            result.data {
                cacheFilePath(this)
                listener!!.onCopyDatabase(this)
            }
            result.throwable { listener!!.invalidateCache() }
        })
    }

    override fun getCachedFilePath() = sharedPreferenceService.getFilePathList()

    private fun cacheFilePath(databaseInfo: DatabaseInfo) {
        sharedPreferenceService.saveUniqueFilePath(databaseInfo)
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }
}