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
                println("**** Materia ${index + 1} ******")
                println("Código: ${materia.codigoMateria}, Nombre: ${materia.nombreMateria}," +
                        " Créditos: ${materia.creditos}, Costo: ${materia.costo}, Obligatoria: ${materia.esObligatoria}")
            }
            println("===================")
        } else {
            println("No hay materias registradas.")
        }
    }


    fun buscarMateriaPorNombre(nombre: String): List<Materia> {
        val materias = cargarMaterias()
        val materiasEncontradas = materias.filter { it.nombreMateria.contains(nombre, ignoreCase = true) }

        if (materiasEncontradas.isNotEmpty()) {
            println("=== Materias Encontradas ===")
            for ((index, materia) in materiasEncontradas.withIndex()) {
                println("=== Materia ${index + 1} ===")
                val infoMateria = buildString {
                    append("Código: ${materia.codigoMateria}, ")
                    append("Nombre: ${materia.nombreMateria}, ")
                    append("Créditos: ${materia.creditos}, ")
                    append("Costo: ${materia.costo}, ")
                    append("Obligatoria: ${materia.esObligatoria}")
                }
                println(infoMateria)
            }
            println("===========================")
        } else {
            println("No se encontraron materias con el nombre: $nombre")
        }

        return materiasEncontradas
    }



    fun actualizarMateriaPorCodigo(codigo: Int, nuevaMateria: Materia) {
        val materias = cargarMaterias()
        val index = materias.indexOfFirst { it.codigoMateria == codigo }

        if (index != -1) {
            materias[index] = nuevaMateria
            guardarMaterias(materias)
            println("Materia actualizada con éxito.")
        } else {
            println("No se encontró una materia con ese código.")
        }
    }



    fun eliminarMateriaPorCodigo(codigoMateria: Int) {
        val materias = cargarMaterias()
        val materiaAEliminar = materias.find { it.codigoMateria == codigoMateria }

        if (materiaAEliminar != null) {
            materias.remove(materiaAEliminar)
            guardarMaterias(materias)
            println("Materia eliminada con éxito.")
        } else {
            println("No se encontró la materia con el código $codigoMateria.")
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