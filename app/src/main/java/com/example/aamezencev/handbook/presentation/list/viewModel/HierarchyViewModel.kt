package com.example.aamezencev.handbook.presentation.list.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.BR;

class HierarchyViewModel : BaseObservable() {
    var hierarchyList = listOf<IElement>()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.hierarchyList)
        }
}