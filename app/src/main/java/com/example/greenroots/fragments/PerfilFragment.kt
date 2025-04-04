package com.example.greenroots.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.greenroots.R

class PerfilFragment : Fragment() {

    private lateinit var tvName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var buttonEdit: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        tvName = view.findViewById(R.id.tvNameProfile)
        tvLastName = view.findViewById(R.id.tvLastNameProfile)
        tvEmail = view.findViewById(R.id.tvEmailProfile)
        tvPhone = view.findViewById(R.id.tvPhoneProfile)
        buttonEdit = view.findViewById(R.id.buttonEditProfileAc)

        sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        loadData()

        buttonEdit.setOnClickListener {
            findNavController().navigate(R.id.EditarPerfilFragment)
        }

        return view
    }

    private fun loadData(){
        tvName.setText(sharedPreferences.getString("name", ""))
        tvLastName.setText(sharedPreferences.getString("lastName", ""))
        tvEmail.setText(sharedPreferences.getLong("phone", 0).toString())
        tvPhone.setText(sharedPreferences.getString("email", ""))
    }
}