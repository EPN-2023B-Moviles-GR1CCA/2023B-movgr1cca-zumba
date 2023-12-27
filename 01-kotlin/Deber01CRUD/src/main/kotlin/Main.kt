import java.text.SimpleDateFormat
import java.util.*
import CRUD.CrudEstudiante
import CRUD.CrudMateria
import Entidades.Estudiante
import Entidades.Materia

fun main() {
    val scanner = Scanner(System.`in`)
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val materiaCRUD = CrudMateria()
    val estudianteCRUD = CrudEstudiante()

    loop@ while (true) {
        println("****** ESCOJA LA OPCION QUE DESEA REALIZAR *********")
        println("1. Ver Estudiante")
        println("2. Ver Materia")
        println("3. Salir")
        println("****** Ingrese el número de la opción que desea realizar:")

        when (scanner.nextInt()) {
            1 -> {
                while (true) {
                    println("******* Selecione una opcion para Estudiante ******")
                    println("1. Crear Estudiante")
                    println("2. Listar Estudiantes")
                    println("3. Listar Estudiante por Nombre")
                    println("4. Actualizar Estudiante")
                    println("5. Eliminar Estudiante por código")
                    println("6. Volver al menú principal")
                    println("****** Ingrese la opción deseada:")

                    when (scanner.nextInt()) {
                        1 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el código del estudiante:")
                            val codigoEstudiante = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            println("Ingrese el nombre del estudiante:")
                            val nombre = scanner.nextLine()

                            println("Ingrese la fecha de nacimiento (dd/MM/yyyy):")
                            val fechaNacimientoStr = scanner.nextLine()
                            val fechaNacimiento = formatoFecha.parse(fechaNacimientoStr)

                            println("Ingrese el promedio del estudiante:")
                            val promedio = scanner.nextDouble()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            println("Estudiante activo? (True/False):")
                            val activo = scanner.nextBoolean()

                            val estudiante = Estudiante(codigoEstudiante, nombre, fechaNacimiento, promedio, activo)
                            estudianteCRUD.crearEstudiante(estudiante)
                        }

                        2 -> {
                            println("**** Mostrando todos los Estudiantes ******")
                            estudianteCRUD.listarEstudiantes()
                            println("===================")

                        }
                        3 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el nombre del estudiante a buscar:")
                            val nombreBusqueda = scanner.nextLine()
                            estudianteCRUD.buscarEstudiantePorNombre(nombreBusqueda)
                        }

                        4 -> {
                            println("Ingrese el código del estudiante a actualizar:")
                            val codigo = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val estudiantes = estudianteCRUD.cargarEstudiantes()
                            val estudianteAActualizar = estudiantes.find { it.codigoEstudiante == codigo }

                            if (estudianteAActualizar != null) {
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
                                    codigo,
                                    nuevoNombre,
                                    nuevaFechaNacimiento,
                                    nuevoPromedio,
                                    nuevoActivo
                                )

                                estudianteCRUD.actualizarEstudiantePorCodigo(codigo, nuevoEstudiante)
                            } else {
                                println("No se encontró un estudiante con ese código.")
                            }
                        }
                        5 -> {
                            println("Ingrese el código del estudiante a eliminar:")
                            val codigo = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val estudiantes = estudianteCRUD.cargarEstudiantes()
                            val estudianteAEliminar = estudiantes.find { it.codigoEstudiante == codigo }
                            if (estudianteAEliminar != null) {
                                estudianteCRUD.eliminarEstudiantePorCodigo(codigo)
                            } else {
                                println("No se encontró un estudiante con ese código.")
                            }
                        }
                        6 -> break  // Salir del CRUD de estudiantes
                        else -> println("Opción inválida.")
                    }
                }
            }
            2 -> {
                while (true) {
                    println("***** Seleccione una opcion para Materia ******")
                    println("1. Crear Materia")
                    println("2. Listar Materias")
                    println("3. Listar  Materia por Nombre")
                    println("4. Actualizar Materia")
                    println("5. Eliminar Materia por código")
                    println("6. Volver al menú principal")
                    println("Ingrese la opción deseada:")
                    when (scanner.nextInt()) {
                        1 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el código de la Materia:")
                            val codigoMateria = scanner.nextInt()
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

                            val materia = Materia(codigoMateria, nombreMateria, creditos, costo, esObligatoria)
                            materiaCRUD.crearMateria(materia)
                        }
                        2 -> {
                            println("***** Mostrando todas las Materias ******")
                            materiaCRUD.listarMaterias()
                            println("================")
                        }
                        3 -> {
                            scanner.nextLine() // Consumir el salto de línea pendiente
                            println("Ingrese el nombre de la materia a buscar:")
                            val nombreMateriaBuscar = scanner.nextLine()
                            val materiasEncontradas = materiaCRUD.buscarMateriaPorNombre(nombreMateriaBuscar)

                        }
                        4 -> {
                            println("Ingrese el código de la materia a actualizar:")
                            val codigo = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val materias = materiaCRUD.cargarMaterias()
                            val materiaAActualizar = materias.find { it.codigoMateria == codigo }

                            if (materiaAActualizar != null) {
                                println("Ingrese el nuevo nombre de la materia:")
                                val nuevoNombreMateria = scanner.nextLine()

                                println("Ingrese la nueva cantidad de créditos de la materia:")
                                val nuevosCreditos = scanner.nextInt()
                                scanner.nextLine() // Consumir el salto de línea pendiente

                                println("Ingrese el nuevo costo de la materia:")
                                val nuevoCosto = scanner.nextDouble()
                                scanner.nextLine() // Consumir el salto de línea pendiente

                                println("La materia es obligatoria? (Si/No):")
                                val nuevaEsObligatoria = scanner.nextBoolean()

                                val nuevaMateria = Materia(
                                    codigo,
                                    nuevoNombreMateria,
                                    nuevosCreditos,
                                    nuevoCosto,
                                    nuevaEsObligatoria
                                )

                                materiaCRUD.actualizarMateriaPorCodigo(codigo, nuevaMateria)
                            } else {
                                println("No se encontró una materia con ese código.")
                            }
                        }
                        5 -> {
                            println("Ingrese el código de la materia a eliminar:")
                            val codigo = scanner.nextInt()
                            scanner.nextLine() // Consumir el salto de línea pendiente

                            val materias = materiaCRUD.cargarMaterias()
                            val materiaAEliminar = materias.find { it.codigoMateria == codigo }
                            if (materiaAEliminar != null) {
                                materiaCRUD.eliminarMateriaPorCodigo(codigo)
                            } else {
                                println("No se encontró una materia con ese código.")
                            }
                        }
                        6 -> break  // Salir del CRUD de materias
                        else -> println("Opción inválida.")
                    }
                }
            }
            3 -> break
            else -> println("Opción inválida.")
        }
    }
    println("¡Gracias por la Interacción!")
}
