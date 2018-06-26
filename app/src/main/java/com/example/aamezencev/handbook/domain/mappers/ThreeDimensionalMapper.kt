package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel

object ThreeDimensionalMapper {
    fun fromPresentation(model: ThreeDimensionalModelDb): ThreeDimensionalModel {
        return ThreeDimensionalModel(model.primaryKey, model.dataHierarchyId, model.modelArray)
    }

    fun fromDb(modelList: List<ThreeDimensionalModel>?): List<ThreeDimensionalModelDb>? {
        return modelList?.map { ThreeDimensionalModelDb().apply { modelArray = it?.modelArray } }
    }
}