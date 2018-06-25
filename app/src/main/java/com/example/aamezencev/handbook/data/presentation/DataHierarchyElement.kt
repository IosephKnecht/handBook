package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class DataHierarchyElement(val hierarchyId: Long,
                           val description: String,
                           val threeDimensionalModels: List<ThreeDimensionalModel>) {
    @HierarchyDSL
    class Builder {
        var hierarchyId: Long = -1
        var description = ""
        private var threeDimensionalModel = arrayListOf<ThreeDimensionalModel>()

        fun threeDimensionalModel(block: ThreeDimensionalModelList.() -> Unit) {
            threeDimensionalModel.addAll(ThreeDimensionalModelList().apply(block))
        }

        fun build() = DataHierarchyElement(hierarchyId, description, threeDimensionalModel)
    }

    class ThreeDimensionalModelList : ArrayList<ThreeDimensionalModel>() {
        fun thrModel(block: ThreeDimensionalModel.Builder.() -> Unit) {
            add(ThreeDimensionalModel.Builder().apply(block).build())
        }
    }
}