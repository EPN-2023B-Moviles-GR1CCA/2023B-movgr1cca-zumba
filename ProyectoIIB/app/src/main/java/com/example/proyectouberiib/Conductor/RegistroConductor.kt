package com.example.proyectouberiib.Conductor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectouberiib.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistroConductor : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_conductor)
        // Inicializar Firebase Auth y Firebase Database
      /*  mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        val mPref = applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val etNombre = findViewById<EditText>(R.id.etnombreRegistro)
        val etApellido = findViewById<EditText>(R.id.etApellidoRegistro)
        val etCorreo = findViewById<EditText>(R.id.etCorreoRegistro)
        val etContraseña = findViewById<EditText>(R.id.etcontraseñaRegistro)

        val SelectedUser=  mPref.getString("user", "")
        Toast.makeText(this, "fue" +SelectedUser , Toast.LENGTH_SHORT).show()


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
                                    saveUser(nombre, correo)
                                    // Si el registro en la base de datos es exitoso, inicia la actividad MapaConductor
                                    //Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@Registro, MapaConductor::class.java)
                                    startActivity(intent)
                                    // Finaliza la actividad actual para evitar que el usuario regrese al formulario de registro con el botón "Atrás"
                                    finish()
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

        //Gurdar en la base de datos


    }

    fun saveUser(nombre: String, correo: String) {
        val mPref = applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
        val selectedUser = mPref.getString("user", "")

        val userRef = mDatabase.reference.child("Users").push() // Generar una nueva clave única para el usuario
        val user = HashMap<String, String>()
        user["nombre"] = nombre
        user["correo"] = correo

        // Guardar los detalles del usuario en la base de datos
        userRef.setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // La información del usuario se guardó exitosamente en la base de datos
                    Toast.makeText(this, "Información del usuario guardada correctamente", Toast.LENGTH_SHORT).show()

                    // Dependiendo del tipo de usuario, guardar en la ubicación adecuada
                    if (selectedUser == "driver") {
                        mDatabase.reference.child("Drivers").child(userRef.key!!).setValue(user)
                    } else if (selectedUser == "cliente") {
                        mDatabase.reference.child("Clientes").child(userRef.key!!).setValue(user)
                    }

                    // Si el registro en la base de datos es exitoso, inicia la actividad MapaConductor
                    val intent = Intent(this@Registro, MapaConductor::class.java)
                    startActivity(intent)
                    // Finaliza la actividad actual para evitar que el usuario regrese al formulario de registro con el botón "Atrás"
                    finish()
                } else {
                    // Hubo un error al guardar la información del usuario en la base de datos
                }
            }*/
    }
}