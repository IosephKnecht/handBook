package com.example.aamezencev.handbook

import com.example.aamezencev.handbook.data.presentation.hierarchyElementOf
import org.junit.Test

class ExampleHierarchyDsl {
    @Test
    fun main() {
        val hierarchy = hierarchyElementOf {
            name = ""
            parentId = 1
            hierarchyId = 1
            dataHierarchyId = 1
            dataHierarchyElement {
                hierarchyId = 1
                description = ""
                threeDimensionalModel {
                    thrModel {

                    }
                    thrModel {

                    }
                }
            }
        }
    }
}