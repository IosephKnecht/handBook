package com.example.aamezencev.handbook.presentation.hierarchy.screen.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.presentation.Page
import com.example.aamezencev.handbook.databinding.PagerFragmentBinding
import com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel.HierarchyInfoVM
import com.example.aamezencev.handbook.ui.bookmarkLayout.BookmarkLayout
import com.example.aamezencev.handbook.ui.bookmarkLayout.BookmarkListener


class ScreenPagerAdapter(private val context: Context) : PagerAdapter() {
    var listener: BookmarkListener? = null
    var pageList = listOf<Page>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return pageList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<PagerFragmentBinding>(inflater, R.layout.pager_fragment, container, false)
        binding.page = pageList[position]

        (binding.root as BookmarkLayout).apply {
            bookmarkListener = this@ScreenPagerAdapter.listener
            establishVisibleBookmark(pageList[position].marked)
        }

        binding.pageText.movementMethod = LinkMovementMethod.getInstance()
        container.addView(binding.root)
        return binding.root
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}