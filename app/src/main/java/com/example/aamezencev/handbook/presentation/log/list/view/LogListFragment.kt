package com.example.aamezencev.handbook.presentation.log.list.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.databinding.LogListFragmentBinding
import com.example.aamezencev.handbook.presentation.log.list.LogListContract
import com.example.aamezencev.handbook.presentation.log.list.di.LogListComponent
import com.example.aamezencev.handbook.presentation.log.list.di.LogListModule
import com.example.aamezencev.handbook.presentation.log.list.view.adapter.LogListAdapter
import kotlinx.android.synthetic.main.log_list_fragment.*

class LogListFragment : AbstractFragment<LogListContract.ViewModel, LogListContract.Presenter>() {
    private lateinit var diComponent: LogListComponent
    private lateinit var binding: LogListFragmentBinding

    companion object {
        fun instanceFragment() = LogListFragment()
    }

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
            .addLogListSubmodule(LogListModule())
    }

    override fun createPresenter(): LogListContract.Presenter = diComponent.getPresenter()

    override fun createViewModel(): LogListContract.ViewModel = diComponent.getViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.log_list_fragment, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        binding.viewModel = viewModel!!

        if (viewModel!!.logList.isEmpty()) {
            presenter!!.obtainLog()
        }

        log_list.apply {
            layoutManager = LinearLayoutManager(this@LogListFragment.context)
            setHasFixedSize(true)
            adapter = LogListAdapter()
        }
    }
}