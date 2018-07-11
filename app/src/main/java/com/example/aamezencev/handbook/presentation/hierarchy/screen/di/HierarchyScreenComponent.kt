package com.example.aamezencev.handbook.presentation.hierarchy.screen.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import dagger.Subcomponent

@Subcomponent(modules = [HierarchyScreenModule::class])
@ModuleScope
interface HierarchyScreenComponent {
    fun getViewModel(): HierarchyScreenContract.ViewModel
    fun getPresnter(): HierarchyScreenContract.Presenter
}