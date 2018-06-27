package com.example.aamezencev.handbook.presentation.screen.viewModel

import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract

class HierarchyInfoVM : AbstractViewModel(), HierarchyScreenContract.ViewModel {
    override var description: String? = null
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(-1)
        }
    override var listModels: List<ThreeDimensionalModel>? = null
}