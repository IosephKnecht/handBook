package com.example.aamezencev.handbook.presentation.screen.view

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.screen.di.HierarchyScreenComponent
import com.example.aamezencev.handbook.presentation.screen.di.HierarchyScreenModule
import com.example.aamezencev.handbook.presentation.screen.view.adapter.PagerAdapter
import com.example.aamezencev.handbook.presentation.screen.viewModel.HierarchyInfoVM
import kotlinx.android.synthetic.main.page_fragment.*

class ViewPagerContainer : AbstractFragment<HierarchyScreenContract.ViewModel, HierarchyScreenContract.Presenter>() {
    companion object {
        val DATA_ID = "DATA_ID"
        fun instanceFragment(dataId: Long) = ViewPagerContainer().apply {
            arguments = Bundle().apply {
                putLong(DATA_ID, dataId)
            }
        }
    }

    private lateinit var diComponent: HierarchyScreenComponent

    override fun injectDi() {
        diComponent = AppDelegate
                .presentationComponent!!
                .addHierarchyScreenSubmodule(HierarchyScreenModule())
    }

    override fun createPresenter(): HierarchyScreenContract.Presenter = diComponent.getPresnter()

    override fun createViewModel(): HierarchyScreenContract.ViewModel = diComponent.getViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.page_fragment, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        val dataId = arguments?.run { getLong(DATA_ID) } ?: -1
        presenter?.obtainDataElement(dataId)
        if (pager.adapter == null) pager.adapter = PagerAdapter(fragmentManager, viewModel!! as HierarchyInfoVM)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }
        })
    }
}