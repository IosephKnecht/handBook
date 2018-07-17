package com.example.aamezencev.handbook.presentation.hierarchy.viewer.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.ViewerContract
import io.reactivex.disposables.CompositeDisposable

class ViewerInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<ViewerContract.Listener>(), ViewerContract.Interactor {
    private val compositeDisposable = CompositeDisposable()

    override fun getThreeDimensionalModel(thrModelId: Long) {
        compositeDisposable.add(discardResult(dataBaseService.getThrModel(thrModelId)) { listener, result ->
            listener?.onObtainThrModel(result)
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}