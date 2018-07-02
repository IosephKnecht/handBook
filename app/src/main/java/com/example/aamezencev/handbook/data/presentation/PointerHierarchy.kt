package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class PointerHierarchy(val startIndex: Int,
                       val finalIndex: Int,
                       val threeDimensionalModel: ThreeDimensionalModel) {
    @HierarchyDSL
    class Builder() {
        var startIndex: Int = 0
        var finalIndex: Int = 0
        lateinit var threeDimensionalModel: ThreeDimensionalModel

        fun build() = PointerHierarchy(startIndex, finalIndex, threeDimensionalModel)
    }
}