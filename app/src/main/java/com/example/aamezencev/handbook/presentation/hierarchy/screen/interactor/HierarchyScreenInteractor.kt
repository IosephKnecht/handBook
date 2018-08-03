package com.example.aamezencev.handbook.presentation.hierarchy.screen.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.domain.mappers.DataHierarchyElementMapper
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.domain.services.SharedPreferenceService
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HierarchyScreenInteractor(private val dataBaseService: DataBaseService,
                                private val sharedPreferenceService: SharedPreferenceService) : AbstractInteractor<HierarchyScreenContract.Listener>(),
    HierarchyScreenContract.Interactor {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun saveBookmark(bookmarkInfo: BookmarkInfo) {
        sharedPreferenceService.saveUniqueBookmark(bookmarkInfo)
    }

    override fun getDataElement(dataId: Long) {
        compositeDisposable.add(discardResult(dataBaseService.getDataElement(dataId)
            .map { DataHierarchyElementMapper.fromPresentation(it) }) { listener, result ->
            result.data { listener!!.onObtainDataElement(this!!) }
        })
    }
}