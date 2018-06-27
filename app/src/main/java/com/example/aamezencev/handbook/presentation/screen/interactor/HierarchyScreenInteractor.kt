package com.example.aamezencev.handbook.presentation.screen.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract

class HierarchyScreenInteractor(private val dataBaseService: DataBaseService) : AbstractInteractor<HierarchyScreenContract.Listener>(),
        HierarchyScreenContract.Interactor {
    override fun setListener(presenter: HierarchyScreenContract.Listener?) {
        super.setListener(presenter)
        interactorListener = presenter
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getDataElement(dataId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}