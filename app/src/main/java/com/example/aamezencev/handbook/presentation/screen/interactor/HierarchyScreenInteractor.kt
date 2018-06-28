package com.example.aamezencev.handbook.presentation.screen.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.mappers.DataHierarchyElementMapper
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract
import io.reactivex.schedulers.Schedulers

class HierarchyScreenInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyScreenContract.Listener>(),
        HierarchyScreenContract.Interactor {
    override fun setListener(presenter: HierarchyScreenContract.Listener?) {
        super.setListener(presenter)
        interactorListener = presenter
    }

    override fun onDestroy() {
        interactorListener = null
        super.onDestroy()
    }

    override fun getDataElement(dataId: Long) {
        dataBaseService.getHierarchyDataElement(dataId)
                .subscribeOn(Schedulers.io())
                .map { DataHierarchyElementMapper.fromPresentation(it) }
                .subscribe {
                    interactorListener!!.onObtainDataElement(it!!)
                }
    }
}