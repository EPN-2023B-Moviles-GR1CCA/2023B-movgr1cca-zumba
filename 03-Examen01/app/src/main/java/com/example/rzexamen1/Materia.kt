package com.example.rzexamen1


import android.content.ContentValues
import android.content.Context

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Materia(
    var codigoMateria: Int?,
    var nombreMateria: String?,
    var creditos: String?,
    var costo: String?,
    var esObligatorio: String?, var codigoEstudiante: Int,
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


    fun setCreditos(creditos: String){
        this.creditos = creditos
    }

    fun setCosto(costo: String){
        this.costo = costo
    }

    fun setesObligatorio(esObligatorio: String){
        this.esObligatorio = esObligatorio
    }

    fun setcodigoEstudiante(codigoEstudiante: Int){
        this.codigoEstudiante = codigoEstudiante
    }

  //Metodo get


  fun getcodigoMateria(): Int? {
      return  codigoMateria
  }


    fun getnombreMateria(): String? {
        return nombreMateria
    }


    fun getCosto(): String?{
        return costo
    }

    fun getcreditos(): String? {
        return creditos
    }

    fun getesObligatorio(): String?{
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
       values.put("IDestudiante",this.codigoEstudiante)

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
                materia = Materia(null, "", "", "", "", 0, null)

                materia.setcodigoMateria(cursorMateria.getString(0).toInt())
             materia.setnombreMateria(cursorMateria.getString(1))
                materia.setCreditos(cursorMateria.getString(2))
                materia.setCosto(cursorMateria.getString(3))
                materia.setesObligatorio(cursorMateria.getString(4))
                materia.setcodigoEstudiante(cursorMateria.getString(5).toInt())
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
                materia.setcodigoMateria(cursor.getString(0).toInt())
                materia.setnombreMateria(cursor.getString(1))
                materia.setCreditos(cursor.getString(2))
                materia.setCosto(cursor.getString(3))
                materia.setesObligatorio(cursor.getString(4))
                materia.setcodigoEstudiante(cursor.getString(5).toInt())

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