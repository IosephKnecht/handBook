package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import io.reactivex.schedulers.Schedulers

class HierarchyListInteractor(val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
    override fun setListener(presenter: HierarchyListContract.Listener?) {
        interactorListener = presenter
    }

    override fun getHierarchy(parentId: Long?) {
        dataBaseService.get(parentId)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    interactorListener?.onObtainHieararchy(it)
                }
    }

}