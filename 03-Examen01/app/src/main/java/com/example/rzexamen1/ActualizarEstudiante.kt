package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class ActualizarEstudiante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_estudiante)

        val idPadre = BEstudiante.idSeleccionado
        var  estudiante = Estudiante(null,"","","","",this)
        estudiante = estudiante.getEstudianteById(idPadre)


         var codigo = findViewById<EditText>(R.id.tv_updCodifoEs)
        codigo.setText(estudiante.getcodigoEstudiante().toString())

        var nombre = findViewById<EditText>(R.id.tv_updNombreEs)
        nombre.setText(estudiante.getnombreEstudiante())


        var fechaNacimiento = findViewById<EditText>(R.id.tv_updFechaNEs)
        fechaNacimiento.setText(estudiante.getfechaNacimiento())


        var promedio = findViewById<EditText>(R.id.tv_updpromedioEs)
        promedio.setText(estudiante.getpromedio())


        var activo = findViewById<EditText>(R.id.tv_updactivoEs)
        activo.setText(estudiante.getactivo())


        // Dentro de la actividad ActualizarEstudiante
        val btnActualizarEstudiante = findViewById<Button>(R.id.btn_updEstudiante)

        btnActualizarEstudiante.setOnClickListener {
            estudiante.setnombreEstudiante(nombre.text.toString())
            estudiante.setfechaNacimiento(fechaNacimiento.text.toString())
            estudiante.setpromedio(promedio.text.toString())
            estudiante.setactivo(activo.text.toString())

            val resultado = estudiante.updateEstudiante()

            // Después de la actualización y obtener los nuevos datos
            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()

                // Limpiar los campos
                cleanEditText()

                // Llamar a la función showListViewEstudiante en BEstudiante para actualizar la lista
                val intent = Intent(this, BEstudiante::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }

    }


    //Limpiar los campos
   private fun cleanEditText(){
        val nombre = findViewById<EditText>(R.id.tv_updNombreEs)
        val fechaNacimiento = findViewById<EditText>(R.id.tv_updFechaNEs)
        val promedio = findViewById<EditText>(R.id.tv_updpromedioEs)
        val activo = findViewById<EditText>(R.id.tv_updactivoEs)

        nombre.text.clear()
        fechaNacimiento.text.clear()
        promedio.text.clear()
        activo.text.clear()

        //Esto hace que el cursor se ponga en  la primer input
        nombre.requestFocus()
    }
}