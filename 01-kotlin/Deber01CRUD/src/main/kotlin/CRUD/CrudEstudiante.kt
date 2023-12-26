package CRUD

import Entidades.Estudiante
import java.io.File
import java.text.SimpleDateFormat

class CrudEstudiante() {

        private val archivo = File("estudiantes.txt")

        private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        fun crearEstudiante(estudiante: Estudiante) {
            val estudiantes = cargarEstudiantes()
            estudiantes.add(estudiante)
            guardarEstudiantes(estudiantes)
            println("Estudiante creado con éxito.")
        }

        fun listarEstudiantes() {
            val estudiantes = cargarEstudiantes()
            if (estudiantes.isNotEmpty()) {
                for ((index, estudiante) in estudiantes.withIndex()) {
                    println("=== Estudiante ${index + 1} ===")
                    println(estudiante)
                }
            } else {
                println("No hay estudiantes registrados.")
            }
        }

        fun actualizarEstudiante(index: Int, nuevoEstudiante: Estudiante) {
            val estudiantes = cargarEstudiantes()
            if (index >= 0 && index < estudiantes.size) {
                estudiantes[index] = nuevoEstudiante
                guardarEstudiantes(estudiantes)
                println("Estudiante actualizado con éxito.")
            }
        }

        fun eliminarEstudiante(index: Int) {
            val estudiantes = cargarEstudiantes()
            if (index >= 0 && index < estudiantes.size) {
                estudiantes.removeAt(index)
                guardarEstudiantes(estudiantes)
                println("Estudiante eliminado con éxito.")
            } else {
                println("Índice del estudiante es inválido.")
            }
        }

        private fun cargarEstudiantes(): MutableList<Estudiante> {
            val estudiantes: MutableList<Estudiante> = mutableListOf()
            if (archivo.exists()) {
                archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                    reader.lineSequence().forEach { line ->
                        val campos = line.split(",")
                        if (campos.size >= 5) {
                            val codigoEstudiante = campos[0].toInt()
                            val nombre = campos[1]
                            val fechaNacimiento = dateFormat.parse(campos[2])
                            val activo = campos[3].toBoolean()
                            val promedio = campos[4].toDouble()
                            val estudiante = Estudiante(codigoEstudiante, nombre, fechaNacimiento, promedio, activo)
                            estudiantes.add(estudiante)
                        }
                    }
                }
            }
            return estudiantes
        }

        private fun guardarEstudiantes(estudiantes: List<Estudiante>) {
            archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
                for (estudiante in estudiantes) {
                    writer.write("${estudiante.codigoEstudiante},${estudiante.nombreEstudiante}," +
                            "${dateFormat.format(estudiante.fechaNacimiento)},${estudiante.activo},${estudiante.promedio}")
                    writer.newLine()
                }
            }
        }
    }






