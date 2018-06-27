package com.example.aamezencev.handbook.presentation.screen.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import kotlinx.android.synthetic.main.page_fragment.*

class PageFragment : Fragment() {
    companion object {
        val PAGE_TEXT = "PAGE_TEXT"
        fun instanceFragment(pageText: String) = PageFragment().apply {
            arguments = Bundle().apply {
                putString(PAGE_TEXT, pageText)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.page_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = arguments?.run { getString(PAGE_TEXT) } ?: "Error"
        pager.adapter = PagerAdapter(fragmentManager, text)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    private class PagerAdapter(fragmentManager: FragmentManager?,
                               private val pageText: String) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return PagerFragment.instanceFragment(position, pageText)
        }

        override fun getCount() = 10

    }
}