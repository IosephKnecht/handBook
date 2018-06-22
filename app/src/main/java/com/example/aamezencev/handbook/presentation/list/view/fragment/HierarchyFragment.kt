package com.example.aamezencev.handbook.presentation.list.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.data.parcel.ParcelHierarchy
import com.example.aamezencev.handbook.data.presentation.IHierarchy
import com.example.aamezencev.handbook.databinding.HierarchyFragmentBinding
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.list.presenter.HierarchyListPresenter
import com.example.aamezencev.handbook.presentation.list.router.HierarchyRouter
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM
import kotlinx.android.synthetic.main.hierarchy_fragment.*

class HierarchyFragment : AbstractFragment<HierarchyListContract.ViewModel, HierarchyListContract.Presenter>() {
    companion object {
        fun instanceFragment(hiererchy: ParcelHierarchy) = HierarchyFragment().apply {
            arguments = Bundle().apply {
                putParcelable("HIERARCHY", hiererchy)
            }
        }
    }

    override fun injectDi() {
//        viewModel = HierarchyElementVM()
//        presenter = HierarchyListPresenter(viewModel!!)
    }

    override fun createPresenter(): HierarchyListContract.Presenter {
        viewModel = HierarchyElementVM()
        return HierarchyListPresenter(viewModel!!)
    }

    override fun createViewModel(): HierarchyListContract.ViewModel {
        return viewModel!!
    }

    private lateinit var hierarchyAdapter: HierarchyAdapter
    private lateinit var binding: HierarchyFragmentBinding
    private lateinit var hierarchy: ParcelHierarchy
    private lateinit var router: HierarchyRouter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hierarchy_fragment, container, false)
        hierarchy = arguments?.getParcelable("HIERARCHY") as ParcelHierarchy

//        viewModel!!.text = hierarchy.text
//        viewModel!!.childList = hierarchy.childList.toMutableList()
//        viewModel!!.name = hierarchy.name

        binding.viewModel = viewModel!! as HierarchyElementVM
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = HierarchyRouter(this.activity)
        hierarchyAdapter = HierarchyAdapter(router)

        hierarchyView.apply {
            layoutManager = LinearLayoutManager(this@HierarchyFragment.context)
            setHasFixedSize(true)
            adapter = hierarchyAdapter
        }
//        binding.viewModel?.childList = hierarchy.childList
//                .map { it }
//                .toMutableList()
        presenter?.obtainHieararchy()
    }
}