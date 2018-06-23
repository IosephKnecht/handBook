package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.domain.FakeService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract

class HierarchyListInteractor : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
    override fun setListener(presenter: HierarchyListContract.Listener?) {
        interactorListener = presenter
    }

    override fun getHierarchy() {
        interactorListener?.onObtainHieararchy(FakeService.getHierarchy())
    }

}