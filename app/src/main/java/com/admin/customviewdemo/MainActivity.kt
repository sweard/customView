package com.admin.customviewdemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private val listContent = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTitle.setTitleText("Kick Your Ass")
        myTitle.setBackText("Close?")
        myTitle.setBackButtonClickListener(View.OnClickListener {
            Toast.makeText(this, "Close unabled now!", Toast.LENGTH_SHORT).show()
        })

        cont(5)

        Log.d("result", convertToTitle(765))

        val nums = IntArray(7)
        val r = Random()
        for (i in 0..6) {
            nums[i] = r.nextInt(99) + 1
        }
        for (num in nums) {
            Log.d("num", num.toString())
        }
        findMaxArray(nums, 2)

        //生成list数据
        (1..20).mapTo(listContent) { it.toString() }
        myList.setOnDeleteListener(object : MyListView.OnDeleteListener {
            override fun onDelete(position: Int) {
                Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT).show()
                listContent.removeAt(position)
                adapter.notifyDataSetChanged()
            }
        })
        adapter = MyAdapter(this, 0, listContent)
        myList.adapter = adapter
    }


    private fun cont(num: Int): Int {
        val binStr = Integer.toBinaryString(num)
        val result = StringBuilder()
        Log.d("binStr:", binStr)
        for (item in binStr) {
            val itemNum = Integer.parseInt(item.toString())
            if (itemNum == 0) {
                result.append(1)
            } else {
                result.append(0)
            }
        }

        Log.d("antBinStr:", result.toString())

        var resultInt = 0
        var count = result.toString().length - 1
        for (item in result.toString()) {
            val itemNum = Integer.parseInt(item.toString())
            Log.d("cal", itemNum.toString() + "乘以" + Math.pow(2.0, count.toDouble()).toInt())
            resultInt += itemNum * Math.pow(2.0, count.toDouble()).toInt()
            count--
            Log.d("forResult", resultInt.toString())
        }

        Log.d("test", resultInt.toString())
        return resultInt
    }

    private fun convertToTitle(n: Int): String {
        return when {
            n <= 26 -> twentySix(n)

            n % 26 == 0 -> {
                convertToTitle((n / 26) - 1) + convertToTitle(n - 26 * ((n / 26) - 1))
            }
            n % 26 != 0 -> {
                convertToTitle(n / 26) + convertToTitle(n - 26 * (n / 26))
            }
            else -> ""
        }
    }


    private fun twentySix(n: Int): String {
        var str = ""
        when (n) {
            1 -> str = "A"
            2 -> str = "B"
            3 -> str = "C"
            4 -> str = "D"
            5 -> str = "E"
            6 -> str = "F"
            7 -> str = "G"
            8 -> str = "H"
            9 -> str = "I"
            10 -> str = "J"
            11 -> str = "K"
            12 -> str = "L"
            13 -> str = "M"
            14 -> str = "N"
            15 -> str = "O"
            16 -> str = "P"
            17 -> str = "Q"
            18 -> str = "R"
            19 -> str = "S"
            20 -> str = "T"
            21 -> str = "U"
            22 -> str = "V"
            23 -> str = "W"
            24 -> str = "X"
            25 -> str = "Y"
            26 -> str = "Z"
        }
        return str
    }

    private fun findMaxArray(nums: IntArray, k: Int): IntArray {

        val newArray = IntArray(nums.size)
        val newArrayIndex = IntArray(nums.size)

        for (i in 0 until nums.size) {
            val index = findMaxIntAndIndex(nums)
            Log.d("index", index.toString())

            newArray[i] = nums[index]
            newArrayIndex[i] = index
            nums[index] = 0
        }

        return newArray
    }

    private fun findMaxIntAndIndex(nums: IntArray): Int {
        //下标
        var index = 0
        //缓存值
        var temp = 0
        for (i in 0 until nums.size) {
            if (nums[i] > temp) {
                temp = nums[i]
                index = i
            }
        }
        return index
    }

    private fun findEquals(nums: IntArray, indexs: IntArray) {
        val newIndexs = emptyArray<Int>()
        var k = 0
        for (i in 0 until nums.size) {
            newIndexs[k] = indexs[i]


        }


    }

    inner class MyAdapter(context: Context?, resource: Int, objects: MutableList<String>?) : ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_mylist, null)
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = getItem(position)
            return view
        }


    }
}
