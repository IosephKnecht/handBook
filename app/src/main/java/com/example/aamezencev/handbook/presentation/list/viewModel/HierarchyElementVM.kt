package com.example.aamezencev.handbook.presentation.list.viewModel

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract

class HierarchyElementVM : AbstractViewModel(), HierarchyListContract.ViewModel {
    override var hierarchy: MutableList<HierarchyElement> = arrayListOf()
        @Bindable get() = field
        set(value) {
            field = value
            notifyChange()
        }

}