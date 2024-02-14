package com.example.proyectouberiib

import android.content.Intent
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


        //  Aqui escuchamos la actividad del cliente  para que vaya a  la actividad de opciones

        botonCliente.setOnClickListener {
            irActividad(SeleccionarOpcion::class.java)
        }

        //  Aqui escuchamos la actividad del conductor  para que vaya a  la actividad de opciones
        botonConductor.setOnClickListener {
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