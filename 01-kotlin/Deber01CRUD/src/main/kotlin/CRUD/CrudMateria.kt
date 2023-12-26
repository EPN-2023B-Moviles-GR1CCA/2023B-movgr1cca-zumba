package CRUD

import Entidades.Materia
import java.io.File

class CrudMateria {

    private val archivo = File("materias.txt")

    fun crearMateria(materia: Materia) {
        val materias = cargarMaterias()
        materias.add(materia)
        guardarMaterias(materias)
        println("Materia creada con éxito.")
    }

    fun listarMaterias() {
        val materias = cargarMaterias()
        if (materias.isNotEmpty()) {
            for ((index, materia) in materias.withIndex()) {
                println("=== Materia ${index + 1} ===")
                println(materia)
            }
        } else {
            println("No hay materias registradas.")
        }
    }

    fun actualizarMateria(index: Int, nuevaMateria: Materia) {
        val materias = cargarMaterias()
        if (index >= 0 && index < materias.size) {
            materias[index] = nuevaMateria
            guardarMaterias(materias)
            println("Materia actualizada con éxito.")
        }
    }


    fun eliminarMateria(index: Int) {
        val materias = cargarMaterias()
        if (index >= 0 && index < materias.size) {
            materias.removeAt(index)
            guardarMaterias(materias)
            println("Materia eliminada con éxito.")
        } else {
            println("Índice de la materia es inválido.")
        }
    }
    fun cargarMaterias(): MutableList<Materia> {
        val materias: MutableList<Materia> = mutableListOf()
        if (archivo.exists()) {
            archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                reader.lineSequence().forEach { line ->
                    val campos = line.split(",")
                    if (campos.size >= 5) {
                        val codigoMateria = campos[0].toInt()
                        val nombreMateria = campos[1]
                        val creditos = campos[2].toInt()
                        val costo = campos[3].toDouble()
                        val esObligatoria = campos[4].toBoolean()
                        val materia = Materia(codigoMateria, nombreMateria, creditos, costo, esObligatoria)
                        materias.add(materia)
                    }
                }
            }
        }
        return materias
    }

    private fun guardarMaterias(materias: List<Materia>) {
        archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            for (materia in materias) {
                writer.write("${materia.codigoMateria},${materia.nombreMateria}," +
                        "${materia.creditos},${materia.costo},${materia.esObligatoria}")
                writer.newLine()
            }
        }
    }

}