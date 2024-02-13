
package com.example.proyectoiib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectoiib.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Registro : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializar Firebase Auth y Firebase Database
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val etNombre = findViewById<EditText>(R.id.etnombreRegistro)
        val etApellido = findViewById<EditText>(R.id.etApellidoRegistro)
        val etCorreo = findViewById<EditText>(R.id.etCorreoRegistro)
        val etContraseña = findViewById<EditText>(R.id.etcontraseñaRegistro)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString().trim()

            mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso, obtén el UID del usuario
                        val userId = mAuth.currentUser?.uid
                        // Guarda los detalles adicionales del usuario en la base de datos
                        val userRef = mDatabase.reference.child("Users").child(userId!!)
                        val user = HashMap<String, String>()
                        user["nombre"] = nombre
                        user["apellido"] = apellido
                        user["correo"] = correo
                        // Guardar los detalles del usuario en la base de datos
                        userRef.setValue(user)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                                    // Aquí puedes iniciar sesión automáticamente o realizar otra acción
                                } else {
                                    Toast.makeText(this, "Error al registrar el usuario en la base de datos", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        // Si el registro falla, muestra un mensaje de error
                        Toast.makeText(this, "Error al registrar el usuario: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
