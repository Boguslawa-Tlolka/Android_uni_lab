package com.lab.android.menu

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView

class ImagesFragment : Fragment() {

    private var actionMode: ActionMode? = null
    private lateinit var backgroundImageView: ImageView
    private lateinit var imageView: ImageView

    private var checkedId: Int? = null

    private val actionModeCallback: ActionMode.Callback = object: ActionMode.Callback {
        override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            activity?.menuInflater?.inflate(R.menu.color_menu, p1)
            return true
        }

        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(p0: ActionMode, p1: MenuItem): Boolean {
            return when (p1.itemId) {
                R.id.red -> {
                    backgroundImageView.setBackgroundColor(Color.RED)
                    p0.finish()
                    true
                }
                R.id.blue -> {
                    backgroundImageView.setBackgroundColor(Color.BLUE)
                    p0.finish()
                    true
                }
                R.id.green -> {
                    backgroundImageView.setBackgroundColor(Color.GREEN)
                    p0.finish()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            actionMode = null
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_images, container, false)
        backgroundImageView = root.findViewById(R.id.backgroundImageView)
        imageView = root.findViewById(R.id.imageView)
        root.findViewById<ImageView>(R.id.backgroundImageView).setOnLongClickListener {
            when (actionMode) {
                null -> {
                    actionMode = activity?.startActionMode(actionModeCallback)
                    view?.isSelected = true
                    true
                }
                else -> false
            }
        }
        registerForContextMenu(root.findViewById(R.id.imageView))
        return root
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.image_menu, menu)

        val item: MenuItem
        when (checkedId) {
            0 -> {
                item = menu.findItem(R.id.flower)
                item.isChecked = true
            }
            1 -> {
                item = menu.findItem(R.id.sun)
                item.isChecked = true
            }
            2 -> {
                item = menu.findItem(R.id.fire)
                item.isChecked = true
            }
            else -> {}
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.flower -> {
                item.isChecked = !item.isChecked
                checkedId = 0
                imageView.setImageResource(R.drawable.ic_flower)
                true
            }
            R.id.sun -> {
                item.isChecked = !item.isChecked
                checkedId = 1
                imageView.setImageResource(R.drawable.ic_sun)
                true
            }
            R.id.fire -> {
                item.isChecked = !item.isChecked
                checkedId = 2
                imageView.setImageResource(R.drawable.ic_fire)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}