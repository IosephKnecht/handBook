package com.example.aamezencev.handbook.common.presenter

import com.example.aamezencev.handbook.common.view.AndroidComponent
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface MvpPresenter<VM : MvpViewModel> {
    fun attachView(viewModel: VM, androidComponent: AndroidComponent)
    fun detachView()
    fun destroy()
    val viewModel: VM
}