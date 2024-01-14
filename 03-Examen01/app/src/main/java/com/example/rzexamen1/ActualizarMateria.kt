package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActualizarMateria : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_materia)

        val idMateria = VerMateria.idMateriaSeleccionada
        var materia = Materia(null, "","","","",0, this)
        materia = materia.getMateriaById(idMateria)

        var codigo  = findViewById<EditText>(R.id.dt_codigoUPdMateria)
        codigo.setText(materia.getcodigoMateria().toString())

        var nombre =  findViewById<EditText>(R.id.et_nombreUPDMateria)
        nombre.setText(materia.getnombreMateria())

        var creditos = findViewById<EditText>(R.id.et_creditosUDPMateria)
        creditos.setText(materia.getcreditos())

        var costo = findViewById<EditText>(R.id.et_tcostoUPDMATERIA)
        costo.setText(materia.getcosto())

        var esObli = findViewById<EditText>(R.id.et_EsobligatorioUPDMateria)
        esObli.setText(materia.getesObligatorio())

        var idEs = findViewById<EditText>(R.id.et_CodigoEstudianteUPD_Materia)
        idEs.setText(materia.getcodigoEstudiante().toString())

        val btnActualizarMateria = findViewById<Button>(R.id.btn_actualizarMateria)
        btnActualizarMateria.setOnClickListener {

            materia.setnombreMateria(nombre.text.toString())
            materia.setcreditos(creditos.text.toString())
            materia.setcosto(costo.text.toString())
            materia.setesObligatorio(esObli.text.toString())
            materia.setcodigoEstudiante(idEs.text.toString().toInt())

            val resultado = materia.updateMateria()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }







    //Limpiar las cajas de texto

    fun cleanEditText(){
        val nombre = findViewById<EditText>(R.id.ed_nombreMateria)
        nombre.setText("")
        val  creditos = findViewById<EditText>(R.id.ed_creditos)
        creditos.setText("")

        val costo = findViewById<EditText>(R.id.edt_costo)
        costo.setText("")

        val obligatorio = findViewById<EditText>(R.id.edt_esObligatorio)
        obligatorio.setText("")

        val codigoEs = findViewById<EditText>(R.id.ed_codigoEstudinateMateria)
        codigoEs.setText("")
    }
}

