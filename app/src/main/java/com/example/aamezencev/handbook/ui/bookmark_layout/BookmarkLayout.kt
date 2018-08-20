package com.example.aamezencev.handbook.ui.bookmark_layout

import android.animation.ValueAnimator
import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.aamezencev.handbook.R


class BookmarkLayout @JvmOverloads constructor(context: Context,
                                               attributeSet: AttributeSet? = null,
                                               defStyle: Int = 0) : LinearLayout(context, attributeSet, defStyle), SwipeTouchListener {
    private var bookmarkImage: ImageView = ImageView(context)

    private val bottomSwipeAnimator: ValueAnimator

    private var bookmarkWidth = 0
    private var bookmarkHeight = 0
    private var bookmarkDrawable: Int? = null
    private var bookmarkVisible = false
        set(value) {
            field = value
            bookmarkImage.visibility = if (bookmarkVisible) View.VISIBLE else View.INVISIBLE
        }

    private val listener = OnSwipeTouchListener(context, this)

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.BookmarkLayout).apply {
            bookmarkWidth = getDimensionPixelSize(R.styleable.BookmarkLayout_bookmark_width, 0)
            bookmarkHeight = getDimensionPixelSize(R.styleable.BookmarkLayout_bookmark_height, 0)
            bookmarkDrawable = getResourceId(R.styleable.BookmarkLayout_bookmark_image, -1)
            bookmarkVisible = getBoolean(R.styleable.BookmarkLayout_bookmark_visible, true)
            recycle()
        }

        bottomSwipeAnimator = initAnimator(0f, 500f)
        initBookmarkImage(bookmarkDrawable!!, bookmarkWidth, bookmarkHeight)

        setOnTouchListener(listener)
    }

    private fun initBookmarkImage(@DrawableRes id: Int, width: Int, height: Int) {
        bookmarkImage.setImageDrawable(resources.getDrawable(id))

        val lp = LinearLayout.LayoutParams(width, height)
        lp.gravity = Gravity.CENTER
        lp.marginStart = this.width / 4
        bookmarkImage.layoutParams = lp

        addView(bookmarkImage)
    }

    private fun initAnimator(startY: Float, endY: Float): ValueAnimator {
        val animator = ValueAnimator.ofFloat(startY, endY)
        animator.interpolator = AccelerateInterpolator()
        animator.duration = 500
        animator.addUpdateListener {
            val progress = it.animatedValue as Float
            bookmarkImage.translationY = progress
        }
        return animator
    }

    override fun onSwipeRight() {
    }

    override fun onSwipeLeft() {
    }

    override fun onSwipeTop() {
//        if (bookmarkVisible) {
        bottomSwipeAnimator.reverse()
        //bookmarkVisible = !bookmarkVisible
//        }
    }

    override fun onSwipeBottom() {
//        if (!bookmarkVisible) {
        bottomSwipeAnimator.start()
        //bookmarkVisible = !bookmarkVisible
//        }
    }
}