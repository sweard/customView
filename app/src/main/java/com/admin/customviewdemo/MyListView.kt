package com.admin.customviewdemo

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.ListView
import android.widget.RelativeLayout

/**
 * Created by admin on 2017/11/27.
 * 自定义View,增加删除item按钮
 */
class MyListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs), GestureDetector.OnGestureListener, View.OnTouchListener {

    private var selectItem: Int = 0
    private var itemLayout: ViewGroup? = null
    private var isDeleteShow: Boolean = false
    private var deleteButton: View? = null
    private var listener: OnDeleteListener? = null
    private val gestureDetector: GestureDetector = GestureDetector(context, this)

    init {
        setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return if (isDeleteShow) {
            itemLayout?.removeView(deleteButton)
            isDeleteShow = false
            deleteButton = null
            false
        } else {
            selectItem = pointToPosition(event.x.toInt(), event.y.toInt())
            gestureDetector.onTouchEvent(event)
        }
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean = false

    override fun onDown(e: MotionEvent): Boolean {

//                selectItem = pointToPosition(e.x.toInt(), e.y.toInt())

        return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (!isDeleteShow && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteButton = LayoutInflater.from(context).inflate(R.layout.delete_button, null)
            deleteButton?.setOnClickListener {
                itemLayout?.removeView(deleteButton)
                deleteButton = null
                isDeleteShow = false
                listener?.onDelete(selectItem)
            }
            itemLayout = getChildAt(selectItem - firstVisiblePosition) as ViewGroup?
            val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            params.addRule(RelativeLayout.CENTER_VERTICAL)
            itemLayout?.addView(deleteButton, params)
            isDeleteShow = true
        }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean =
            false

    override fun onLongPress(e: MotionEvent?) {
    }


     fun setOnDeleteListener(l: OnDeleteListener) {
        listener = l
    }

     interface OnDeleteListener {
        fun onDelete(position: Int)
    }


}