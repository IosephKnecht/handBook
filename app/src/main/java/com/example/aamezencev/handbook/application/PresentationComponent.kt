package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.presentation.PresentationScope
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListComponent
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListModule
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenComponent
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenModule
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerComponent
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerModule
import com.example.aamezencev.handbook.presentation.loader.di.LoaderComponent
import com.example.aamezencev.handbook.presentation.loader.di.LoaderModule
import dagger.Component

@Component(modules = [PresentationModule::class], dependencies = [BusinessComponent::class])
@PresentationScope
interface PresentationComponent {
    fun addHierarchyListSubmodule(module: HierarchyListModule): HierarchyListComponent
    fun addHierarchyScreenSubmodule(module: HierarchyScreenModule): HierarchyScreenComponent
    fun addViewerModule(viewerModule: ViewerModule): ViewerComponent
    fun addLoaderSubmodule(loaderModule: LoaderModule): LoaderComponent
}