package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.help.HierarchyContainerDb
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HierarchyListInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
    private val compositeDisposable = CompositeDisposable()

    override fun setListener(presenter: HierarchyListContract.Listener?) {
        interactorListener = presenter
    }

    override fun getHierarchy(parentId: Long?) {
        compositeDisposable.add(dataBaseService.getHierarchyElement(parentId)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    interactorListener?.onObtainHieararchy(it)
                })
    }

    override fun insertHierarchyElement(containerDb: HierarchyContainerDb) {
        compositeDisposable.add(dataBaseService.insertHierarchyElement(containerDb.hierarchyElementDb,
                containerDb.hierarchyDataHierarchyDb, containerDb.pointerList)
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    override fun insertHierarchyElementList(containerDbList: List<HierarchyContainerDb>) {
        compositeDisposable.add(dataBaseService.insertHierarchyList(containerDbList)
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}