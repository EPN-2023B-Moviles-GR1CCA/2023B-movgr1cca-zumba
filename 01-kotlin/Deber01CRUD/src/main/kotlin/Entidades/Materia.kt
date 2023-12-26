package Entidades

import java.io.Serializable

//Definicion de los atributos en la clase Materia
class Materia(
    var codigoMateria: Int,
    var nombreMateria: String,
    var  creditos: Int,
    var costo: Double,
    var esObligatoria: Boolean

): Serializable {


}