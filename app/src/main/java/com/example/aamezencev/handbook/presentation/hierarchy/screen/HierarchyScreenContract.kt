package com.example.aamezencev.handbook.presentation.hierarchy.screen

import android.content.Intent
import android.databinding.Bindable
import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.data.presentation.DataElement
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

interface HierarchyScreenContract {
    interface ViewModel : MvpViewModel {
        var description: SpannableStringBuilder
            @Bindable get
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainDataElement(data: DataElement)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainDataElement(dataId: Long)
        fun addBookmark(bookmarkInfo: BookmarkInfo)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getDataElement(dataId: Long)
        fun saveBookmark(bookmarkInfo: BookmarkInfo)
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun showViewer(androidComponent: AndroidComponent, thrModelId: Long)
    }

}