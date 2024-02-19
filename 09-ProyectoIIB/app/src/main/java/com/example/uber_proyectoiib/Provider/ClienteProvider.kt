package com.example.uber_proyectoiib.Provider

import com.example.uber_proyectoiib.Modelos.Cliente
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class ClienteProvider {
    val db = Firebase.firestore.collection("Clientes")
    var storage = FirebaseStorage.getInstance().getReference().child("profile")

    fun create(cliente: Cliente): Task<Void> {
        return db.document(cliente.id!!).set(cliente)
    }
}