package com.example.aamezencev.handbook.presentation.list.viewModel

import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract

class HierarchyElementVM : AbstractViewModel(), HierarchyListContract.ViewModel {
    override var name: String = ""
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }

    override var childList = mutableListOf<IHierarchy>()
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }

    override var text = ""
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }
}