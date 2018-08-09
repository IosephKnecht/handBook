package com.example.aamezencev.handbook.presentation.loader.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.aamezencev.handbook.presentation.loader.view.adapter.LoaderItemTouchHelper
import kotlinx.android.synthetic.main.loader_fragment.*

class LoaderFragment : AbstractFragment<LoaderContract.ViewModel, LoaderContract.Presenter>(), LoaderItemTouchHelper.RecyclerItemTouchHelperListener {
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
            adapter = LoaderAdapter().apply {
                clickListener = {
                    viewModel!!.state = LoaderContract.State.OPEN
                    presenter!!.obtainFilePath(it)
                }
            }
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@LoaderFragment.context, DividerItemDecoration.VERTICAL))

            LoaderItemTouchHelper(0, ItemTouchHelper.LEFT, this@LoaderFragment)
                .let {
                    ItemTouchHelper(it).attachToRecyclerView(this@apply)
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

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        with(database_info_view.adapter as LoaderAdapter) {
            presenter!!.deleteFilePath(this.databaseList[position])
            this.removeItem(position)
        }
    }
}