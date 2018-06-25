package com.example.aamezencev.handbook.presentation.list.interactor

import com.example.aamezencev.handbook.common.interactor.AbstractInteractor
import com.example.aamezencev.handbook.common.interactor.MvpInteractor
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.domain.FakeService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract

class HierarchyListInteractor : AbstractInteractor<HierarchyListContract.Listener>(), HierarchyListContract.Interactor {
    override fun setListener(presenter: HierarchyListContract.Listener?) {
        interactorListener = presenter
    }

    override fun getHierarchy() {
        interactorListener?.onObtainHieararchy(object :IHierarchy{
            override fun isHasNesting(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override val name: String
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            override val childList: List<IHierarchy>
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            override val text: String
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

            override fun iterator(): Iterator<IElement<IHierarchy>?> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}