package com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel

import android.databinding.Bindable
import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.BR
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract

class HierarchyInfoVM : AbstractViewModel(), HierarchyScreenContract.ViewModel {
    override var description = SpannableStringBuilder()
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewModel)
        }
}