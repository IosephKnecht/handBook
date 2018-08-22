package com.example.aamezencev.handbook.common.interactor

import com.example.aamezencev.handbook.application.Constants
import com.hypertrack.hyperlog.HyperLog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class AbstractInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {
    private var interactorListener: L? = null

    override fun setListener(presenter: L?) {
        interactorListener = presenter
    }

    override fun onDestroy() {
        interactorListener = null
    }

    protected fun <R, O : Observable<R>> discardResult(observable: O,
                                                       block: (listener: L?, result: PendingResult<R>) -> Unit): Disposable {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { block(interactorListener, PendingResult(null, it)) }
                .subscribe({
                    block(interactorListener, PendingResult(it, null))
                }, {
                    it.printStackTrace()
                    HyperLog.w(Constants.INTERACTOR_ERROR, it.message)
                })
    }

    data class PendingResult<R>(private val data: R?,
                                private val throwable: Throwable?) {
        fun data(block: R.() -> Unit) = data?.apply(block)
        fun throwable(block: Throwable.() -> Unit) = throwable?.apply(block)
    }
}