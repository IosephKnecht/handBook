package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.hierarchyElementOf

object FakeService {
    fun buildFakeHierarchy(): List<HierarchyElement> {
        val e1 = hierarchyElementOf {
            name = "Chapter 1"
            hierarchyId = 1
            parentId = null
        }

        val e2 = hierarchyElementOf {
            name = "Page 1"
            parentId = 1
            hierarchyId = 2
            dataHierarchyElement {
                description = "very interesting text"
                threeDimensionalModel {
                    thrModel { modelArray = byteArrayOf(1, 2, 3) }
                    thrModel { modelArray = byteArrayOf(3, 4, 5) }
                }
            }
        }

        val e3 = hierarchyElementOf {
            name = "Chapter 2"
            hierarchyId = 3
            parentId = null
        }

        val e4 = hierarchyElementOf {
            name = "Page 2"
            hierarchyId = 4
            parentId = 2
            dataHierarchyElement {
                description = "very very interesting text"
                threeDimensionalModel {
                    thrModel { modelArray = byteArrayOf(1, 2, 3) }
                    thrModel { modelArray = byteArrayOf(3, 4, 5) }
                }
            }
        }

        return listOf(e1, e2, e3, e4)
    }
}