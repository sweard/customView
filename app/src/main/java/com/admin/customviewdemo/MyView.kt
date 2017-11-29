package com.admin.customviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by admin on 2017/11/24.
 */
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        mPaint.color = Color.YELLOW
        canvas?.drawRect(0f,0f, width.toFloat(), height.toFloat(),mPaint)
        mPaint.color = Color.BLUE
        mPaint.textSize = 20f
        val text = "Hello MyView"
        canvas?.drawText(text, 0f, (height/2).toFloat(),mPaint)
    }

}