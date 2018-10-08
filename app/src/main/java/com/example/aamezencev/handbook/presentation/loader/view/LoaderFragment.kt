package com.example.aamezencev.handbook.presentation.loader.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.application.Constants.DATABASE_READ_REQUEST_CODE
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.databinding.LoaderFragmentBinding
import com.example.aamezencev.handbook.presentation.loader.LoaderContract
import com.example.aamezencev.handbook.presentation.loader.LoaderInputModule
import com.example.aamezencev.handbook.presentation.loader.di.LoaderComponent
import com.example.aamezencev.handbook.presentation.loader.di.LoaderModule
import com.example.aamezencev.handbook.presentation.loader.presenter.LoaderPresenter
import com.example.aamezencev.handbook.presentation.loader.view.adapter.LoaderAdapter
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemContract
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemTouchHelper
import kotlinx.android.synthetic.main.loader_fragment.*

class LoaderFragment : AbstractFragment<LoaderContract.ViewModel, LoaderContract.Presenter>(), RemovableItemContract.RemovableItemListener {
    private var diComponent: LoaderComponent? = null
    private lateinit var binding: LoaderFragmentBinding

    companion object {
        fun instanceFragment() = LoaderFragment()
    }

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
            .addLoaderSubmodule(LoaderModule())
    }

    override fun createPresenter(): LoaderContract.Presenter {
        return diComponent!!.getPresenter()
    }

    override fun createViewModel(): LoaderContract.ViewModel {
        return diComponent!!.getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.loader_fragment, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.viewModel = viewModel!!

        database_info_view.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            RemovableItemTouchHelper(0, ItemTouchHelper.LEFT, this@LoaderFragment).let {
                ItemTouchHelper(it).attachToRecyclerView(this)
            }

            adapter = LoaderAdapter().apply {
                clickListener = {
                    viewModel!!.state = LoaderContract.State.OPEN
                    presenter!!.obtainFilePath(it)
                }
            }
        }

        fab_database.setOnClickListener {
            startActivityForResult(LoaderInputModule().createOpenFileIntent(), DATABASE_READ_REQUEST_CODE)
        }

        viewModel!!.loadableUri?.apply {
            viewModel!!.state = LoaderContract.State.LOAD
            presenter!!.obtainFilePath(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DATABASE_READ_REQUEST_CODE) {
            viewModel!!.loadableUri = data?.data
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.bookmark_item -> {
                diComponent?.getRouter()?.showBookmarksFragment(this)
            }
            else -> {
            }
        }
        return false
    }

    override fun onRemove(viewHolder: RemovableItemContract.RemovableViewHolder, direction: Int, position: Int) {
        with((database_info_view.adapter as LoaderAdapter)) {
            val databaseInfo = databaseList[position]
            removeItem(position)
            presenter!!.deleteFilePath(databaseInfo)
        }
    }
}