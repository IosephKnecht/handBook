package com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel

import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract

class HierarchyInfoVM : AbstractViewModel(), HierarchyScreenContract.ViewModel {
    override var description = SpannableStringBuilder()
}