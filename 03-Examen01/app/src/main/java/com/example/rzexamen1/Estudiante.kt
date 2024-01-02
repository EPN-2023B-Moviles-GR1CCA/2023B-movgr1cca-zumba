package com.example.rzexamen1

import android.content.Context
class Estudiante(
    var codigoEstudiante: Int,
    var nombreEstudiante: String?,
    var fechaNacimiento: String?,
    var promedio: Double?,
    var activo: Boolean?,
    val context: Context?
) {


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