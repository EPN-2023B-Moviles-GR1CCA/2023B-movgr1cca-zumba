package com.example.rzexamen1


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import kotlin.math.cos

class Materia( var codigoMateria: Int,
    var nombreMateria: String?,
    var creditos: Double?,
    var costo: Double,
    var esObligatorio: Boolean, var codigoEstudiante: Int,
    val context: Context) {


    //init
    init{
        codigoMateria
        nombreMateria
        creditos
        costo
        esObligatorio
        codigoEstudiante
        context
    }

    //Metodos get and set

fun setcodigoMateria(codigoMateria: Int){
    this.codigoMateria = codigoMateria
}

   //FUNCIOn Insertar
    fun InsertarMateria(): Long{
       val dbHelper: BaseDatos = BaseDatos(this.context)
       val db: SQLiteDatabase = dbHelper.writableDatabase
       val values: ContentValues = ContentValues()

       values.put("nombreMateria", this.nombreMateria)
       values.put("creditos", this.creditos)
       values.put("costo", this.costo)
       values.put("esObligatoro", this.esObligatorio)

       return db.insert("t_materia", null,values)
    }
    //Funcion Update
    fun updateMateria(): Int{
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("nombreMateria", this.nombreMateria)
        values.put("creditos", this.creditos)
        values.put("costo", this.costo)
        values.put("esObligatoro", this.esObligatorio)
        values.put("CODESTUDIANTE", this.codigoEstudiante)
        return db.delete("t_materia", values , "codigoMateria="+this.codigoMateria, null)
    }
    //Funcion Eliminar
 fun deleteMateria(id: Int){
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_materia", "codigoMateria="+(id+1), null)
 }




//Metodo toString

    override fun toString(): String {
        val salida=
        "codigoMateria:${codigoMateria}\n" +
                "nombreMateria: ${nombreMateria}\n" +
                " creditos: ${creditos}\n " +
                "costo=${costo}\n" +
                " esObligatorio=${esObligatorio}"

        return salida
    }
}