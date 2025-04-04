package com.example.greenroots.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.greenroots.R

class CategoriasFragment : Fragment() {

    private lateinit var lyCategoryElectronic: LinearLayout
    private lateinit var lyCategoryClothes: LinearLayout
    private lateinit var lyCategoryHome: LinearLayout
    private lateinit var lyCategorySports: LinearLayout
    private lateinit var lyCategoryAccesories: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)

        lyCategoryElectronic = view.findViewById(R.id.categoryElectronic)
        lyCategoryClothes = view.findViewById(R.id.categoryClothes)
        lyCategoryHome = view.findViewById(R.id.categoryHome)
        lyCategorySports = view.findViewById(R.id.categorySports)
        lyCategoryAccesories = view.findViewById(R.id.categoryAccesories)

        lyCategoryHome.setOnClickListener{
            findNavController().navigate(R.id.CategoriaHogarFragment)
        }

        lyCategoryClothes.setOnClickListener{
            findNavController().navigate(R.id.CategoriaRopaFragment)
        }

        lyCategorySports.setOnClickListener{
            findNavController().navigate(R.id.CategoriaDeportesFragment)
        }

        lyCategoryElectronic.setOnClickListener{
            findNavController().navigate(R.id.CategoriaElectronicaFragment)
        }

        lyCategoryAccesories.setOnClickListener{
            findNavController().navigate(R.id.CategoriaAccesoriosFragment)
        }

        return view
    }

}