package com.example.greenroots.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenroots.R

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var buttonSingIn : Button
    private lateinit var textRegister : TextView

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsernameLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        buttonSingIn = findViewById(R.id.buttonSingInLogin)
        textRegister = findViewById(R.id.textRegisterLogin)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        buttonSingIn.setOnClickListener {
            if (validateFields() && validateData()) {
                redirection(MainActivity::class.java)
            }
        }

        textRegister.setOnClickListener {
            redirection(RegisterActivity::class.java)
        }
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

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart llamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy llamado")
    }

    //Functions Apart from the life cycles of the activity

    private fun validateFields() : Boolean{
        if (etUsername.text.toString().trim().isEmpty()){
            Toast.makeText(this,"El campo username es requerido", Toast.LENGTH_SHORT).show()
            return false
        } else if ( etPassword.text.toString().trim().isEmpty() ){
            Toast.makeText(this,"El campo password es requerido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!etUsername.text.toString().trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
    private fun validateData() : Boolean {

        if (etUsername.text.toString().trim() != sharedPreferences.getString("email", "")
            || etPassword.text.toString().trim() != sharedPreferences.getString("password", "")
        ) {
            Toast.makeText(this, "Los Datos son Incorrectos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun redirection(destinationActivity:Class<*>){
        val intent= Intent(this, destinationActivity)
        startActivity(intent)
    }

}