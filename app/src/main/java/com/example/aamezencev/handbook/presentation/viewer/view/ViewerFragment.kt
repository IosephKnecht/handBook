package com.example.aamezencev.handbook.presentation.viewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.application.AppDelegate
import com.example.aamezencev.handbook.common.view.AbstractFragment
import com.example.aamezencev.handbook.presentation.viewer.ViewerContract
import com.example.aamezencev.handbook.presentation.viewer.di.ViewerComponent
import com.example.aamezencev.handbook.presentation.viewer.di.ViewerModule
import kotlinx.android.synthetic.main.viewer_layout.*

class ViewerFragment : AbstractFragment<ViewerContract.ViewModel, ViewerContract.Presenter>() {

    private val MODEL_ARRAY = "model"

    companion object {
        fun instanceFragment(model: ByteArray) = ViewerFragment().apply {
            arguments = Bundle().apply {
                putByteArray(MODEL_ARRAY, model)
            }
        }
    }

    private var diComponent: ViewerComponent? = null

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
        return inflater.inflate(R.layout.viewer_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        val facade = diComponent!!.getViewer().getFacade()
        val modelArray = arguments!!.getByteArray(MODEL_ARRAY)
        val model = facade.buildModel(modelArray.inputStream())
        model_container.addView(facade.buildSurfaceView(model, facade.buildFloor(), facade.buildLight()))
    }
}