package com.example.rzexamen1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class VerMateria : AppCompatActivity() {

 companion object {
     var idMateriaSeleccionada = 0

   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_materia)


        val idEstudiante = BEstudiante.idSeleccionado
        var idEstudianteAux =  Estudiante(null, "", "","","",this)

        val textViewPadre = findViewById<TextView>(R.id.tv_padreVerMaterias)
        textViewPadre.text = "Padre:" + idEstudianteAux.getEstudianteById(idEstudiante).getnombreEstudiante()

        val btnCrearMaterias = findViewById<Button>(R.id.btn_CrearMaterias)
        btnCrearMaterias.setOnClickListener {
            irActividad(BMateria::class.java)
        }

      showListView(idEstudiante)


    }
//Con esta estaba
   fun  showListView(id:Int){
        val objMateria = Materia(null,"","","","",0,this)
        val listviewMaterias = findViewById<ListView>(R.id.lv_Materia)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objMateria.mostrarMateria(id)
        )
        listviewMaterias.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listviewMaterias)
    }





    //Original
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_materia, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idMateriaSeleccionada = id
    }

//Verdadera

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId){
            R.id.mi_editarmateria ->{
                irActividad(ActualizarMateria::class.java)
                return true
            }
            R.id.mi_eliminarmaterias->{
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
    }

    }



    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta materia?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val materia = Materia(null, "", "", "", "", 0, this)
                val resultado = materia.deleteMateria(idMateriaSeleccionada)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idEstudiante = BEstudiante.idSeleccionado
                showListView(idEstudiante)
            }
        )
        builder.setNegativeButton(
            "NO",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }



    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}


