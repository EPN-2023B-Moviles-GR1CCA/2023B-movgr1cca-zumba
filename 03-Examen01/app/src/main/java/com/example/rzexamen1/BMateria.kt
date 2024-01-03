package com.example.rzexamen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.EditText

class BMateria : AppCompatActivity() {

    companion object{
        var idSelecionadoMateria = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmateria)
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
        idSelecionadoMateria= id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId){
            R.id.mi_editarmateria -> {
                irActividad(ActualizarMateria::class.java)
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
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }



}