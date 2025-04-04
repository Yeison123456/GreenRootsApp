package com.example.greenroots.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.greenroots.R
import com.example.greenroots.activities.MainActivity

class EditarPerfilFragment : Fragment() {

    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonChangePhoto: Button
    private lateinit var textGoBack: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        etName = view.findViewById(R.id.etNameProfile)
        etLastName = view.findViewById(R.id.etLastNameProfile)
        etEmail = view.findViewById(R.id.etEmailProfile)
        etPhone = view.findViewById(R.id.etPhoneProfile)
        buttonEdit = view.findViewById(R.id.buttonEditProfile)
        buttonChangePhoto = view.findViewById(R.id.buttonChangePhoto)
        textGoBack = view.findViewById(R.id.textGoBackProfile)

        sharedPreferences = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)

        loadData()

        buttonEdit.setOnClickListener {
            if (validateFields()) {
                editData()
                findNavController().navigate(R.id.PerfilFragment)
            }
        }

        buttonChangePhoto.setOnClickListener {
            Toast.makeText(requireContext(), "Esta funcionalidad todavia no esta implementada", Toast.LENGTH_SHORT).show()
        }

        textGoBack.setOnClickListener {
            findNavController().navigate(R.id.PerfilFragment)
        }

        return view
    }

    private fun validateFields(): Boolean {
        fun isFieldEmpty(field: EditText, errorMessage: String): Boolean {
            if (field.text.toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                return true
            }
            return false
        }

        if (isFieldEmpty(etName, "El campo nombre es requerido")) return false
        if (isFieldEmpty(etLastName, "El campo apellido es requerido")) return false
        if (isFieldEmpty(etEmail, "El campo email es requerido")) return false
        if (isFieldEmpty(etPhone, "El campo teléfono es requerido")) return false

        if (etPhone.text.toString().trim().length < 10) {
            Toast.makeText(requireContext(), "El número de teléfono debe tener al menos 10 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!etEmail.text.toString().trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            Toast.makeText(requireContext(), "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun editData() {
        val control = sharedPreferences.edit()
        control.putString("name", etName.text.toString().trim())
        control.putString("lastName", etLastName.text.toString().trim())

        try {
            control.putLong("phone", etPhone.text.toString().trim().toLong())
        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "El número de teléfono no es válido", Toast.LENGTH_SHORT).show()
            return
        }

        control.putString("email", etEmail.text.toString().trim())
        control.apply()
        Toast.makeText(requireContext(), "Se editaron los datos correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        etName.setText(sharedPreferences.getString("name", ""))
        etLastName.setText(sharedPreferences.getString("lastName", ""))
        etPhone.setText(sharedPreferences.getLong("phone", 0).toString())
        etEmail.setText(sharedPreferences.getString("email", ""))
    }

    private fun redirection(destinationActivity: Class<*>) {
        val intent = Intent(requireActivity(), destinationActivity)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume llamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause llamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop llamado")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Lifecycle", "onDestroyView llamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy llamado")
    }
}