package com.example.aamezencev.handbook.domain

import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.hierarchyElementOf

object FakeService {
    fun buildFakeHierarchy(): List<HierarchyElement> {
        val e1 = hierarchyElementOf {
            name = "Chapter 1"
        }

        val e2 = hierarchyElementOf {
            name = "Page 1"
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
        }

        val e4 = hierarchyElementOf {
            name = "Page 2"
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