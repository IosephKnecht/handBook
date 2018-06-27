package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.presentation.PresentationScope
import com.example.aamezencev.handbook.presentation.list.di.HierarchyListComponent
import com.example.aamezencev.handbook.presentation.list.di.HierarchyListModule
import com.example.aamezencev.handbook.presentation.screen.di.HierarchyScreenComponent
import com.example.aamezencev.handbook.presentation.screen.di.HierarchyScreenModule
import dagger.Component

@Component(modules = [PresentationModule::class], dependencies = [BusinessComponent::class])
@PresentationScope
interface PresentationComponent {
    fun addHierarchyListSubmodule(module: HierarchyListModule): HierarchyListComponent
    fun addHierarchyScreenSubmodule(module: HierarchyScreenModule): HierarchyScreenComponent
}