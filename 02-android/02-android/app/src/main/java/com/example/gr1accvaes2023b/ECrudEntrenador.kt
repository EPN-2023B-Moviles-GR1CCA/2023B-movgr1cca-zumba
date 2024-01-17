package com.example.gr1accvaes2023b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)
//Buscar
        val botonBusacarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
         botonBusacarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)

             // Busqueda en la BDD Sqlite
             val entrenador = EBaseDeDatos.tablaEntrenador!!
                 .consultarEntrenadorPorID(
                     id.text.toString().toInt()
                 )
             //Setear los valores en los componentes visuales
             if(entrenador.id ==0){
                 mostrarSnackbar("Usu. no encontrado")
             }
             id.setText(entrenador.id.toString())
             nombre.setText(entrenador.nombre)
             descripcion.setText(entrenador.descripcion)
             mostrarSnackbar("Usuario no encontrado")
        }
//Crear
        val bobtonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        bobtonCrearBDD.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val respuesta = EBaseDeDatos.tablaEntrenador!!.crearEntrenador(
                nombre.text.toString(), descripcion.text.toString()
            )
           if(respuesta)  mostrarSnackbar("Entidad Creada")
        }
        //Actualizar
        val  botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val respuesta =     EBaseDeDatos.tablaEntrenador!!.actualizarEntrenadorFormulario(nombre.text.toString(),
                descripcion.text.toString(),
                id.text.toString().toInt())
            if (respuesta) mostrarSnackbar("Usuario actualizado")

        }

        //Eliminar
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val respuesta = EBaseDeDatos.tablaEntrenador!!.eliminarEntrenadorFormulario(
                    id.text.toString().toInt()
                )
                if(respuesta) mostrarSnackbar("Usuario Eliminado")
                }

    }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.cl_sqlite), // view
                texto, // texto
                Snackbar.LENGTH_LONG // tiempo
            )
            .show()
    }


}