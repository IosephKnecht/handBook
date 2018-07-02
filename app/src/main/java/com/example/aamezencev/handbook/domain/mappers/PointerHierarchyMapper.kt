package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.PointerHierarchyDb
import com.example.aamezencev.handbook.data.presentation.PointerHierarchy

object PointerHierarchyMapper {
    fun fromPresentation(model: PointerHierarchyDb): PointerHierarchy {
        return PointerHierarchy(model.startIndex, model.finalIndex, ThreeDimensionalMapper.fromPresentation(model.threeDimensionalModelDb))
    }

    fun fromDb(model: PointerHierarchy): PointerHierarchyDb {
        return PointerHierarchyDb().apply {
            startIndex = model.startIndex
            finalIndex = model.finalIndex
            threeDimensionalModelDb = ThreeDimensionalMapper.fromDb(model.threeDimensionalModel)
        }
    }
}