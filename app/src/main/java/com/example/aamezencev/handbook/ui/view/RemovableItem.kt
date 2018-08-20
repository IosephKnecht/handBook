package com.example.aamezencev.handbook.ui.view

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.aamezencev.handbook.R

class RemovableItem : FrameLayout {
    var foregroundView: View? = null
        private set
    var backgroundView: View? = null
        private set

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        var background = -1
        var foreground = -1

        context.obtainStyledAttributes(attrs, R.styleable.RemovableItem).apply {
            background = getResourceId(R.styleable.RemovableItem_rmv_background, -1)
            foreground = getResourceId(R.styleable.RemovableItem_rmv_foreground, -1)
            recycle()
        }

        addLayers(background, foreground)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, @LayoutRes background: Int, @LayoutRes foreground: Int) : super(context) {
        addLayers(background, foreground)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val foregroundHeight = foregroundView?.measuredHeight
        val backgroundHeight = backgroundView?.measuredHeight
        if (foregroundHeight != null && backgroundHeight != null)
            setMeasuredDimension(measuredWidth, Math.min(foregroundHeight, backgroundHeight))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        foregroundView?.apply {
            backgroundView?.layout(this.left, this.top, this.right, this.bottom)
        }
    }

    private fun addLayers(background: Int, foreground: Int) {
        backgroundView = initLayer(background)
        foregroundView = initLayer(foreground)

        this.addView(backgroundView)
        this.addView(foregroundView)
    }

    private fun initLayer(value: Int?): View? {
        return value?.takeIf { it != -1 }?.run {
            LayoutInflater.from(context)
                .inflate(this, this@RemovableItem, false)
        }
    }
}