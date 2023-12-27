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
           val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
           val estudiantes = cargarEstudiantes()

           if (estudiantes.isNotEmpty()) {
               var estudianteIndex = 1
               estudiantes.forEach { estudiante ->
                   println("=== Estudiante $estudianteIndex ===")
                   println("Código: ${estudiante.codigoEstudiante}, Nombre: ${estudiante.nombreEstudiante}," +
                           " Fecha Nacimiento: ${formatoFecha.format(estudiante.fechaNacimiento)}," +
                           " Promedio: ${estudiante.promedio}, Activo: ${estudiante.activo}")
                   estudianteIndex++
               }
               println("===================")
           } else {
               println("No hay estudiantes registrados.")
           }
       }



    fun buscarEstudiantePorNombre(nombre: String): Estudiante? {
        val estudiantes = cargarEstudiantes()
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        // Utilizamos find para buscar el estudiante por nombre
        val estudianteEncontrado = estudiantes.find { it.nombreEstudiante == nombre }

        if (estudianteEncontrado != null) {
            println("Estudiante encontrado:")
            println("Código: ${estudianteEncontrado.codigoEstudiante}, Nombre: ${estudianteEncontrado.nombreEstudiante}," +
                    " Fecha Nacimiento: ${formatoFecha.format(estudianteEncontrado.fechaNacimiento)}," +
                    " Promedio: ${estudianteEncontrado.promedio}, Activo: ${estudianteEncontrado.activo}")
        } else {
            println("No se encontró un estudiante con el nombre '$nombre'.")
        }

        return estudianteEncontrado
    }


    fun actualizarEstudiantePorCodigo(codigo: Int, nuevoEstudiante: Estudiante) {
        val estudiantes = cargarEstudiantes()
        val index = estudiantes.indexOfFirst { it.codigoEstudiante == codigo }

        if (index != -1) {
            estudiantes[index] = nuevoEstudiante
            guardarEstudiantes(estudiantes)
            println("Estudiante actualizado con éxito.")
        } else {
            println("No se encontró un estudiante con ese código.")
        }
    }

    fun eliminarEstudiantePorCodigo(codigoEstudiante: Int) {
        val estudiantes = cargarEstudiantes()
        val estudianteAEliminar = estudiantes.find { it.codigoEstudiante == codigoEstudiante }

        if (estudianteAEliminar != null) {
            estudiantes.remove(estudianteAEliminar)
            guardarEstudiantes(estudiantes)
            println("Estudiante eliminado con éxito.")
        } else {
            println("No se encontró al estudiante con el código $codigoEstudiante.")
        }
    }


    fun cargarEstudiantes(): MutableList<Estudiante> {
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






