package com.example.rzexamen1


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Con la letra R accedemos a los Recursos
    setContentView(R.layout.activity_main)
        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Si deseas autenticar al usuario aquí, puedes hacerlo

        irActividad(BEstudiante::class.java)

    }

//Ir a la actividad
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}
