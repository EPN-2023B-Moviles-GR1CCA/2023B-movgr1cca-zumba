package com.example.exameniib


import android.util.Log

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Materia(
    var codigoMateria: Int?,
    var nombreMateria: String?,
    var creditos: String?,
    var costo: String?,
    var esObligatorio: String?,
    var CodigoEstudiante: Int?
) {

    companion object {
        private const val TAG = "Materia"
    }
    fun setcodigoMateria(codigoMateria: Int){
        this.codigoMateria = codigoMateria
    }

    fun setnombreMateria(nombreMateria: String){
        this.nombreMateria = nombreMateria
    }


    fun setcreditos(creditos: String): Unit{
        this.creditos = creditos
    }

    fun setcosto(costo: String){
        this.costo = costo
    }

    fun setesObligatorio(esObligatorio: String){
        this.esObligatorio = esObligatorio
    }

    fun setcodigoEstudiante(codigoEstudiante: Int){
        this.CodigoEstudiante = codigoEstudiante
    }

    //Metodo get


    fun getcodigoMateria(): Int? {
        return  codigoMateria
    }

    fun getcodigoEstudiante(): Int? {
        return CodigoEstudiante
    }


    fun getnombreMateria(): String? {
        return nombreMateria
    }


    fun getcosto(): String?{
        return costo
    }

    fun getcreditos(): String? {
        return creditos
    }

    fun getesObligatorio(): String?{
        return esObligatorio
    }

    // Función para insertar una materia en Firestore
    fun insertMateria() {
        val db = Firebase.firestore
        val materia = hashMapOf(
            "codigoMateria" to this.codigoMateria,
            "nombreMateria" to this.nombreMateria,
            "creditos" to this.creditos,
            "costo" to this.costo,
            "esObligatorio" to this.esObligatorio,
            "CodigoEstudiante" to this.CodigoEstudiante
        )

        db.collection("materias")
            .add(materia)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Materia agregada con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar materia", e)
            }
    }

    // Función para mostrar todas las materias desde Firestore
    fun showMaterias(callback: (ArrayList<Materia>) -> Unit) {
        val db = Firebase.firestore
        val lista = ArrayList<Materia>()

        db.collection("materias")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val materia = Materia(
                        document["codigoMateria"] as Int?,
                        document["nombreMateria"] as String?,
                        document["creditos"] as String?,
                        document["costo"] as String?,
                        document["esObligatorio"] as String?,
                        document["CodigoEstudiante"] as? Int ?: 0
                    )
                    lista.add(materia)
                }
                callback(lista)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener materias", exception)
            }
    }

    // Función para obtener una materia por su ID desde Firestore
    fun getMateriaById(id: String, callback: (Materia?) -> Unit) {
        val db = Firebase.firestore
        db.collection("materias")
            .whereEqualTo("codigoMateria", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val materia = Materia(
                            document["codigoMateria"] as Int?,
                            document["nombreMateria"] as String?,
                            document["creditos"] as String?,
                            document["costo"] as String?,
                            document["esObligatorio"] as String?,
                            document["CodigoEstudiante"] as Int
                        )
                        callback(materia)
                        return@addOnSuccessListener
                    }
                }
                callback(null)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener materia", exception)
                callback(null)
            }
    }

    // Función para eliminar una materia en Firestore
    fun deleteMateria(id: Int, callback: (Boolean) -> Unit) {
        val db = Firebase.firestore
        db.collection("materias")
            .whereEqualTo("codigoMateria", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        db.collection("materias")
                            .document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al eliminar materia", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al eliminar materia", exception)
                callback(false)
            }
    }

    // Función para actualizar una materia en Firestore
    fun updateMateria(callback: (Boolean) -> Unit) {
        val db = Firebase.firestore
        db.collection("materias")
            .whereEqualTo("codigoMateria", codigoMateria)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val data = hashMapOf(
                            "nombreMateria" to nombreMateria,
                            "creditos" to creditos,
                            "costo" to costo,
                            "esObligatorio" to esObligatorio
                        )

                        db.collection("materias")
                            .document(document.id)
                            .update(data as Map<String, Any>)
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al actualizar materia", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al actualizar materia", exception)
                callback(false)
            }
    }

    // Método toString
    override fun toString(): String {
        return "Codigo: $codigoMateria\n" +
                "Nombre Materia: $nombreMateria\n" +
                "Creditos: $creditos\n" +
                "Costo: $costo\n" +
                "Es obligatorio: $esObligatorio\n" +
                "Codigo Estudiante: $CodigoEstudiante"
    }
}
