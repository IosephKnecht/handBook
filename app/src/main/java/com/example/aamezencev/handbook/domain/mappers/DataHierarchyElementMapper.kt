package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement

object DataHierarchyElementMapper {
    fun fromPresentation(model: DataHierarchyDb): DataHierarchyElement {
        return DataHierarchyElement(model.primaryKey, model.description, model.threeDimensionalModels
                .map { ThreeDimensionalMapper.fromPresentation(it) })
    }

    fun fromDb(model: DataHierarchyElement?): DataHierarchyDb {
        return DataHierarchyDb().apply {
            description = model?.description
        }
    }
}