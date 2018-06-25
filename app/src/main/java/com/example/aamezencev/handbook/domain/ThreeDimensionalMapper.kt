package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

object ThreeDimensionalMapper {
    fun fromPresentation(model: ThreeDimensionalModelDb): ThreeDimensionalModel {
        return ThreeDimensionalModel(model.primaryKey, model.dataHierarchyId, model.modelArray)
    }
}