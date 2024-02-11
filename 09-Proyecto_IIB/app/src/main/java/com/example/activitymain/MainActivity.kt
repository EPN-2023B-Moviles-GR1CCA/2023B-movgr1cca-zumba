package com.example.activitymain

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //instanciamos las variables
        val botonCliente = findViewById<Button>(R.id.btnSoyCliente)
        val botonConductor = findViewById<Button>(R.id.btnSoyConductor)


        //  Aqui escuchamos la actividad del cliente  para que vaya a  la actividad de opciones

         botonCliente.setOnClickListener {
             irActividad(SelecionarOpcionRL::class.java)
         }

        //  Aqui escuchamos la actividad del conductor  para que vaya a  la actividad de opciones
        botonConductor.setOnClickListener {
            irActividad(SelecionarOpcionRL::class.java)
        }

    }
//
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}