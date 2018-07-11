package com.example.aamezencev.handbook.presentation.hierarchy.screen.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.databinding.PagerFragmentBinding
import com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel.HierarchyInfoVM

class PagerFragment : Fragment() {
    companion object {
        private val PAGE_NUMBER = "CURRENT_PAGE"
        private val PAGE_MODEL = "VIEW_MODEL"
        fun instanceFragment(pageNumber: Int, viewModel: Parcelable) = PagerFragment().apply {
            arguments = Bundle().apply {
                putInt(PAGE_NUMBER, pageNumber)
                putParcelable(PAGE_MODEL, viewModel)
            }
        }
    }

    private lateinit var binding: PagerFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pager_fragment, container, false)
        binding.viewModel = arguments?.run { getParcelable<HierarchyInfoVM>(PAGE_MODEL) }
        return binding.root
    }
}