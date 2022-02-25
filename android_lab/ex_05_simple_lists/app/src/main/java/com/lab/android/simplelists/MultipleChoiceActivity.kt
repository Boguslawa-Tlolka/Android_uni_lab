package com.lab.android.simplelists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MultipleChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        val arrayAdapter: ArrayAdapter<*>
        val itemsArray = Array(15) {"Item ${it + 1}"}
        val listView = findViewById<ListView>(R.id.multipleChoiceListView)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, itemsArray)
        listView.adapter = arrayAdapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var info = ""
                val checkedItems = listView.checkedItemPositions

                for (i in 0..checkedItems.size()){
                    if (checkedItems.valueAt(i)) {
                        info += " ${checkedItems.keyAt(i) + 1}"
                    }
                }
                Toast.makeText(applicationContext, "Selected items:$info", Toast.LENGTH_SHORT).show()
            }
    }
}