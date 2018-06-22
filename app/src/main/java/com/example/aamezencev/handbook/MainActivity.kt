package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.data.presentation.Chapter
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment
import com.example.aamezencev.handbook.data.presentation.elementOf
import com.example.aamezencev.handbook.domain.HierarchyElementMapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.hierarchyContainer, HierarchyFragment.instanceFragment(HierarchyElementMapper.fromParcelable(elementOf {  })))
                .commit()
    }
}
