package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment.HierarchyFragment

class MainActivity : AppCompatActivity() {

    private val INIT_FRAGMENT = "INIT_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val oldFragment = supportFragmentManager.findFragmentByTag(INIT_FRAGMENT)
        if (oldFragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(-1), INIT_FRAGMENT)
                    .commit()
        }
    }
}
