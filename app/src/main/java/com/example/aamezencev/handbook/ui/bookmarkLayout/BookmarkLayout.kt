package com.example.aamezencev.handbook.ui.bookmarkLayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.aamezencev.handbook.R


class BookmarkLayout @JvmOverloads constructor(context: Context,
                                               attributeSet: AttributeSet? = null,
                                               defStyle: Int = 0) :
        RelativeLayout(context, attributeSet, defStyle), SwipeTouchListener {
    private var bookmarkImage: ImageView = ImageView(context)

    private var bookmarkWidth = 0
    private var bookmarkHeight = 0
    private var bookmarkDrawable: Int? = null
    private var bookmarkVisible = false
    private var bookmarkColor = -1
    private var swiped = false

    private val swipeListener = OnSwipeTouchListener(context, this)
    var bookmarkListener: BookmarkListener? = null

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.BookmarkLayout).apply {
            bookmarkWidth = getDimensionPixelSize(R.styleable.BookmarkLayout_bookmark_width, 0)
            bookmarkHeight = getDimensionPixelSize(R.styleable.BookmarkLayout_bookmark_height, 0)
            bookmarkDrawable = getResourceId(R.styleable.BookmarkLayout_bookmark_image, -1)
            bookmarkVisible = getBoolean(R.styleable.BookmarkLayout_bookmark_visible, false)
            bookmarkColor = getColor(R.styleable.BookmarkLayout_bookmark_color, resources.getColor(android.R.color.black))
            recycle()
        }

        swiped = bookmarkVisible

        addView(bookmarkImage)
        setOnTouchListener(swipeListener)
    }

    fun establishVisibleBookmark(visible: Boolean) {
        bookmarkVisible = visible
        bookmarkImage.translationY = if (bookmarkVisible) bookmarkHeight.toFloat() else 0f
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        val count = childCount - 1
        for (i in 0..count) {
            val view = getChildAt(i)
            if (view != bookmarkImage) initAloneChilds(view)
        }

        initBookmarkImage()
    }

    private fun initAloneChilds(view: View) {
        if (view.top < bookmarkHeight) {
            view.let {
                val childBottom =
                        if (it.bottom < bookmarkHeight) it.measuredHeight + bookmarkHeight else it.bottom
                it.layout(it.left, bookmarkHeight, it.right, childBottom)
            }
        }
    }

    private fun initBookmarkImage() {
        if (bookmarkDrawable != null && bookmarkDrawable != -1)
            bookmarkImage.setImageDrawable(resources.getDrawable(bookmarkDrawable!!))

        bookmarkImage.setColorFilter(bookmarkColor)
        establishVisibleBookmark(bookmarkVisible)

        val bkmLeft = (measuredWidth * 0.75).toInt() - bookmarkWidth
        val bkmTop = bookmarkHeight * -1
        val bkmRight = bkmLeft + bookmarkWidth
        val bkmBottom = 1

        bookmarkImage.layout(bkmLeft, bkmTop, bkmRight, bkmBottom)
    }

    override fun onSwipeTop() {
        if (swiped) bookmarkImage.animate()
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .alpha(0f)
                .translationY(0f)
                .withEndAction {
                    swiped = !swiped
                    bookmarkListener?.onRemovedBookmark()
                }
    }

    override fun onSwipeBottom() {
        if (!swiped) bookmarkImage.animate()
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .alpha(1f)
                .translationY(bookmarkHeight.toFloat())
                .withEndAction {
                    swiped = !swiped
                    bookmarkListener?.onAddedBookmark()
                }
    }


    private class OnSwipeTouchListener(ctx: Context,
                                       private val listener: SwipeTouchListener) : OnTouchListener {

        companion object {
            private const val SWIPE_THRESHOLD = 150
            private const val SWIPE_VELOCITY_THRESHOLD = 100
        }

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) < Math.abs(diffY) && Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(
                                    velocityY) > SWIPE_VELOCITY_THRESHOLD
                    ) {
                        if (diffY > 0) {
                            listener.onSwipeBottom()
                        } else {
                            listener.onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }
        }
    }
}