package com.example.aamezencev.handbook.presentation.log.screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.example.aamezencev.handbook.databinding.LogScreenFragmentBinding
import com.example.aamezencev.handbook.presentation.log.screen.LogScreenContract
import com.example.aamezencev.handbook.presentation.log.screen.di.LogScreenComponent
import com.example.aamezencev.handbook.presentation.log.screen.di.LogScreenModule

class LogScreenFragment : AbstractFragment<LogScreenContract.ViewModel, LogScreenContract.Presenter>() {

    private val LOG_ITEM_KEY = "log_item"

    companion object {
        fun instanceFragment(logItemModel: LogItemModel) = LogScreenFragment().apply {
            arguments = Bundle().apply {
                putParcelable(LOG_ITEM_KEY, logItemModel)
            }
        }
    }

    private lateinit var diComponent: LogScreenComponent
    private lateinit var binding: LogScreenFragmentBinding

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
            .addLogScreenSubmodule(LogScreenModule())
    }

    override fun createPresenter() = diComponent.getPresenter()

    override fun createViewModel() = diComponent.getViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.log_screen_fragment, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel!!.logItemModel = arguments?.getParcelable(LOG_ITEM_KEY)
        binding.viewModel = viewModel!!
    }
}