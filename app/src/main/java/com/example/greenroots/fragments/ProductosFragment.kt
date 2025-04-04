package com.example.greenroots.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.greenroots.R
import org.json.JSONArray
import org.json.JSONObject

class ProductosFragment : Fragment() {

    private lateinit var btProduct1: Button
    private lateinit var btProduct2: Button
    private lateinit var btProduct3: Button
    private lateinit var btProduct4: Button
    private lateinit var btProduct5: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        btProduct1 = view.findViewById(R.id.buttonAddCartProduct1)
        btProduct2 = view.findViewById(R.id.buttonAddCartProduct2)
        btProduct3 = view.findViewById(R.id.buttonAddCartProduct3)
        btProduct4 = view.findViewById(R.id.buttonAddCartProduct4)
        btProduct5 = view.findViewById(R.id.buttonAddCartProduct5)

        sharedPreferences = requireActivity().getSharedPreferences("ShoppingCart", Context.MODE_PRIVATE)

        btProduct1.setOnClickListener{
            addProductShoppingCart("Cepillo para cabello", 1, 15000)
        }

        btProduct2.setOnClickListener{
            addProductShoppingCart("Camisa", 1, 27000)
        }

        btProduct3.setOnClickListener{
            addProductShoppingCart("Sombras", 1, 30000)
        }

        btProduct4.setOnClickListener{
            addProductShoppingCart("Cuadernos", 1, 15000)
        }

        btProduct5.setOnClickListener{
            addProductShoppingCart("Acuarelas", 1, 15000)
        }

        return view
    }


    private fun addProductShoppingCart(name: String, amount: Int, price: Int){
        val control= sharedPreferences.edit()

        val productsJson = sharedPreferences.getString("products", "[]")

        // Convertimos el string JSON a un JSONArray
        val productsArray = JSONArray(productsJson)

        // veficacion de producto existente
        var productExists = false

        // Recorremos los productos existentes para buscar coincidencias
        for (i in 0 until productsArray.length()) {
            val currentProduct = productsArray.getJSONObject(i)

            // Si encontramos un producto con el mismo nombre
            if (currentProduct.getString("name") == name) {
                // Aumentamos la cantidad
                val currentAmount = currentProduct.getInt("amount")
                currentProduct.put("amount", currentAmount + amount)

                // Actualizamos el producto en el array
                productsArray.put(i, currentProduct)

                productExists = true
                break
            }
        }

        // Si el producto no existe, lo añadimos como nuevo
        if (!productExists) {
            // Creamos un objeto JSON para el nuevo producto
            val productObject = JSONObject().apply {
                put("name", name)
                put("amount", amount)
                put("price", price)
            }

            // Agregamos el producto al array
            productsArray.put(productObject)
        }


        control.putString("products", productsArray.toString())
        control.apply()

        Toast.makeText(requireContext(),"Se agrego correctamente al carrito de compras", Toast.LENGTH_SHORT).show()
    }
}