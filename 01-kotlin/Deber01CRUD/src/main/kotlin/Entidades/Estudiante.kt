package Entidades

import java.io.Serializable
import java.util.*

//Definicion de los atributos en la clase Estudiante
class Estudiante(var codigoEstudiante: Int,
                 var nombreEstudiante: String,
                 var fechaNacimiento: Date,
                 var  promedio: Double,
                 var activo: Boolean): Serializable {
    override fun toString(): String {
        return "Código: $codigoEstudiante, Nombre: $nombreEstudiante, Fecha Nacimiento: $fechaNacimiento, Promedio: $promedio, Activo: $activo"
    }

}