package com.example.rzexamen1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Estudiante(
    var codigoEstudiante: Int?,
    var nombreEstudiante: String?,
    var fechaNacimiento: String?,
    var promedio: Double?,
    var activo: Boolean?,
    val context: Context?
) {

        // Métodos set

        fun setCodigoEstudiante(codigoEstudiante: Int) {
            this.codigoEstudiante = codigoEstudiante
        }

        fun setNombreEstudiante(nombreEstudiante: String?) {
            this.nombreEstudiante = nombreEstudiante
        }

        fun setFechaNacimiento(fechaNacimiento: String?) {
            this.fechaNacimiento = fechaNacimiento
        }

        fun setPromedio(promedio: Double?) {
            this.promedio = promedio
        }

        fun setActivo(activo: Boolean?) {
            this.activo = activo
        }

        // Métodos get

        fun getCodigoEstudiante(): Int? {
            return codigoEstudiante
        }

        fun getNombreEstudiante(): String? {
            return nombreEstudiante
        }

        fun getFechaNacimiento(): String? {
            return fechaNacimiento
        }

        fun getPromedio(): Double? {
            return promedio
        }

        fun getActivo(): Boolean? {
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

//Funcion Mostrar

    fun showEstudiantes(): ArrayList<Estudiante> {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<Estudiante>()
        var estudiante: Estudiante
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_estudiante", null)

        if (cursor.moveToFirst()) {
            do {
                estudiante = Estudiante(null, "", "", 0.0, false, this.context)

                estudiante.setCodigoEstudiante(cursor.getInt(0).toInt())
                estudiante.setNombreEstudiante(cursor.getString(1))
                estudiante.setFechaNacimiento(cursor.getString(2))
                estudiante.setPromedio(cursor.getDouble(3))
                estudiante.setActivo(cursor.getInt(4) == 1)

                lista.add(estudiante)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

//
fun getEstudianteById(id: Int): Estudiante {
    val dbHelper = BaseDatos(this.context)
    val db: SQLiteDatabase = dbHelper.writableDatabase

    var estudiante = Estudiante(0, "", "", 0.0, false, null)
    var cursor: Cursor? = null

    cursor = db.rawQuery("SELECT * FROM t_estudiante WHERE codigoEstudiante = ${id + 1}", null)

    if (cursor.moveToFirst()) {
        do {
            estudiante.setCodigoEstudiante(cursor.getInt(0))
            estudiante.setNombreEstudiante(cursor.getString(1))
            estudiante.setFechaNacimiento(cursor.getString(2))
            estudiante.setPromedio(cursor.getDouble(3))
            estudiante.setActivo(cursor.getInt(4) == 1)
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

    //Funcion Delete
    fun deleteEstudiante(id: Int): Int {
        val dbHelper: BaseDatos = BaseDatos(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_estudiante", "codigoEstudiante=" + (id + 1), null)
    }






    //Sobreescribir la funcion

    override fun toString(): String {
        val salida =
            "codigoEstudiante: ${codigoEstudiante} \n" +
                    "nombre: ${nombreEstudiante} \n"+
                    "fechaN: ${fechaNacimiento} \n"+
                    "promedio: ${promedio}\n"+
                    "activo: ${activo}"

        return salida
    }
}