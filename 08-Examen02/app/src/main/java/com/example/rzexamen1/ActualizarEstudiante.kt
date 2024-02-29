package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActualizarEstudiante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_estudiante)

        val idPadre = BEstudiante.idSeleccionado
        var estudiante = Estudiante(null, "", "", "", "", this)
        estudiante.getEstudianteById(idPadre) { estudianteResult ->
            if (estudianteResult != null) {
                var codigo = findViewById<EditText>(R.id.tv_updCodifoEs)
                codigo.setText(estudianteResult.getcodigoEstudiante()?.toString() ?: "")

                var nombre = findViewById<EditText>(R.id.tv_updNombreEs)
                nombre.setText(estudianteResult.getnombreEstudiante() ?: "")

                var fechaNacimiento = findViewById<EditText>(R.id.tv_updFechaNEs)
                fechaNacimiento.setText(estudianteResult.getfechaNacimiento() ?: "")

                var promedio = findViewById<EditText>(R.id.tv_updpromedioEs)
                promedio.setText(estudianteResult.getpromedio())

                var activo = findViewById<EditText>(R.id.tv_updactivoEs)
                activo.setText(estudianteResult.getactivo())
            } else {
                Toast.makeText(this, "No se pudo obtener el estudiante", Toast.LENGTH_SHORT).show()
            }
        }

        val btnActualizarEstudiante = findViewById<Button>(R.id.btn_updEstudiante)
        btnActualizarEstudiante.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.tv_updNombreEs).text.toString()
            val fechaNacimiento = findViewById<EditText>(R.id.tv_updFechaNEs).text.toString()
            val promedio = findViewById<EditText>(R.id.tv_updpromedioEs).text.toString()
            val activo = findViewById<EditText>(R.id.tv_updactivoEs).text.toString()

            estudiante.setnombreEstudiante(nombre)
            estudiante.setfechaNacimiento(fechaNacimiento)
            estudiante.setpromedio(promedio)
            estudiante.setactivo(activo)

            estudiante.updateEstudiante { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
                    cleanEditText()
                    val intent = Intent(this, BEstudiante::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.tv_updNombreEs)
        val fechaNacimiento = findViewById<EditText>(R.id.tv_updFechaNEs)
        val promedio = findViewById<EditText>(R.id.tv_updpromedioEs)
        val activo = findViewById<EditText>(R.id.tv_updactivoEs)

        nombre.setText("")
        fechaNacimiento.setText("")
        promedio.setText("")
        activo.setText("")
    }
}
