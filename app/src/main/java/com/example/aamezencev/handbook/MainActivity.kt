package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.data.presentation.DataHierarchyElement
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.data.presentation.hierarchyElementOf
import com.example.aamezencev.handbook.domain.HierarchyElementMapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kek = hierarchyElementOf {
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

    override fun onStart() {
        super.onStart()
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment())
//                .commit()
    }
}
