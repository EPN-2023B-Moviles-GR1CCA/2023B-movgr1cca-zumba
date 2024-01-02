package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class BEstudiante : AppCompatActivity() {


    companion object{
        var idSeleccionado = 0
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aqui vamos a escuchar la actividad
        setContentView(R.layout.activity_bestudiante)

        //Declaramos la variable  y llamamos a  al boton para luego escuchar la actividad
        val nombre = findViewById<EditText>(R.id.editTextText_NombreEs)
        val fechaNacimiento = findViewById<EditText>(R.id.editTextText_FechaNacimiento)
        val promedio = findViewById<EditText>(R.id.editTexPromedio)
        val activo = findViewById<EditText>(R.id.editTextText_Activo)

        val btncrearEstudiante = findViewById<Button>(R.id.btnCrearEstudiante)
        //Aqui vamos a escuchar
        btncrearEstudiante.setOnClickListener {
     //Crear instancia

        }


    }

    // Esta funcion nos permite
 fun irActividad(
     clase: Class<*>
 ){
     val intent = Intent(this, clase)
     startActivities(intent)
 }

}