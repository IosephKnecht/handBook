package com.example.aamezencev.handbook.presentation.bookmarks.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.databinding.BookmarksFragmentBinding
import com.example.aamezencev.handbook.presentation.bookmarks.BookmarksContract
import com.example.aamezencev.handbook.presentation.bookmarks.di.BookmarksComponent
import com.example.aamezencev.handbook.presentation.bookmarks.di.BookmarksModule
import com.example.aamezencev.handbook.presentation.bookmarks.view.adapter.BookmarksAdapter
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemContract
import com.example.aamezencev.handbook.ui.removableItem.helper.RemovableItemTouchHelper
import kotlinx.android.synthetic.main.bookmarks_fragment.*

class BookmarksFragment : AbstractFragment<BookmarksContract.ViewModel, BookmarksContract.Presenter>(),
    RemovableItemContract.RemovableItemListener {

    private lateinit var diComponent: BookmarksComponent
    private lateinit var binding: BookmarksFragmentBinding

    companion object {
        fun instanceFragment() = BookmarksFragment()
    }

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
            .addBookmarksModule(BookmarksModule())
    }

    override fun createPresenter() = diComponent.getPresenter()

    override fun createViewModel() = diComponent.getViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookmarks_fragment, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.viewModel = viewModel!!

        when (viewModel!!.state) {
            BookmarksContract.State.IDLE -> presenter!!.obtainBookmarks()
            else -> {
            }
        }

        bookmarks_view.apply {
            layoutManager = LinearLayoutManager(this@BookmarksFragment.context)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            RemovableItemTouchHelper(0, ItemTouchHelper.LEFT, this@BookmarksFragment).let {
                ItemTouchHelper(it).attachToRecyclerView(this)
            }

            adapter = BookmarksAdapter().apply {
                clickListener = {
                    presenter?.openBookmark(it)
                }
            }
        }
    }

    override fun onRemove(viewHolder: RemovableItemContract.RemovableViewHolder, direction: Int, position: Int) {
        with(bookmarks_view.adapter as BookmarksAdapter) {
            val bookmarkInfo = bookmarkList[position]
            presenter!!.removeBookmark(bookmarkInfo)
            removeItem(position)
            viewModel!!.reset()
        }
    }
}