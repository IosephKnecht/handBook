package com.example.aamezencev.handbook.presentation.list.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.parcel.ParcelHierarchy
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.databinding.HierarchyFragmentBinding
import com.example.aamezencev.handbook.presentation.list.router.HierarchyRouter
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM
import kotlinx.android.synthetic.main.hierarchy_fragment.*

class HierarchyFragment : Fragment() {
    companion object {
        fun instanceFragment(hiererchy: ParcelHierarchy) = HierarchyFragment().apply {
            arguments = Bundle().apply {
                putParcelable("HIERARCHY", hiererchy)
            }
        }
    }

    private lateinit var hierarchyAdapter: HierarchyAdapter
    private lateinit var binding: HierarchyFragmentBinding
    private lateinit var hierarchy: ParcelHierarchy
    private lateinit var router: HierarchyRouter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hierarchy_fragment, container, false)
        binding.viewModel = HierarchyElementVM()
        val view = binding.root
        hierarchy = arguments?.getParcelable("HIERARCHY") as ParcelHierarchy
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val block: ParcelHierarchy
        router = HierarchyRouter(this.activity)
        hierarchyAdapter = HierarchyAdapter(router)

        hierarchyView.apply {
            layoutManager = LinearLayoutManager(this@HierarchyFragment.context)
            setHasFixedSize(true)
            adapter = hierarchyAdapter
        }

        binding.viewModel?.childList = hierarchy.childList
                .map { it as IHierarchy }
                .toMutableList()
        binding.viewModel?.childList!![0].name

        //binding.viewModel.hierarchyList = list.toList()
    }
}