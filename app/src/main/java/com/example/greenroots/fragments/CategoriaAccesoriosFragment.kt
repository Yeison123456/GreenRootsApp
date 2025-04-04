package com.example.greenroots.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.greenroots.R

class CategoriaAccesoriosFragment : Fragment() {

    private lateinit var textGoBack: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categoria_accesorios, container, false)

        textGoBack = view.findViewById(R.id.textGoBack)

        textGoBack.setOnClickListener {
            findNavController().navigate(R.id.CategoriasFragment)
        }

        return view
    }


}