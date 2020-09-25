package de.ahochschute.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun insertItem(view: View){
        val index = Random.nextInt(8)
        val newItem = ExampleItem(R.drawable.ic_baseline_android_24, "New item at position $index", "Line2")
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)

    }

    fun removeItem(view: View){
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()

        for(i in 0 until size){
            val drawable = when (i % 3){
                0 -> R.drawable.ic_baseline_android_24
                1 -> R.drawable.ic_baseline_audiotrack_24
                else -> R.drawable.ic_baseline_wb_sunny_24
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}