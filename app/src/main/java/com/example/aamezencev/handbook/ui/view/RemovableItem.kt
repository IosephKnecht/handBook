package com.example.aamezencev.handbook.ui.view

import android.content.Context
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.aamezencev.handbook.R

class RemovableItem : FrameLayout {
    @IdRes
    private var foregroundViewRes: Int? = null
    @IdRes
    private var backgroundViewRes: Int? = null

    var foregroundView: View? = null
        private set
    var backgroundView: View? = null
        private set

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        context.obtainStyledAttributes(attrs, R.styleable.RemovableItem).apply {
            backgroundViewRes = getResourceId(R.styleable.RemovableItem_rmv_background, -1)
            foregroundViewRes = getResourceId(R.styleable.RemovableItem_rmv_foreground, -1)
            recycle()
        }

        backgroundView = initLayer(backgroundViewRes)
        foregroundView = initLayer(foregroundViewRes)

        this.addView(backgroundView)
        this.addView(foregroundView)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initLayer(value: Int?): View? {
        return value?.takeIf { it != -1 }?.run {
            LayoutInflater.from(context)
                .inflate(this, this@RemovableItem, false)
        }
    }
}