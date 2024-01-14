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


class BEstudiante : AppCompatActivity() {


    companion object{
        var idSeleccionado = 0 
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aqui vamos a escuchar la actividad
        setContentView(R.layout.activity_bestudiante)
        //Actualiza el menu
        showListViewEstudiante()

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
                showListViewEstudiante()

            }else{
                Toast.makeText(this,"Error ", Toast.LENGTH_LONG).show()
            }
        }


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
   }R.id.mi_eliminarestudiante->{
                 abrirDialogo()
                return true

            }
            R.id.mi_vermaterias->{
                irActividad(VerMateria::class.java)
                return true

       }else-> super.onContextItemSelected(item)

        }

    }


  fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este estudiante?")

        builder.setPositiveButton("SI") { dialog, which ->
            // Verificar que idSeleccionado sea válido antes de intentar eliminar
            if (idSeleccionado >= 0) {val padre = Estudiante(null, "", "", "", "", this)
                val resultado = padre.deleteEstudiante(idSeleccionado )

                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    runOnUiThread {
                        showListViewEstudiante()
                    }
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }

            } else {
                // Manejar el caso en que idSeleccionado no es válido
                Toast.makeText(this, "Selección no válida", Toast.LENGTH_LONG).show()
            }
        }

        builder.setNegativeButton("NO") { dialog, which ->
            // Manejar el caso en que el usuario selecciona "NO"
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_LONG).show()
        }

        val dialogo = builder.create()
        dialogo.show()
    }
    fun showListViewEstudiante() {
        // ListView
        val estudiante = Estudiante(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.lvView_Estudiante)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            estudiante.showEstudiantes()
        )
        //Actualiza la interfaz del Estudiante
        //Pero no vale mierda .........
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

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

    fun irActividad(
            clase: Class<*>
        ) {
            val intent = Intent(this, clase)
            startActivity(intent)
        }

}

