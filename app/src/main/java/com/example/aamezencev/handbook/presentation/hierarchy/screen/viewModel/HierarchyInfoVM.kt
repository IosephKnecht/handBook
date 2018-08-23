package com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel

import com.example.aamezencev.handbook.BR
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract

class HierarchyInfoVM : AbstractViewModel(), HierarchyScreenContract.ViewModel {
    override var pageList: List<Page> = listOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.page)
        }

    override var marked: Boolean = false

    override fun contentChipping(position: Int, endIndex: Int): String {
        return with(pageList[position].description) {
            if (endIndex > length) this.toString() else substring(endIndex)
        }
    }
}