package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.domain.BusinessScope
import com.example.aamezencev.handbook.domain.services.DataBaseService
import com.example.aamezencev.handbook.domain.services.DatabaseLoaderService
import dagger.Component

@Component(modules = [BusinessModule::class], dependencies = [AppComponent::class])
@BusinessScope
interface BusinessComponent {
    fun getDataBaseService(): DataBaseService
    fun getDatabaseLoaderService(): DatabaseLoaderService
}