package com.admin.customviewdemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:MyAdapter
    private val listContent = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTitle.setTitleText("Kick Your Ass")
        myTitle.setBackText("Close?")
        myTitle.setBackButtonClickListener(View.OnClickListener {
            Toast.makeText(this, "Close unabled now!", Toast.LENGTH_SHORT).show()
        })

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


    inner class MyAdapter(context: Context?, resource: Int, objects: MutableList<String>?) : ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_mylist, null)
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = getItem(position)
            return view
        }


    }
}
