package com.example.aamezencev.handbook.presentation.log.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.mappers.LogMapper
import com.example.aamezencev.handbook.domain.services.FileManagerService
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import com.hypertrack.hyperlog.HyperLog
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class LogListInteractor(private val fileManagerService: FileManagerService) : AbstractInteractor<LogListContract.Listener>(),
    LogListContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getLogs() {
        val observable = Observable.fromCallable { HyperLog.getDeviceLogs(false, 0) }
            .map { LogMapper.fromPresentation(it) }

        compositeDisposable.add(discardResult(observable) { listener, result ->
            result.data { listener!!.onObtainLog(this) }
            result.throwable { }
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}