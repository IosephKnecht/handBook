package com.example.aamezencev.handbook.common.view

import android.support.v7.app.AppCompatActivity
import com.example.aamezencev.handbook.common.presenter.MvpPresenter
import com.example.aamezencev.handbook.common.viewModel.MvpViewModel

abstract class AbstractActivity<VM : MvpViewModel, Presenter : MvpPresenter<VM>>
    : AppCompatActivity(), AndroidComponent {

}