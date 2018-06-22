package com.example.aamezencev.handbook.common.view

import android.app.Activity
import android.support.v4.app.FragmentManager

interface AndroidComponent {
    val activityComponent: Activity
    val fragmentManagerComponent: FragmentManager
}