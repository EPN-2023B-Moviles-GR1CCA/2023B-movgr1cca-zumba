package com.example.rzexamen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView

@Suppress("UNREACHABLE_CODE")
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
            val padre = Estudiante(
                null,
                nombre.text.toString(),
                fechaNacimiento.text.toString(),
                promedio.text.toString(),
                activo.text.toString(), this
            )
val  resultado = padre.insertEstudiante()

            if(resultado > 0){

                Toast.makeText(this,"Registro Guardado", Toast.LENGTH_LONG).show()
                cleanEditText()

            }else{
                Toast.makeText(this,"Error ", Toast.LENGTH_LONG).show()
            }
        }


    }

    //Limpiar los campos del texto
    fun cleanEditText(){
        val nombre = findViewById<EditText>(R.id.editTextText_NombreEs)
        val fechaNacimiento = findViewById<EditText>(R.id.editTextText_FechaNacimiento)
        val promedio = findViewById<EditText>(R.id.editTexPromedio)
        val activo = findViewById<EditText>(R.id.editTextText_Activo)

        nombre.text.clear()
        fechaNacimiento.text.clear()
        promedio.text.clear()
        activo.text.clear()

        //Esto hace que el cursor se ponga en  la primer input
        nombre.requestFocus()
    }

    //Llenar los campos del menu
    fun showListViewAlbum() {
        // ListView Canciones
        val album = Estudiante(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.lvView_Estudiante)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            album.showEstudiantes()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }


    //Menu contextual
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_estudiante, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSeleccionado = id

    }


    //Tener selecionada la opcion para que se muestre las opciones
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId){
       R.id.mi_editarestudiante -> {
       irActividad(ActualizarEstudiante::class.java)
           return true
   }

           /* R.id.mi_eliminarestudiante->{

            }*/

       /*R.id.mi_vermaterias->{

       }*/else-> super.onContextItemSelected(item)

        }

    }


//Abrir dialogo

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este álbum?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val padre = Estudiante(null, "", "", "", "", this)
                val resultado = padre.deleteEstudiante(idSeleccionado)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewAlbum()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
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

