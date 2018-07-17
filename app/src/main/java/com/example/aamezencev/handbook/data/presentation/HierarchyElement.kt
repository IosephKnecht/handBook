package com.example.aamezencev.handbook.data.presentation

import com.example.aamezencev.handbook.data.HierarchyDSL

class HierarchyElement(val id: Long,
                       val parentId: Long?,
                       val name: String,
                       val dataElement: DataElement?) {
    @HierarchyDSL
    class Builder {
        var id: Long = -1L
        var parentId = -1L
        var name: String = ""
        private var dataElement: DataElement? = null

        fun data(block: DataElement.Builder.() -> Unit) {
            dataElement = DataElement.Builder().apply(block).build()
        }

        fun build() = HierarchyElement(id, parentId, name, dataElement)
    }
}

fun hierarchyElementOf(block: HierarchyElement.Builder.() -> Unit) = HierarchyElement.Builder().apply(block).build()