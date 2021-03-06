package com.example.aamezencev.handbook.presentation.loader

import android.content.Intent
import android.databinding.Bindable
import android.net.Uri
import android.support.v4.app.Fragment
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.router.MvpRouter
import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.DatabaseInfo

interface LoaderContract {
    enum class State {
        LOAD,
        OPEN
    }

    interface ViewModel : MvpViewModel {
        var state: State
        var loadableUri: Uri?
        var databaseList: MutableList<DatabaseInfo>
            @Bindable get

        fun cachedUri(databaseInfo: DatabaseInfo): Boolean
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainFilePath(uri: Uri?)
        fun deleteFilePath(databaseInfo: DatabaseInfo)
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainFilePath(databaseInfo: DatabaseInfo)
    }

    interface Interactor : MvpInteractor<Listener> {
        fun copyDatabase(uri: Uri)
        fun getCachedFilePath(): List<DatabaseInfo>
        fun removeFilepath(databaseInfo: DatabaseInfo)
    }

    interface RouterListener : MvpRouter.Listener

    interface Router : MvpRouter<RouterListener> {
        fun showSelectFileActivity(androidComponent: AndroidComponent)
        fun showHierarchyFragment(androidComponent: AndroidComponent)
        fun showBookmarksFragment(androidComponent: AndroidComponent)
    }

    interface InputModule {
        fun createOpenFileIntent(): Intent
        fun createHierarchyFragment(parentId: Long): Fragment
        fun createBookmarkFragment(): Fragment
    }
}