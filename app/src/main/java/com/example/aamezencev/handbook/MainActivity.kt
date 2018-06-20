package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.data.presentation.elementOf
import com.example.aamezencev.handbook.domain.HierarchyElementMapper

class MainActivity : AppCompatActivity() {

    private val testHierarchy = elementOf {
        name = "Chapter 1"
        childs {
            child {
                name = "Chapter 2"
                childs {
                    child {
                        name = "Page 1"
                        text = "it's is very intresting page"
                    }
                }
            }
            child {
                name = "Page 2"
                text = "it's is very very intresting page"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(HierarchyElementMapper.fromParcelable(testHierarchy)))
                .commit()
    }
}
