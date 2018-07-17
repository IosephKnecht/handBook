package com.example.aamezencev.handbook.presentation.hierarchy.list.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.databinding.HierarchyFragmentBinding
import com.example.aamezencev.handbook.presentation.hierarchy.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListComponent
import com.example.aamezencev.handbook.presentation.hierarchy.list.di.HierarchyListModule
import com.example.aamezencev.handbook.presentation.hierarchy.list.router.HierarchyRouter
import com.example.aamezencev.handbook.presentation.hierarchy.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.hierarchy.list.viewModel.HierarchyElementVM
import kotlinx.android.synthetic.main.hierarchy_fragment.*

class HierarchyFragment : AbstractFragment<HierarchyListContract.ViewModel, HierarchyListContract.Presenter>() {
    companion object {
        private val PARENT_ID = "PARENT_ID"
        fun instanceFragment(parentId: Long) = HierarchyFragment().apply {
            arguments = Bundle().apply {
                putLong(PARENT_ID, parentId)
            }
        }
    }

    private var diComponent: HierarchyListComponent? = null

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent
                ?.addHierarchyListSubmodule(HierarchyListModule())
    }

    override fun createPresenter(): HierarchyListContract.Presenter {
        return diComponent?.getPresenter() as HierarchyListContract.Presenter
    }

    override fun createViewModel(): HierarchyListContract.ViewModel {
        return diComponent?.getHierarchyListViewModel() as HierarchyListContract.ViewModel
    }

    private lateinit var hierarchyAdapter: HierarchyAdapter
    private lateinit var binding: HierarchyFragmentBinding
    private var parentId: Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.hierarchy_fragment, container, false)
        initParentId(arguments!!.getLong(PARENT_ID))

        binding.viewModel = viewModel!! as HierarchyElementVM
        val view = binding.root
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hierarchyAdapter = HierarchyAdapter().apply {
            itemClickChapter = { diComponent?.getHierarchyListRouter()?.showChapter(this@HierarchyFragment, it) }
            itemClickPage = { diComponent?.getHierarchyListRouter()?.showPage(this@HierarchyFragment, it) }
        }

        hierarchyView.apply {
            layoutManager = LinearLayoutManager(this@HierarchyFragment.context)
            setHasFixedSize(true)
            adapter = hierarchyAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        presenter?.obtainHieararchy(parentId)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.hierarchy_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return false
    }

    private fun initParentId(parentId: Long) {
        if (parentId < 0) this.parentId = null else this.parentId = parentId
    }
}