package com.lab.android.simplelists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class SimpleListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)

        val arrayAdapter: ArrayAdapter<*>
        val itemsArray = Array(15) {"Item ${it + 1}"}
        val listView = findViewById<ListView>(R.id.simpleListListView)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsArray)
        listView.adapter = arrayAdapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val itemValue = listView.getItemAtPosition(position) as String
                Toast.makeText(applicationContext, itemValue, Toast.LENGTH_LONG).show()
            }
    }
}