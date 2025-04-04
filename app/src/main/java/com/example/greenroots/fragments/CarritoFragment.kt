package com.example.greenroots.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.greenroots.R
import org.json.JSONArray
import java.text.NumberFormat
import java.util.Locale

class CarritoFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var containerLayout: LinearLayout
    private lateinit var tvTotal: TextView
    private lateinit var btnCheckout: Button
    private lateinit var tvEmptyCart: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("ShoppingCart", Context.MODE_PRIVATE)
        containerLayout = view.findViewById(R.id.cartItemsContainer)
        tvTotal = view.findViewById(R.id.tvTotal)
        btnCheckout = view.findViewById(R.id.btnCheckout)
        tvEmptyCart = view.findViewById(R.id.tvEmptyCart)

        btnCheckout.setOnClickListener {
            checkout()
        }

        loadCartItems()

        return view
    }

    private fun loadCartItems() {
        containerLayout.removeAllViews()

        val productsJson = sharedPreferences.getString("products", "[]")
        val productsArray = JSONArray(productsJson)

        if (productsArray.length() == 0) {
            tvEmptyCart.visibility = View.VISIBLE
            tvTotal.visibility = View.GONE
            btnCheckout.visibility = View.GONE
            return
        }

        tvEmptyCart.visibility = View.GONE
        tvTotal.visibility = View.VISIBLE
        btnCheckout.visibility = View.VISIBLE

        var total = 0

        for (i in 0 until productsArray.length()) {
            val product = productsArray.getJSONObject(i)
            val name = product.getString("name")
            var amount = product.getInt("amount")
            val price = product.getInt("price")

            total += price * amount

            addCartItemView(name, amount, price)
        }

        updateTotal(total)
    }

    private fun addCartItemView(name: String, amount: Int, price: Int) {
        val itemView = LayoutInflater.from(requireContext())
            .inflate(R.layout.item_cart_product, containerLayout, false)

        itemView.findViewById<TextView>(R.id.tvProductName).text = name
        itemView.findViewById<TextView>(R.id.tvProductPrice).text =
            "Precio unitario: ${formatPrice(price)}"

        val tvAmount = itemView.findViewById<TextView>(R.id.tvProductAmount)
        tvAmount.text = amount.toString()

        itemView.findViewById<Button>(R.id.btnDecrease).setOnClickListener {
            val currentAmount = tvAmount.text.toString().toInt()
            if (currentAmount > 1) {
                val newAmount = currentAmount - 1
                tvAmount.text = newAmount.toString()
                updateProductAmount(name, newAmount)
            } else {
                removeProduct(name)
            }
        }

        itemView.findViewById<Button>(R.id.btnIncrease).setOnClickListener {
            val newAmount = tvAmount.text.toString().toInt() + 1
            tvAmount.text = newAmount.toString()
            updateProductAmount(name, newAmount)
        }

        itemView.findViewById<Button>(R.id.btnRemoveProduct).setOnClickListener {
            removeProduct(name)
        }

        containerLayout.addView(itemView)
    }

    private fun updateProductAmount(name: String, newAmount: Int) {
        val productsJson = sharedPreferences.getString("products", "[]")
        val productsArray = JSONArray(productsJson)
        val newProductsArray = JSONArray()

        for (i in 0 until productsArray.length()) {
            val product = productsArray.getJSONObject(i)
            if (product.getString("name") == name) {
                if (newAmount > 0) {
                    product.put("amount", newAmount)
                    newProductsArray.put(product)
                }
            } else {
                newProductsArray.put(product)
            }
        }

        sharedPreferences.edit()
            .putString("products", newProductsArray.toString())
            .apply()

        loadCartItems()
    }

    private fun removeProduct(name: String) {
        updateProductAmount(name, 0)
    }

    private fun updateTotal(total: Int) {
        tvTotal.text = "Total: ${formatPrice(total)}"
    }

    private fun checkout() {
        Toast.makeText(requireContext(), "Compra realizada por un total de ${tvTotal.text}", Toast.LENGTH_LONG).show()
        sharedPreferences.edit().remove("products").apply()
        loadCartItems()
    }

    private fun formatPrice(price: Int): String {
        return "$${String.format(Locale.US, "%,d", price)}"
    }


}