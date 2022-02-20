package com.lab.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PersonTabFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_person_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())

        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val surnameEditText = view.findViewById<EditText>(R.id.surnameEditText)

        nameEditText.setText(preferences.getString("user_name", ""))
        surnameEditText.setText(preferences.getString("user_surname", ""))

        val buttonSave = view.findViewById<Button>(R.id.saveButton)
        buttonSave.setOnClickListener {
            preferences.edit {
                putString("user_name", nameEditText.text.toString())
                putString("user_surname", surnameEditText.text.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PersonTabFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}