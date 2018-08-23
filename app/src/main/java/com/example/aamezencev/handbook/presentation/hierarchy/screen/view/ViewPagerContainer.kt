package com.example.aamezencev.handbook.presentation.hierarchy.screen.view

import android.databinding.Observable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.data.presentation.BookmarkInfo
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenComponent
import com.example.aamezencev.handbook.presentation.hierarchy.screen.di.HierarchyScreenModule
import com.example.aamezencev.handbook.presentation.hierarchy.screen.view.adapter.ScreenPagerAdapter
import com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel.HierarchyInfoVM
import com.example.aamezencev.handbook.ui.bookmarkLayout.BookmarkListener
import kotlinx.android.synthetic.main.pager_container_fragment.*

class ViewPagerContainer : AbstractFragment<HierarchyScreenContract.ViewModel, HierarchyScreenContract.Presenter>(), BookmarkListener {
    companion object {
        val DATA_ID = "DATA_ID"
        fun instanceFragment(dataId: Long) = ViewPagerContainer().apply {
            arguments = Bundle().apply {
                putLong(DATA_ID, dataId)
            }
        }
    }

    private lateinit var diComponent: HierarchyScreenComponent
    private var callbackList: MutableList<Observable.OnPropertyChangedCallback>? = arrayListOf()

    override fun injectDi() {
        diComponent = AppDelegate
                .presentationComponent!!
                .addHierarchyScreenSubmodule(HierarchyScreenModule())
    }

    override fun createPresenter(): HierarchyScreenContract.Presenter = diComponent.getPresnter()

    override fun createViewModel(): HierarchyScreenContract.ViewModel = diComponent.getViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pager_container_fragment, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        val dataId = arguments?.run { getLong(DATA_ID) } ?: -1
        if (viewModel == null || viewModel!!.pageList.isEmpty()) {
            presenter?.obtainDataElement(dataId)
        }

        if (pager.adapter == null) {
            pager.adapter = ScreenPagerAdapter(this.activityComponent).apply {
                pageList = viewModel!!.pageList
                listener = this@ViewPagerContainer
            }
        }

        viewModel!!.addOnPropertyChangedCallback(initSubscriptionViewModel())
    }

    override fun onStop() {
        callbackList?.forEach {
            viewModel!!.removeOnPropertyChangedCallback(it)
        }
        callbackList = null
        super.onStop()
    }

    override fun onAddedBookmark() {
        val position = pager.currentItem
        (pager.adapter as ScreenPagerAdapter).pageList[position].marked = true
        val dataId = arguments?.run { getLong(DATA_ID) } ?: -1
        presenter!!.addBookmark(dataId, position)
    }

    override fun onRemovedBookmark() {
        val position = pager.currentItem
        (pager.adapter as ScreenPagerAdapter).pageList[position].marked = false
        val dataId = arguments?.run { getLong(DATA_ID) } ?: -1
        presenter!!.removeBookmark(dataId, position)
    }

    private fun initSubscriptionViewModel(): Observable.OnPropertyChangedCallback {
        val subscriber = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (pager != null) {
                    (pager?.adapter as ScreenPagerAdapter).pageList = viewModel!!.pageList
                    pageIndicatorView.count = viewModel!!.pageList.size
                    pager?.adapter?.notifyDataSetChanged()
                }
            }
        }
        callbackList?.add(subscriber)
        return subscriber
    }
}