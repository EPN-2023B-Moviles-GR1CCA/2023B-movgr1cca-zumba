package com.example.rzexamen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView

class BEstudiante : AppCompatActivity() {

    companion object{
        var idSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bestudiante)
        showListViewEstudiante()

        val nombre = findViewById<EditText>(R.id.editTextText_NombreEs)
        nombre.requestFocus()
        val fechaNacimiento = findViewById<EditText>(R.id.editTextText_FechaNacimiento)
        val promedio = findViewById<EditText>(R.id.editTexPromedio)
        val activo = findViewById<EditText>(R.id.editTextText_Activo)

        val btncrearEstudiante = findViewById<Button>(R.id.btnCrearEstudiante)
        btncrearEstudiante.setOnClickListener {
            val estudiante = Estudiante(
                null,
                nombre.text.toString(),
                fechaNacimiento.text.toString(),
                promedio.text.toString(),
                activo.text.toString(), this
            )
            val idInsertado = estudiante.insertEstudiante()

            if(idInsertado != null){
                Toast.makeText(this,"Registro Guardado", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewEstudiante()
            } else {
                Toast.makeText(this,"Error al guardar el registro", Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_estudiante, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editarestudiante -> {
                irActividad(ActualizarEstudiante::class.java)
                true
            }
            R.id.mi_eliminarestudiante -> {
                abrirDialogo()
                true
            }
            R.id.mi_vermaterias -> {
                irActividad(VerMateria::class.java)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este estudiante?")

        builder.setPositiveButton("SI") { dialog, which ->
            if (idSeleccionado >= 0) {
                val estudiante = Estudiante(null, "", "", "", "", this)
                estudiante.deleteEstudiante { resultado ->
                    if (resultado) {
                        // Registro eliminado correctamente
                        Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                        runOnUiThread {
                            showListViewEstudiante()
                        }
                    } else {
                        // Error al eliminar el registro
                        Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Selección no válida", Toast.LENGTH_LONG).show()
            }
        }

        builder.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_LONG).show()
        }

        val dialogo = builder.create()
        dialogo.show()
    }

    private fun showListViewEstudiante() {
        val estudiante = Estudiante(null, "", "", "", "", this)
        estudiante.showEstudiantes { listaEstudiantes ->
            val listView = findViewById<ListView>(R.id.lvView_Estudiante)
            val adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaEstudiantes
            )
            listView.adapter = adaptador
            registerForContextMenu(listView)
        }
    }


    private fun cleanEditText(){
        val nombre = findViewById<EditText>(R.id.editTextText_NombreEs)
        val fechaNacimiento = findViewById<EditText>(R.id.editTextText_FechaNacimiento)
        val promedio = findViewById<EditText>(R.id.editTexPromedio)
        val activo = findViewById<EditText>(R.id.editTextText_Activo)

        nombre.text.clear()
        fechaNacimiento.text.clear()
        promedio.text.clear()
        activo.text.clear()

        nombre.requestFocus()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}

