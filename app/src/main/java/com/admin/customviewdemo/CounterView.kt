package com.admin.customviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * Created by admin on 2017/11/23.
 * 点击计数控件
 */
class CounterView(context: Context, attrs: AttributeSet) : View(context, attrs), View.OnClickListener, View.OnLongClickListener {

    override fun onClick(v: View?) {
        mCount++
        invalidate()
    }

    override fun onLongClick(v: View?): Boolean {
        mCount = 0
        invalidate()
        return true
    }

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBounds: Rect = Rect()
    private var mCount: Int = 0

    init {
        setOnClickListener(this)
        setOnLongClickListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = Color.RED
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint)

        mPaint.color = Color.GRAY
        mPaint.textSize = 36f
        val text = mCount.toString()
        mPaint.getTextBounds(text, 0, text.length, mBounds)
        val textWidth = mBounds.width()
        val textHeight = mBounds.height()
        canvas?.drawText(text, (width / 2 - textWidth / 2).toFloat(), (height / 2 + textHeight / 2).toFloat(), mPaint)
    }


}