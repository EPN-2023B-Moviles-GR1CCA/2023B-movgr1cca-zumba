package com.example.proyectoiib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Login : AppCompatActivity() {

    //private lateinit var mAuth: FirebaseAuth
    //private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        //Declaramos la variable  y llamamos al boton para luego escuchar la actividad
        val usuarioLogin = findViewById<EditText>(R.id.tvUsuarioLogin)
        val contraseñaLogin = findViewById<EditText>(R.id.tvcontraseñalogin)
        val btnIngresarLogin = findViewById<Button>(R.id.btnIngresarLogin)

        btnIngresarLogin.setOnClickListener {
            val usuario = usuarioLogin.text.toString()
            val contraseña = contraseñaLogin.text.toString()

           /* if (usuario.isNotEmpty() && contraseña.isNotEmpty()) {
                if (contraseña.length >= 6) {
                    // Aquí puedes realizar la autenticación con Firebase
                } else {
                    Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }*/
        }
    }
}
