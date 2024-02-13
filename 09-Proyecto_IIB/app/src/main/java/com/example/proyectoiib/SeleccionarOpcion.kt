package com.example.proyectoiib


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SeleccionarOpcion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_opcion)

        //instanciamos las variables
        val botonIrlogin = findViewById<Button>(R.id.btnTengoCuentaLogin)
        val botonIrRegistro = findViewById<Button>(R.id.btnRegistrarmeahora)

        //  Aqui escuchamos la actividad del conductor  para que vaya a  la actividad de opciones
        botonIrlogin.setOnClickListener {
            irActividad(Login::class.java)
        }

        //Para que se vaya al formulario de registro
        botonIrRegistro.setOnClickListener {
            irActividad(Registro::class.java)
        }

    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}