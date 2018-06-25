package com.example.aamezencev.handbook.application

import com.example.aamezencev.handbook.domain.BusinessScope
import dagger.Component

@Component(modules = [BusinessModule::class], dependencies = [AppComponent::class])
@BusinessScope
interface BusinessComponent {
}