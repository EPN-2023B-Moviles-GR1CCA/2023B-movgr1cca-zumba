package com.example.rzexamen1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Estudiante(
    var codigoEstudiante: Int?,
    var nombreEstudiante: String?,
    var fechaNacimiento: String?,
    var promedio: String,
    var activo: String,
    val context: Context?
) {

   /* init{
        codigoEstudiante
        nombreEstudiante
        fechaNacimiento
        promedio
        activo
        context
    }*/

        // Métodos set

       fun setcodigoEstudiante(codigoEstudiante: Int) {
            this.codigoEstudiante = codigoEstudiante
        }

        fun setnombreEstudiante(nombreEstudiante: String?) {
            this.nombreEstudiante = nombreEstudiante
        }

        fun setfechaNacimiento(fechaNacimiento: String?) {
            this.fechaNacimiento = fechaNacimiento
        }

        fun setpromedio(promedio: String) {
            this.promedio = promedio
        }

        fun setactivo(activo: String) {
            this.activo = activo
        }

        // Métodos get

        fun getcodigoEstudiante(): Int? {
            return codigoEstudiante
        }

        fun getnombreEstudiante(): String? {
            return nombreEstudiante
        }

        fun getfechaNacimiento(): String? {
            return fechaNacimiento
        }

        fun getpromedio(): String {
            return promedio
        }

        fun getactivo(): String {
            return activo
        }

// Funcion  Insertar

    fun insertEstudiante(): Long {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("codigoEstudiante", this.codigoEstudiante)
        values.put("nombreEstudiante", this.nombreEstudiante)
        values.put("fechaNacimiento", this.fechaNacimiento)
        values.put("promedio", this.promedio)
        values.put("activo", this.activo)

        return db.insert("t_estudiante", null, values)
    }

 //Funcion Mostrar a los estudiantes

    fun showEstudiantes(): ArrayList<Estudiante> {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<Estudiante>()
        var estudiante: Estudiante
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_estudiante", null)

        if (cursor.moveToFirst()) {
            do {
                estudiante = Estudiante(null, "", "", "", "", null)

                estudiante.setcodigoEstudiante(cursor.getString(0).toInt())
                estudiante.setnombreEstudiante(cursor.getString(1))
                estudiante.setfechaNacimiento(cursor.getString(2))
                estudiante.setpromedio(cursor.getString(3))
                estudiante.setactivo(cursor.getString(4))
                lista.add(estudiante)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

// Obtener  al estudiante con codigo
fun getEstudianteById(id: Int): Estudiante {
    val dbHelper = BaseDatos(this.context)
    val db: SQLiteDatabase = dbHelper.writableDatabase

    var estudiante = Estudiante(null, "", "", "", "",this.context)
    var cursor: Cursor? = null

    cursor = db.rawQuery("SELECT * FROM t_estudiante WHERE codigoEstudiante = ${id+1}", null)

    if (cursor.moveToFirst()) {
        do {
            estudiante.setcodigoEstudiante(cursor.getString(0).toInt())
            estudiante.setnombreEstudiante(cursor.getString(1))
            estudiante.setfechaNacimiento(cursor.getString(2))
            estudiante.setpromedio(cursor.getString(3))
            estudiante.setactivo(cursor.getString(4))
        } while (cursor.moveToNext())
    }

    cursor.close()
    return estudiante
}



    //Funcion Update
    fun updateEstudiante(): Int {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("nombreEstudiante", this.nombreEstudiante)
        values.put("fechaNacimiento", this.fechaNacimiento)
        values.put("promedio", this.promedio)
        values.put("activo", this.activo)

        return db.update("t_estudiante", values, "codigoEstudiante="+this.codigoEstudiante, null)
    }

    //Funcion Delete verdadera
    fun deleteEstudiante(id: Int): Int {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_estudiante", "codigoEstudiante="+(id+1), null)
    }

    //Sobreescribir la funcion

    override fun toString(): String {
        val salida =
            "Codigo: ${codigoEstudiante} \n" +
                    "Nombre: ${nombreEstudiante} \n"+
                    "Fecha Nacimiento: ${fechaNacimiento} \n"+
                    "Promedio: ${promedio}\n"+
                    "Activo: ${activo}"

        return salida
    }
}
