package com.example.rzexamen1

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class Estudiante(
    var codigoEstudiante: Int?,
    var nombreEstudiante: String?,
    var fechaNacimiento: String?,
    var promedio: String,
    var activo: String,
    val context: Context?
) {
    companion object {
        private const val TAG = "Estudiante"
    }
    fun setcodigoEstudiante(codigoEstudiante: Int) {
        this.codigoEstudiante = codigoEstudiante
    }

    fun setnombreEstudiante(nombreEstudiante: String?) {
        this.nombreEstudiante = nombreEstudiante
    }

    fun setfechaNacimiento(fechaNacimiento: String?) {
        this.fechaNacimiento = fechaNacimiento
    }

    fun setpromedio(promedio: String) {
        this.promedio = promedio
    }

    fun setactivo(activo: String) {
        this.activo = activo
    }

    // Métodos get

    fun getcodigoEstudiante(): Int? {
        return codigoEstudiante
    }

    fun getnombreEstudiante(): String? {
        return nombreEstudiante
    }

    fun getfechaNacimiento(): String? {
        return fechaNacimiento
    }

    fun getpromedio(): String {
        return promedio
    }

    fun getactivo(): String {
        return activo
    }


    // Función para insertar un estudiante en Firestore
    fun insertEstudiante() {
        val firestore = FirebaseFirestore.getInstance()
        val estudiante = hashMapOf(
            "codigoEstudiante" to codigoEstudiante,
            "nombreEstudiante" to nombreEstudiante,
            "fechaNacimiento" to fechaNacimiento,
            "promedio" to promedio,
            "activo" to activo
        )

        firestore.collection("estudiantes")
            .add(estudiante)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Estudiante agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar estudiante", e)
            }
    }

    // Función para actualizar un estudiante en Firestore
    fun updateEstudiante(callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("estudiantes")
            .whereEqualTo("codigoEstudiante", codigoEstudiante)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val data = hashMapOf(
                            "nombreEstudiante" to nombreEstudiante,
                            "fechaNacimiento" to fechaNacimiento,
                            "promedio" to promedio,
                            "activo" to activo
                        )

                        firestore.collection("estudiantes")
                            .document(document.id)
                            .update(data as Map<String, Any>)
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al actualizar estudiante", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al actualizar estudiante", exception)
                callback(false)
            }
    }

    // Función para eliminar un estudiante en Firestore
    fun deleteEstudiante(callback: (Boolean) -> Unit){
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("estudiantes")
            .whereEqualTo("codigoEstudiante", codigoEstudiante)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        firestore.collection("estudiantes")
                            .document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al eliminar estudiante", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al eliminar estudiante", exception)
                callback(false)
            }
    }

    // Función para mostrar todos los estudiantes en Firestore
    fun showEstudiantes(callback: (ArrayList<Estudiante>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val listaEstudiantes = ArrayList<Estudiante>()

        firestore.collection("estudiantes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val estudiante = Estudiante(
                        document["codigoEstudiante"] as Int?,
                        document["nombreEstudiante"] as String?,
                        document["fechaNacimiento"] as String?,
                        document["promedio"] as String,
                        document["activo"] as String,
                        context
                    )
                    listaEstudiantes.add(estudiante)
                }
                callback(listaEstudiantes)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener estudiantes", exception)
            }
    }
    fun getEstudianteById(id: Int, callback: (Estudiante?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("estudiantes")
            .whereEqualTo("codigoEstudiante", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents[0] // Suponiendo que solo hay un estudiante con ese ID
                    val estudiante = Estudiante(
                        document["codigoEstudiante"] as Int?,
                        document["nombreEstudiante"] as String?,
                        document["fechaNacimiento"] as String?,
                        document["promedio"] as String,
                        document["activo"] as String,
                        context
                    )
                    callback(estudiante)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener estudiante por ID", exception)
                callback(null)
            }
    }

}
