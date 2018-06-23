package com.example.aamezencev.handbook.presentation.list.di

import com.example.aamezencev.handbook.presentation.common.ModuleScope
import com.example.aamezencev.handbook.presentation.list.interactor.HierarchyListInteractor
import com.example.aamezencev.handbook.presentation.list.presenter.HierarchyListPresenter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [HierarchyListModule::class])
@ModuleScope
interface HierarchyListComponent {
    fun getPresenter(): HierarchyListPresenter

    fun getHierarchyListViewModel(): HierarchyElementVM

    fun getHierarchyInteractor(): HierarchyListInteractor
}