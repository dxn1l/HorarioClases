package com.example.horarioclases.DataBase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

class FirebaseClaseRepository {
    private val database = FirebaseFirestore.getInstance()

    fun addClase(clase: Clase) {
        val id = clase.id ?: throw IllegalArgumentException("Clase id cannot be null or empty")
        val newClaseRef = database.collection("clases").document(id)
        newClaseRef.set(clase)
    }

    fun removeClase(claseId: String) {
        database.collection("clases").document(claseId).delete()
    }

    fun getClases(onResult: (List<Clase>) -> Unit, onError: (FirebaseFirestoreException) -> Unit) {
        database.collection("clases").addSnapshotListener { snapshot, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val clases = snapshot.toObjects(Clase::class.java)
                onResult(clases)
            }
        }
    }
}