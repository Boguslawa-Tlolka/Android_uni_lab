package com.lab.android.menu

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView

class TextsFragment : Fragment() {

    private lateinit var helloTextView: TextView
    private lateinit var greetingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_texts, container, false)
        helloTextView = root.findViewById(R.id.helloTextView)
        greetingTextView = root.findViewById(R.id.greetingTextView)
        registerForContextMenu(helloTextView)
        registerForContextMenu(greetingTextView)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.red -> {
                helloTextView.setTextColor(Color.RED)
                greetingTextView.setTextColor(Color.RED)
                true
            }
            R.id.blue -> {
                helloTextView.setTextColor(Color.BLUE)
                greetingTextView.setTextColor(Color.BLUE)
                true
            }
            R.id.green -> {
                helloTextView.setTextColor(Color.GREEN)
                greetingTextView.setTextColor(Color.GREEN)
                true
            }
            R.id.small -> {
                helloTextView.textSize = 10f
                greetingTextView.textSize = 10f
                true
            }
            R.id.medium -> {
                helloTextView.textSize = 20f
                greetingTextView.textSize = 20f
                true
            }
            R.id.large -> {
                helloTextView.textSize = 30f
                greetingTextView.textSize = 30f
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v.id == R.id.helloTextView)
            activity?.menuInflater?.inflate(R.menu.color_menu, menu)
        else if (v.id == R.id.greetingTextView)
            activity?.menuInflater?.inflate(R.menu.size_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.contextRed -> {
                helloTextView.setTextColor(Color.RED)
                true
            }
            R.id.contextBlue -> {
                helloTextView.setTextColor(Color.BLUE)
                true
            }
            R.id.contextGreen -> {
                helloTextView.setTextColor(Color.GREEN)
                true
            }
            R.id.contextSmall -> {
                greetingTextView.textSize = 10f
                true
            }
            R.id.contextMedium -> {
                greetingTextView.textSize = 20f
                true
            }
            R.id.contextLarge -> {
                greetingTextView.textSize = 30f
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}