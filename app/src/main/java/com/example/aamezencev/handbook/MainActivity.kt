package com.example.aamezencev.handbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.presentation.list.view.fragment.HierarchyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.hierarchyContainer, HierarchyFragment())
                .commit()
    }
}
