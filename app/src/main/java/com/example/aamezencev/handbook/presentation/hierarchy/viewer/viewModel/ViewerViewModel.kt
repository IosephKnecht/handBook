package com.example.aamezencev.handbook.presentation.hierarchy.viewer.viewModel

import android.databinding.Bindable
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.ViewerContract

class ViewerViewModel : AbstractViewModel(),ViewerContract.ViewModel {
    override var thrModel: ThreeDimensionalModel? = null
        @Bindable get() = field
        set(value) {
            field = value
            notifyChange()
        }
}