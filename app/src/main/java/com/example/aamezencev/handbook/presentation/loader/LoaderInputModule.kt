package com.example.aamezencev.handbook.presentation.loader

import android.content.Intent
import javax.inject.Inject

class LoaderInputModule @Inject constructor() : LoaderContract.InputModule {
    override fun createOpenFileIntent() = Intent(Intent.ACTION_GET_CONTENT).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        type = "*/*"
    }
}