package com.example.aamezencev.handbook.presentation.hierarchy.screen.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.mappers.DataHierarchyElementMapper
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HierarchyScreenInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyScreenContract.Listener>(),
        HierarchyScreenContract.Interactor {
    private val compositeDisposable = CompositeDisposable()

    override fun setListener(presenter: HierarchyScreenContract.Listener?) {
        super.setListener(presenter)
        interactorListener = presenter
    }

    override fun onDestroy() {
        interactorListener = null
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun getDataElement(dataId: Long) {
        compositeDisposable.add(dataBaseService.getDataElement(dataId)
                .subscribeOn(Schedulers.io())
                .map { DataHierarchyElementMapper.fromPresentation(it) }
                .subscribe {
                    interactorListener!!.onObtainDataElement(it!!)
                })
    }
}