package com.example.aamezencev.handbook.presentation.hierarchy.screen.presenter

import com.example.aamezencev.handbook.common.presenter.AbstractPresenter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.DataPointer
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.domain.mappers.PageMapper
import com.example.aamezencev.handbook.domain.utils.PointerUtil
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
        interactor.saveBookmark(dataId, viewModel!!.contentChipping(position, CONTENT_CHIPPING_VALUE), position)
    }

    override fun removeBookmark(dataId: Long, position: Int) {
        interactor.removeBookmark(dataId, viewModel!!.contentChipping(position, CONTENT_CHIPPING_VALUE), position)
    }

    override fun onObtainDataElement(pageList: List<Page>, pointerList: List<DataPointer>) {
        viewModel!!.pageList =
            PointerUtil.pastePointers(pageList, pointerList.toMutableList()) { router.showViewer(androidComponent!!, it.thrModelId) }
    }
}