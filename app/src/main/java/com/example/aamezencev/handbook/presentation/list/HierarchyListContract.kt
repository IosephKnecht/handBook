package com.example.aamezencev.handbook.presentation.list

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel
import com.example.aamezencev.handbook.data.presentation.IHierarchy

interface HierarchyListContract {
    interface ViewModel : MvpViewModel {
        var name: String
            @Bindable get
        var childList: MutableList<IHierarchy>
            @Bindable get
        var text: String
            @Bindable get
    }

    interface Listener : MvpInteractor.Listener {
        fun onObtainHieararchy(hierarchy: IHierarchy)
    }

    interface Presenter : MvpPresenter<ViewModel> {
        fun obtainHieararchy()
    }

    interface Interactor : MvpInteractor<Listener> {
        fun getHierarchy()
    }
}