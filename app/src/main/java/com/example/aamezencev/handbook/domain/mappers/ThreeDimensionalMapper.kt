package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

object ThreeDimensionalMapper {
    fun fromPresentation(model: ThreeDimensionalModelDb): ThreeDimensionalModel {
        return ThreeDimensionalModel(model.primaryKey, model.modelArray)
    }

    fun fromDb(model: ThreeDimensionalModel): ThreeDimensionalModelDb {
        return ThreeDimensionalModelDb().apply {
            modelArray = model.modelArray
        }
    }
}