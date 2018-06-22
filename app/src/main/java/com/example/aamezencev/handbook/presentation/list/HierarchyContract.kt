package com.example.aamezencev.handbook.presentation.list

import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface HierarchyContract {
    interface ViewModel : MvpViewModel {

    }

    interface Presenter : MvpPresenter<ViewModel> {

    }

    interface Interactor<P : MvpPresenter<MvpViewModel>> : MvpInteractor<P> {

    }
}