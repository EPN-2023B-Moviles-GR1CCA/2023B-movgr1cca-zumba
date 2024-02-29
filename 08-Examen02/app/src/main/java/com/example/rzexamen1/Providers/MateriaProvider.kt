package com.example.rzexamen1.Providers

import Materia

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MateriaProvider {
    val db = Firebase.firestore.collection("Materia")
    var storage = FirebaseStorage.getInstance().getReference().child("profile")

    fun create(materia: Materia): Task<Void> {
        return db.document(materia.codigoMateria!!.toString()).set(materia)
    }
}