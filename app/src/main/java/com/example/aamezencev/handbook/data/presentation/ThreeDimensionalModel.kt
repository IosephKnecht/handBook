package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class ThreeDimensionalModel(val id: Long,
                            val modelArray: ByteArray) {
    @HierarchyDSL
    class Builder {
        var id = -1L
        var modelArray = byteArrayOf()

        fun build() = ThreeDimensionalModel(id, modelArray)
    }
}