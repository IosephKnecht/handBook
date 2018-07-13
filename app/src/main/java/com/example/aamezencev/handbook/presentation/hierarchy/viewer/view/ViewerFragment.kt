package com.example.aamezencev.handbook.presentation.hierarchy.viewer.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.databinding.ViewerLayoutBinding
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.ViewerContract
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerComponent
import com.example.aamezencev.handbook.presentation.hierarchy.viewer.di.ViewerModule
import kotlinx.android.synthetic.main.viewer_layout.*

class ViewerFragment : AbstractFragment<ViewerContract.ViewModel, ViewerContract.Presenter>() {

    private val THR_MODEL_ID = "model_id"

    private var diComponent: ViewerComponent? = null
    private lateinit var binding: ViewerLayoutBinding

    companion object {
        fun instanceFragment(thrModelId: Long) = ViewerFragment().apply {
            arguments = Bundle().apply {
                putLong(THR_MODEL_ID, thrModelId)
            }
        }
    }

    override fun injectDi() {
        diComponent = AppDelegate.presentationComponent!!
                .addViewerModule(ViewerModule(activityComponent))
    }

    override fun createPresenter(): ViewerContract.Presenter {
        return diComponent!!.getPresenter()
    }

    override fun createViewModel(): ViewerContract.ViewModel {
        return diComponent!!.getViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.viewer_layout, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.viewModel = viewModel
        binding.facade = diComponent!!.getViewer().getFacade()
        val thrModelId = arguments!!.getLong(THR_MODEL_ID)
        presenter!!.obtainThrModel(thrModelId)
    }
}