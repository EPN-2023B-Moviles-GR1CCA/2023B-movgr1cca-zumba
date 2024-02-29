package com.example.rzexamen1.Providers

import com.example.rzexamen1.Estudiante
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class EstudianteProvider {
    val db = Firebase.firestore.collection("Estudiante")
    var storage = FirebaseStorage.getInstance().getReference().child("profile")

    fun create(estudiante: Estudiante): Task<Void> {
        return db.document(estudiante.codigoEstudiante!!.toString()).set(estudiante)
    }
}