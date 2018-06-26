package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class HierarchyElement(val hierarchyId: Long,
                       val parentId: Long?,
                       val name: String,
                       val dataHierarchyId: Long?,
                       val dataHierarchyElement: DataHierarchyElement?) {
    @HierarchyDSL
    class Builder {
        var hierarchyId: Long = -1
        var parentId: Long? = null
        var name: String = ""
        var dataHierarchyId: Long? = null
        private var dataHierarchyElement: DataHierarchyElement? = null

        fun dataHierarchyElement(block: DataHierarchyElement.Builder.() -> Unit) {
            dataHierarchyElement = DataHierarchyElement.Builder().apply(block).build()
        }

        fun build() = HierarchyElement(hierarchyId, parentId, name, dataHierarchyId, dataHierarchyElement)
    }
}

fun hierarchyElementOf(block: HierarchyElement.Builder.() -> Unit) = HierarchyElement.Builder().apply(block).build()