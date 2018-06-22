package com.example.aamezencev.handbook.common.view

import android.support.v4.app.Fragment
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractFragment<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : Fragment(), AndroidComponent {

}