package com.example.rzexamen1


import android.content.ContentValues
import android.content.Context

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Materia(
    var codigoMateria: Int?,
    var nombreMateria: String?,
    var creditos: Double?,
    var costo: Double,
    var esObligatorio: Boolean, var codigoEstudiante: Int,
    val context: Context?) {


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

    //Metodos set

fun setcodigoMateria(codigoMateria: Int){
    this.codigoMateria = codigoMateria
}

    fun setnombreMateria(nombreMateria: String){
        this.nombreMateria = nombreMateria
    }


    fun setCreditos(creditos: Double){
        this.creditos = creditos
    }

    fun setCosto(costo: Double){
        this.costo = costo
    }

    fun setesObligatorio(esObligatorio: Boolean){
        this.esObligatorio = esObligatorio
    }


  //Metodo get


  fun getcodigoMateria(): Int? {
      return  codigoMateria
  }


    fun getnombreMateria(): String? {
        return nombreMateria
    }


    fun getCosto(): Double{
        return costo
    }

    fun getCreditos(): Double? {
        return creditos
    }

    fun getesObligatorio(): Boolean{
        return esObligatorio
    }


   //Funcion Insertar
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

    //Funcion


    fun mostrarEstudiante(codigoEstudiante: Int): ArrayList<Materia> {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val listaMaterias = ArrayList<Materia>()
        var materia: Materia
        var cursorMateria: Cursor? = null

        cursorMateria = db.rawQuery("SELECT * FROM t_materias WHERE codigoEstudiante=$codigoEstudiante", null)

        if (cursorMateria.moveToFirst()) {
            do {
                materia = Materia(null, "", 0.0, 0.0, true, 0, null)

                materia.codigoMateria = cursorMateria.getInt(0)
                materia.nombreMateria = cursorMateria.getString(1)
                materia.creditos = cursorMateria.getDouble(2)
                materia.costo = cursorMateria.getDouble(3)
                materia.esObligatorio = cursorMateria.getInt(4) == 1
                materia.codigoEstudiante = cursorMateria.getInt(5)

                listaMaterias.add(materia)
            } while (cursorMateria.moveToNext())
        }

        cursorMateria.close()
        return listaMaterias
    }

    //

    fun getMateriaById(id: Int): Materia {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var materia = Materia(null, "", 0.0, 0.0, false,0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_materias WHERE idMateria = ${id + 1}", null)

        if (cursor.moveToFirst()) {
            do {
                materia.codigoMateria = cursor.getInt(0)
                materia.nombreMateria = cursor.getString(1)
                materia.creditos = cursor.getDouble(2)
                materia.costo = cursor.getDouble(3)
                materia.esObligatorio = cursor.getInt(4) == 1
                materia.codigoEstudiante = cursor.getInt(5)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return materia
    }
    //Funcion Eliminar

    fun deleteMateria(id: Int): Int {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_materia", "codigoMateria"+ (id+1), null)
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
        return db.update("t_materia", values, "codigoMateria="+this.codigoMateria, null)
    }




//Metodo toString

    override fun toString(): String {
        val salida=
        "codigoMateria:${codigoMateria}\n" +
                "nombreMateria: ${nombreMateria}\n" +
                " creditos: ${creditos}\n " +
                "costo=${costo}\n" +
                " esObligatorio=${esObligatorio}"+
                "codigoEstudiante = ${codigoEstudiante}"

        return salida
    }
}