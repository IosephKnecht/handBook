package com.example.aamezencev.handbook.presentation.hierarchy.list.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.hierarchy.list.interactor.HierarchyListInteractor
import com.example.aamezencev.handbook.presentation.hierarchy.list.presenter.HierarchyListPresenter
import com.example.aamezencev.handbook.presentation.hierarchy.list.viewModel.HierarchyElementVM
import dagger.Subcomponent

@Subcomponent(modules = [HierarchyListModule::class])
@ModuleScope
interface HierarchyListComponent {
    fun getPresenter(): HierarchyListPresenter

    fun getHierarchyListViewModel(): HierarchyElementVM

    fun getHierarchyInteractor(): HierarchyListInteractor
}