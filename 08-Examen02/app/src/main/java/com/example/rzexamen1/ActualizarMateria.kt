package com.example.rzexamen1
import Materia
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActualizarMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_materia)

        val idMateria = VerMateria.idMateriaSeleccionada
        val materia = Materia(null, "", "", "", "", 0)
        materia.getMateriaById(idMateria) { materiaResult ->
            if (materiaResult != null) {
                findViewById<EditText>(R.id.dt_codigoUPdMateria).setText(materiaResult.getcodigoMateria().toString())
                findViewById<EditText>(R.id.et_nombreUPDMateria).setText(materiaResult.getnombreMateria())
                findViewById<EditText>(R.id.et_creditosUDPMateria).setText(materiaResult.getcreditos())
                findViewById<EditText>(R.id.et_tcostoUPDMATERIA).setText(materiaResult.getcosto())
                findViewById<EditText>(R.id.et_EsobligatorioUPDMateria).setText(materiaResult.getesObligatorio())
                findViewById<EditText>(R.id.et_CodigoEstudianteUPD_Materia).setText(materiaResult.getcodigoEstudiante().toString())
            } else {
                Toast.makeText(this, "No se pudo obtener la materia", Toast.LENGTH_SHORT).show()
            }
        }

        val btnActualizarMateria = findViewById<Button>(R.id.btn_actualizarMateria)
        btnActualizarMateria.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.et_nombreUPDMateria).text.toString()
            val creditos = findViewById<EditText>(R.id.et_creditosUDPMateria).text.toString()
            val costo = findViewById<EditText>(R.id.et_tcostoUPDMATERIA).text.toString()
            val esObligatorio = findViewById<EditText>(R.id.et_EsobligatorioUPDMateria).text.toString()
            val codigoEstudiante = findViewById<EditText>(R.id.et_CodigoEstudianteUPD_Materia).text.toString().toInt()

            materia.setnombreMateria(nombre)
            materia.setcreditos(creditos)
            materia.setcosto(costo)
            materia.setesObligatorio(esObligatorio)
            materia.setcodigoEstudiante(codigoEstudiante)

            materia.updateMateria { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                    cleanEditText()
                } else {
                    Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            }


        }
    }

    private fun cleanEditText() {
        findViewById<EditText>(R.id.et_nombreUPDMateria).setText("")
        findViewById<EditText>(R.id.et_creditosUDPMateria).setText("")
        findViewById<EditText>(R.id.et_tcostoUPDMATERIA).setText("")
        findViewById<EditText>(R.id.et_EsobligatorioUPDMateria).setText("")
        findViewById<EditText>(R.id.et_CodigoEstudianteUPD_Materia).setText("")
    }
}
