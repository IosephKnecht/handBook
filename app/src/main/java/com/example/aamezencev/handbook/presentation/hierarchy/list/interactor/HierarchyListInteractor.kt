package com.example.aamezencev.handbook.presentation.hierarchy.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HierarchyListInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
    private val compositeDisposable = CompositeDisposable()


    override fun getHierarchy(parentId: Long?) {
        compositeDisposable.add(discardResult(dataBaseService.getHierarchyList(parentId)) { listener, result ->
            result.data { listener!!.onObtainHieararchy(this) }
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}