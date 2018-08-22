package com.example.aamezencev.handbook.presentation.hierarchy.screen

import android.databinding.Bindable
import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DataElement

interface HierarchyScreenContract {
    interface ViewModel : MvpViewModel {
        var marked: Boolean
        var description: SpannableStringBuilder
            @Bindable get

        fun contentChipping(endIndex: Int): String
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainDataElement(data: DataElement)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainDataElement(dataId: Long)
        fun addBookmark(dataId: Long, position: Int)
        fun removeBookmark(dataId: Long, position: Int)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getDataElement(dataId: Long)
        fun saveBookmark(dataId: Long, contentChipping: String, position: Int)
        fun removeBookmark(dataId: Long, contentChipping: String, position: Int)
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun showViewer(androidComponent: AndroidComponent, thrModelId: Long)
    }

}