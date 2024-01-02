package com.example.rzexamen1

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context

class BaseDatos(context: Context?): SQLiteOpenHelper(
    context, "Examen.db", null, 1
) {
    override fun onCreate(db: SQLiteDatabase?) {
  //Crear base de datos

        val scriptSQLCrearTablaEstudiante =
            "CREATE TABLE t_estudiante(" +
                    "codigoEstudiante INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombreEstudiante TEXT NOT NULL," +
                    "fechaNacimiento TEXT NOT NULL," +
                    "promedio TEXT NOT NULL," +
                    "activo TEXT NOT NULL);"



        val scriptSQLCrearTablaMateria =
            "CREATE TABLE t_materia(" +
                    "codigoMateria INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombreMateria TEXT NOT NULL," +
                    "creditos TEXT NOT NULL," +
                    "costo TEXT NOT NULL," +
                    "esObligatorio TEXT NOT NULL, " +
                    "CodigoEstudiante INTEGER NOT NULL," +
                    "FOREIGN KEY(CodigoEstudiante) REFERENCES t_estudiante(codigoEstudiante ));"

        db?.execSQL(scriptSQLCrearTablaEstudiante)
        db?.execSQL(scriptSQLCrearTablaMateria)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS t_estudiante")
        db?.execSQL("DROP TABLE IF EXISTS t_materia")
        onCreate(db)
    }


}