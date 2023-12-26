import java.text.SimpleDateFormat
import java.util.*
import CRUD.CrudEstudiante
import CRUD.CrudMateria
import Entidades.Estudiante
import Entidades.Materia

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val materiaCRUD = CrudMateria()
    val estudianteCRUD = CrudEstudiante()

    loop@ while (true) {
        println("==== MENÚ ====")
        println("1. CRUD Estudiante")
        println("2. CRUD Materia")
        println("3. Salir")
        println("Ingrese la opción deseada:")

        when (scanner.nextInt()) {
            1 -> {
                val estudianteCRUD = CrudEstudiante()

                while (true) {
                    println("==== CRUD Estudiante ====")
                    println("1. Crear Estudiante")
                    println("2. Listar Estudiantes")
                    println("3. Actualizar Estudiante")
                    println("4. Eliminar Estudiante")
                    println("5. Volver al menú principal")
                    println("Ingrese la opción deseada:")

                    when (scanner.nextInt()) {
                        1 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el nombre del estudiante:")
                            val nombre = scanner.nextLine()
                            println("Ingrese la fecha de nacimiento (dd/MM/yyyy):")
                            val fechaNacimientoStr = scanner.nextLine()
                            val fechaNacimiento = formatoFecha.parse(fechaNacimientoStr)
                            println("Ingrese el promedio del estudiante:")
                            val promedio = scanner.nextDouble()
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Estudiante activo? (true/false):")
                            val activo = scanner.nextBoolean()

                            val estudiante = Estudiante(0, nombre, fechaNacimiento, promedio, activo)
                            estudianteCRUD.crearEstudiante(estudiante)
                        }
                        2 -> {
                            println("=== Estudiantes ===")
                            estudianteCRUD.listarEstudiantes()
                            println("===================")
                        }
                        3 -> {
                            println("Ingrese el índice del estudiante a actualizar:")
                            val index = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val estudiantes = estudianteCRUD.cargarEstudiantes()
                            if (index >= 0 && index < estudiantes.size) {
                                scanner.nextLine() // Consumir el salto de línea pendiente
                                println("Ingrese el nuevo nombre del estudiante:")
                                val nuevoNombre = scanner.nextLine()
                                println("Ingrese la nueva fecha de nacimiento (dd/MM/yyyy):")
                                val nuevaFechaNacimientoStr = scanner.nextLine()
                                val nuevaFechaNacimiento = formatoFecha.parse(nuevaFechaNacimientoStr)
                                println("Ingrese el nuevo promedio del estudiante:")
                                val nuevoPromedio = scanner.nextDouble()
                                scanner.nextLine() // Consumir el salto de línea pendiente
                                println("El estudiante está activo? (true/false):")
                                val nuevoActivo = scanner.nextBoolean()

                                val nuevoEstudiante = Estudiante(
                                    estudiantes[index].codigoEstudiante,
                                    nuevoNombre,
                                    nuevaFechaNacimiento,
                                    nuevoPromedio,
                                    nuevoActivo
                                )
                                estudianteCRUD.actualizarEstudiante(index, nuevoEstudiante)
                            } else {
                                println("Índice del estudiante inválido.")
                            }
                        }
                        4 -> {
                            println("Ingrese el índice del estudiante a eliminar:")
                            val index = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val estudiantes = estudianteCRUD.cargarEstudiantes()
                            if (index >= 0 && index < estudiantes.size) {
                                estudianteCRUD.eliminarEstudiante(index)
                                println("Estudiante eliminado con éxito.")
                            } else {
                                println("Índice del estudiante inválido.")
                            }
                        }
                        5 -> break  // Salir del CRUD de estudiantes
                        else -> println("Opción inválida.")
                    }
                }
            }
            2 -> {
                val materiaCRUD = CrudMateria()

                while (true) {
                    println("==== CRUD Materia ====")
                    println("1. Crear Materia")
                    println("2. Listar Materias")
                    println("3. Actualizar Materia")
                    println("4. Eliminar Materia")
                    println("5. Volver al menú principal")
                    println("Ingrese la opción deseada:")

                    when (scanner.nextInt()) {
                        1 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el nombre de la materia:")
                            val nombreMateria = scanner.nextLine()
                            println("Ingrese la cantidad de créditos de la materia:")
                            val creditos = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el costo de la materia:")
                            val costo = scanner.nextDouble()
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("La materia es obligatoria? (true/false):")
                            val esObligatoria = scanner.nextBoolean()

                            val materia = Materia(0, nombreMateria, creditos, costo, esObligatoria)
                            materiaCRUD.crearMateria(materia)
                        }
                        2 -> {
                            println("=== Materias ===")
                            materiaCRUD.listarMaterias()
                            println("================")
                        }
                        3 -> {
                            println("Ingrese el índice de la materia a actualizar:")
                            val index = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val materias = materiaCRUD.cargarMaterias()
                            if (index >= 0 && index < materias.size) {
                                scanner.nextLine() // Consumir el salto de línea pendiente
                                println("Ingrese el nuevo nombre de la materia:")
                                val nuevoNombreMateria = scanner.nextLine()
                                println("Ingrese la nueva cantidad de créditos de la materia:")
                                val nuevosCreditos = scanner.nextInt()
                                scanner.nextLine() // Consumir el salto de línea pendiente
                                println("Ingrese el nuevo costo de la materia:")
                                val nuevoCosto = scanner.nextDouble()
                                scanner.nextLine() // Consumir el salto de línea pendiente
                                println("La materia es obligatoria? (true/false):")
                                val nuevaEsObligatoria = scanner.nextBoolean()

                                val nuevaMateria = Materia(
                                    materias[index].codigoMateria,
                                    nuevoNombreMateria,
                                    nuevosCreditos,
                                    nuevoCosto,
                                    nuevaEsObligatoria
                                )
                                materiaCRUD.actualizarMateria(index, nuevaMateria)
                            } else {
                                println("Índice de la materia inválido.")
                            }
                        }
                        4 -> {
                            println("Ingrese el índice de la materia a eliminar:")
                            val index = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val materias = materiaCRUD.cargarMaterias()
                            if (index >= 0 && index < materias.size) {
                                materiaCRUD.eliminarMateria(index)
                                println("Materia eliminada con éxito.")
                            } else {
                                println("Índice de la materia inválido.")
                            }
                        }
                        5 -> break  // Salir del CRUD de materias
                        else -> println("Opción inválida.")
                    }
                }
            }
            3 -> break@loop
            else -> println("Opción inválida.")
        }
    }

    println("¡Gracias por la Interacción!")
}
