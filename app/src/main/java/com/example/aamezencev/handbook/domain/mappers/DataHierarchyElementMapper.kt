package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement

object DataHierarchyElementMapper {
    fun fromPresentation(model: DataHierarchyDb?): DataHierarchyElement? {
        if (model != null && !model.isEmpty) {
            return DataHierarchyElement(model.primaryKey, model.description,
                    model.threeDimensionalModels.map { ThreeDimensionalMapper.fromPresentation(it) },
                    model.pointerList.map { PointerHierarchyMapper.fromPresentation(it) })
        } else {
            return null
        }
    }

    fun fromDb(model: DataHierarchyElement?): DataHierarchyDb {
        return DataHierarchyDb().apply {
            description = model?.description
        }
    }
}