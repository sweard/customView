package com.admin.customviewdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by admin on 2018/1/4
 */
class BubbleChart : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)
    }


    private fun initView() {

    }


    private fun cont():Int {

        val num = 5
        val binStr = Integer.toBinaryString(num)
        val result: StringBuilder = StringBuilder()
        for (item in binStr) {
            if (item.toInt() == 0) {
                result.append(1)
            } else {
                result.append(0)
            }
        }


        return num

    }

}