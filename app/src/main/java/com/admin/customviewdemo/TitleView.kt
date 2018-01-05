package com.admin.customviewdemo

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView

/**
 * Created by admin on 2017/11/24.
 * 自定义标题View
 */
class TitleView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val mTitle: TextView
    private val mBack: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.title_layout, this)
        mTitle = findViewById(R.id.title_text)
        mBack = findViewById(R.id.button_left)
        mBack.setOnClickListener({
            (getContext() as Activity).finish()
        })
    }

    fun setTitleText(title: String) {
        mTitle.text = title
    }

    fun setBackText(text: String) {
        mBack.text = text
    }

    fun setBackButtonClickListener(listener: OnClickListener) {
        mBack.setOnClickListener(listener)
    }
}