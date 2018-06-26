package com.example.aamezencev.handbook.presentation.list.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.data.db.DataHierarchyDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.ThreeDimensionalModelDb
import com.example.aamezencev.handbook.data.parcel.ParcelHierarchy
import com.example.aamezencev.handbook.data.presentation.HierarchyElement
import com.example.aamezencev.handbook.data.presentation.hierarchyElementOf
import com.example.aamezencev.handbook.databinding.HierarchyFragmentBinding
import com.example.aamezencev.handbook.domain.FakeService
import com.example.aamezencev.handbook.presentation.list.HierarchyListContract
import com.example.aamezencev.handbook.presentation.list.di.HierarchyListComponent
import com.example.aamezencev.handbook.presentation.list.di.HierarchyListModule
import com.example.aamezencev.handbook.presentation.list.router.HierarchyRouter
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyElementVM
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
    private lateinit var router: HierarchyRouter

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
        router = HierarchyRouter(this.activity)
        hierarchyAdapter = HierarchyAdapter(router)

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
        when (item?.itemId) {
            R.id.initDb -> {
                presenter?.addHierarchyListElement(FakeService.buildFakeHierarchy())
            }
        }
        return false
    }

    private fun initParentId(parentId: Long) {
        if (parentId < 0) this.parentId = null else this.parentId = parentId
    }
}