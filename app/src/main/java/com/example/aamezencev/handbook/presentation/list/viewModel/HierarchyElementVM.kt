package com.example.aamezencev.handbook.presentation.list.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.example.aamezencev.handbook.data.parcel.IElement
import com.example.aamezencev.handbook.data.presentation.IHierarchy

class HierarchyElementVM : BaseObservable() {
    var name: String = ""
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }

    var childList = mutableListOf<IHierarchy>()
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }

    var text = ""
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyElement)
        }
}