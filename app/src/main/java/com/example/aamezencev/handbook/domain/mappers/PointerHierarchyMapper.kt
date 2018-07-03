package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.db.PointerDataDb
import com.example.aamezencev.handbook.data.presentation.DataPointer

object PointerHierarchyMapper {
    fun fromPresentation(model: PointerDataDb): DataPointer {
        return DataPointer.Builder().build()
        //return PointerHierarchy(model.startIndex, model.finalIndex, ThreeDimensionalMapper.fromPresentation(model.threeDimensionalModelDb))
    }

    fun fromDb(model: DataPointer): PointerDataDb {
        return PointerDataDb()
//        return PointerHierarchyDb().apply {
//            startIndex = model.startIndex
//            finalIndex = model.finalIndex
//            threeDimensionalModelDb = ThreeDimensionalMapper.fromDb(model.threeDimensionalModel)
//        }
    }
}