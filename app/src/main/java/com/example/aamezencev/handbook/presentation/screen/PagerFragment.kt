package com.example.aamezencev.handbook.presentation.screen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import kotlinx.android.synthetic.main.pager_fragment.*

class PagerFragment : Fragment() {
    companion object {
        private val PAGE_NUMBER = "CURRENT_PAGE"
        private val PAGE_TEXT = "TEXT"
        fun instanceFragment(pageNumber: Int, textPage: String) = PagerFragment().apply {
            arguments = Bundle().apply {
                putInt(PAGE_NUMBER, pageNumber)
                putString(PAGE_TEXT, textPage)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pager_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pageText.text = arguments?.run { getString(PAGE_TEXT) } ?: "Error"
    }
}