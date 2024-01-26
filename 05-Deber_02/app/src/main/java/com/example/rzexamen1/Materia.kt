package com.example.rzexamen1


import android.content.ContentValues
import android.content.Context

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Materia(
    var codigoMateria: Int?,
    var nombreMateria: String?,
    var creditos: String?,
    var costo: String?,
    var esObligatorio: String?, var CodigoEstudiante: Int,
    val context: Context?) {


    //init
    /*init{
        codigoMateria
        nombreMateria
        creditos
        costo
        esObligatorio
        CodigoEstudiante
        context
    }*/

    //Metodos set

fun setcodigoMateria(codigoMateria: Int){
    this.codigoMateria = codigoMateria
}

    fun setnombreMateria(nombreMateria: String){
        this.nombreMateria = nombreMateria
    }


    fun setcreditos(creditos: String): Unit{
        this.creditos = creditos
    }

    fun setcosto(costo: String){
        this.costo = costo
    }

    fun setesObligatorio(esObligatorio: String){
        this.esObligatorio = esObligatorio
    }

    fun setcodigoEstudiante(codigoEstudiante: Int){
        this.CodigoEstudiante = codigoEstudiante
    }

  //Metodo get


  fun getcodigoMateria(): Int? {
      return  codigoMateria
  }

     fun getcodigoEstudiante(): Int{
         return CodigoEstudiante
     }


    fun getnombreMateria(): String? {
        return nombreMateria
    }


    fun getcosto(): String?{
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
       values.put("esObligatorio", this.esObligatorio)
       values.put("CodigoEstudiante", this.CodigoEstudiante)


       return db.insert("t_materia", null,values)
    }

    //Funcion verdadera


    fun mostrarMateria(id: Int): ArrayList<Materia> {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val listaMaterias = ArrayList<Materia>()
        var materia: Materia
        var cursorMateria: Cursor? = null

        cursorMateria = db.rawQuery("SELECT * FROM t_materia WHERE codigoMateria = ${id+1}", null)

        if (cursorMateria.moveToFirst()) {
            do {
                materia = Materia(null, "", "", "", "", 0, null)

                materia.setcodigoMateria(cursorMateria.getString(0).toInt())
             materia.setnombreMateria(cursorMateria.getString(1))
                materia.setcreditos(cursorMateria.getString(2))
                materia.setcosto(cursorMateria.getString(3))
                materia.setesObligatorio(cursorMateria.getString(4))
                materia.setcodigoEstudiante(cursorMateria.getString(5).toInt())
                listaMaterias.add(materia)
            } while (cursorMateria.moveToNext())
        }

        cursorMateria.close()
        return listaMaterias
    }


    //Verdadera

    fun getMateriaById(id: Int): Materia {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var materia = Materia(null, "", "", "", "",0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_materia WHERE codigoMateria = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                materia.setcodigoMateria(cursor.getString(0).toInt())
                materia.setnombreMateria(cursor.getString(1))
                materia.setcreditos(cursor.getString(2))
                materia.setcosto(cursor.getString(3))
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
//Original
        //return db.delete("t_materia", "codigoMateria"+ (id+1), null)
     //   return db.delete("t_materia", "codigoMateria=" + (id + 1), null)

        return db.delete("t_materia", "codigoMateria=?", arrayOf((id + 1).toString()))

    }

    //Funcion Update
    fun updateMateria(): Int{
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("nombreMateria", this.nombreMateria)
        values.put("creditos", this.creditos)
        values.put("costo", this.costo)
        values.put("esObligatorio", this.esObligatorio)
        values.put("CodigoEstudiante", this.CodigoEstudiante)
        return db.update("t_materia", values, "codigoMateria="+this.codigoMateria, null)
    }


//Metodo toString

    override fun toString(): String {
        val salida=
        "Codigo: ${codigoMateria}\n" +
                "Nombre Materia: ${nombreMateria}\n" +
                "Creditos: ${creditos}\n " +
                "Costo: ${costo}\n" +
                "Es obligatorio: ${esObligatorio} \n"+
                "Codigo Estudiante: ${CodigoEstudiante}"

        return salida
    }
}