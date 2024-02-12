package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.material3.MaterialTheme

class BMateria : AppCompatActivity() {
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmateria)

        val nombre = findViewById<EditText>(R.id.ed_nombreMateria)
        nombre.requestFocus()
        val  creditos = findViewById<EditText>(R.id.ed_creditos)
        val costo = findViewById<EditText>(R.id.edt_costo)
        val obligatorio = findViewById<EditText>(R.id.edt_esObligatorio)
        val codigoEs = findViewById<EditText>(R.id.ed_codigoEstudinateMateria)

        val btnInsertar = findViewById<Button>(R.id.btn_InsertarMateria)
        btnInsertar.setOnClickListener {

            val materia:  Materia = Materia(
                null,
                nombre.text.toString(),
                creditos.text.toString(),
                costo.text.toString(),
                obligatorio.text.toString(),
                codigoEs.text.toString().toInt(),
                this
            )
            val resultado = materia.InsertarMateria()


            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }


        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_materia, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado= id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId){
            R.id.mi_editarmateria -> {
             "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminarmaterias -> {
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    //limpiar los campos
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