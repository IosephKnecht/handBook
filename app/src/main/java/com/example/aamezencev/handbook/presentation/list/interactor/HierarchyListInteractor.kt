package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import io.reactivex.schedulers.Schedulers

class HierarchyListInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
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

    override fun insertHierarchyElement(hierarchyElement: HierarchyElementDb,
                                        data: DataHierarchyDb,
                                        modelList: List<ThreeDimensionalModelDb>) {
        dataBaseService.insert(hierarchyElement, data, modelList)
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun insertHierarchyElementList(list: List<Triple<HierarchyElementDb, DataHierarchyDb, List<ThreeDimensionalModelDb>>>) {
        dataBaseService.insertHierarchyList(list)
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}