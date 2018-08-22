package com.example.aamezencev.handbook.presentation.hierarchy.screen.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.domain.mappers.SpannableMapper
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.screen.router.HierarchyScreenRouter

class HierarchyScreenPresenter(private var router: HierarchyScreenContract.Router,
                               private var interactor: HierarchyScreenContract.Interactor) : AbstractPresenter<HierarchyScreenContract.ViewModel>(),
        HierarchyScreenContract.Presenter, HierarchyScreenContract.Listener {

    private val CONTENT_CHIPPING_VALUE = 100

    override fun attachView(viewModel: HierarchyScreenContract.ViewModel, androidComponent: AndroidComponent) {
        super.attachView(viewModel, androidComponent)
        interactor.setListener(this)
    }

    override fun detachView() {
        super.detachView()
        androidComponent = null
    }

    override fun destroy() {
        interactor.onDestroy()
        super.destroy()
    }

    override fun obtainDataElement(dataId: Long) {
        interactor.getDataElement(dataId)
    }

    override fun addBookmark(dataId: Long, position: Int) {
        interactor.saveBookmark(dataId, viewModel!!.contentChipping(CONTENT_CHIPPING_VALUE), position)
    }

    override fun removeBookmark(dataId: Long, position: Int) {
        interactor.removeBookmark(dataId, viewModel!!.contentChipping(CONTENT_CHIPPING_VALUE), position)
    }

    override fun onObtainDataElement(data: DataElement) {
        viewModel!!.description = SpannableMapper.fromSpannable(data.description, data.pointerList) {
            router.showViewer(androidComponent!!, it.thrModelId)
        }
    }
}