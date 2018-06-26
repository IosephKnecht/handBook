package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
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
        compositeDisposable.add(dataBaseService.get(parentId)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    interactorListener?.onObtainHieararchy(it)
                })
    }

    override fun insertHierarchyElement(hierarchyElement: HierarchyElementDb,
                                        data: DataHierarchyDb,
                                        modelList: List<ThreeDimensionalModelDb>) {
        compositeDisposable.add(dataBaseService.insert(hierarchyElement, data, modelList)
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    override fun insertHierarchyElementList(list: List<Triple<HierarchyElementDb, DataHierarchyDb, List<ThreeDimensionalModelDb>>>) {
        compositeDisposable.add(dataBaseService.insertHierarchyList(list)
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}