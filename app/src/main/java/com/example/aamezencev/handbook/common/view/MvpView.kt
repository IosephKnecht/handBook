package com.example.aamezencev.handbook.common.view

import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

interface MvpView<VM : MvpViewModel> : AndroidComponent {
    val viewModel: VM
}