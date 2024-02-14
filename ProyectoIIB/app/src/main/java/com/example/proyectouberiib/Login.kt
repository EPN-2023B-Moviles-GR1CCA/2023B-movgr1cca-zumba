package com.example.proyectouberiib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        // Referencias a los elementos de la interfaz de usuario
        val usuarioLogin = findViewById<EditText>(R.id.tvUsuarioLogin)
        val contraseñaLogin = findViewById<EditText>(R.id.tvcontraseñalogin)
        val btnIngresarLogin = findViewById<Button>(R.id.btnIngresarLogin)

        // Listener para el botón de inicio de sesión
        btnIngresarLogin.setOnClickListener {
            val usuario = usuarioLogin.text.toString()
            val contraseña = contraseñaLogin.text.toString()

            if (usuario.isNotEmpty() && contraseña.isNotEmpty()) {
                if (contraseña.length >= 6) {
                    signIn(usuario, contraseña)
                } else {
                    Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes navegar a la siguiente actividad o realizar otra acción
                } else {
                    // Si el inicio de sesión falla, muestra un mensaje de error
                    Toast.makeText(this, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
