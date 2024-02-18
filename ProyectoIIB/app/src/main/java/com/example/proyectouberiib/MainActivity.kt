package com.example.proyectouberiib

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instanciamos las variables
        val botonCliente = findViewById<Button>(R.id.btnSoyCliente)
        val botonConductor = findViewById<Button>(R.id.btnSoyConductor)
        val mPref = applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = mPref.edit()


        //  Aqui escuchamos la actividad del cliente  para que vaya a  la actividad de opciones

        botonCliente.setOnClickListener {
            // Guardar el tipo de usuario en las preferencias compartidas
            editor.putString("user", "cliente")
            editor.apply()
            irActividad(SeleccionarOpcion::class.java)
        }

        //  Aqui escuchamos la actividad del conductor  para que vaya a  la actividad de opciones
        botonConductor.setOnClickListener {
            // Guardar el tipo de usuario en las preferencias compartidas
            editor.putString("user'", "conductor")
            editor.apply()
            irActividad(SeleccionarOpcion::class.java)
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}