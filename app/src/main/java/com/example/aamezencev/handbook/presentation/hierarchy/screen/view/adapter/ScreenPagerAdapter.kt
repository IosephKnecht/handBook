package com.example.aamezencev.handbook.presentation.hierarchy.screen.view.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel.HierarchyInfoVM


class ScreenPagerAdapter(private val context: Context, private val viewModel: HierarchyInfoVM) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 10
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.pager_fragment, container, false) as ViewGroup
        val textView = view.findViewById<TextView>(R.id.pageText)
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.text = viewModel.description
        container.addView(view)
        return view
    }
}