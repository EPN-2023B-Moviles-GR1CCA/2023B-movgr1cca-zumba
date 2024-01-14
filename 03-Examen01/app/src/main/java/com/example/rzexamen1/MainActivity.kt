package com.example.rzexamen1


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Con la letra R accedemos a los Recursos
    setContentView(R.layout.activity_main)
        val dbHelper: BaseDatos = BaseDatos(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        if (db != null) {

        } else {
            Toast.makeText(this, "ERROR AL CREAR LA BD", Toast.LENGTH_LONG).show()
        }
        irActividad(BEstudiante::class.java)

    }

//Ir a la actividad
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}
