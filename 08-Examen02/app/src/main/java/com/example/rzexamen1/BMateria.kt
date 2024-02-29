package com.example.rzexamen1

import Materia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class BMateria : AppCompatActivity() {
    var idItemSeleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmateria)

        val nombre = findViewById<EditText>(R.id.ed_nombreMateria)
        nombre.requestFocus()
        val creditos = findViewById<EditText>(R.id.ed_creditos)
        val costo = findViewById<EditText>(R.id.edt_costo)
        val obligatorio = findViewById<EditText>(R.id.edt_esObligatorio)
        val codigoEs = findViewById<EditText>(R.id.ed_codigoEstudinateMateria)

        val btnInsertar = findViewById<Button>(R.id.btn_InsertarMateria)
        btnInsertar.setOnClickListener {

            val materia = Materia(
                null,
                nombre.text.toString(),
                creditos.text.toString(),
                costo.text.toString(),
                obligatorio.text.toString(),
                codigoEs.text.toString().toInt()
            )

            insertarMateriaEnFirestore(materia)
        }
    }

    private fun insertarMateriaEnFirestore(materia: Materia) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("materias")
            .add(materia.toMap())
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Materia agregada con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
                cleanEditText()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agregar materia: $e", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_materia, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position.toString()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarmateria -> {
                "$idItemSeleccionado"
                return true
            }
            R.id.mi_eliminarmaterias -> {
                "$idItemSeleccionado"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Limpiar los campos
    private fun cleanEditText() {
        findViewById<EditText>(R.id.ed_nombreMateria).setText("")
        findViewById<EditText>(R.id.ed_creditos).setText("")
        findViewById<EditText>(R.id.edt_costo).setText("")
        findViewById<EditText>(R.id.edt_esObligatorio).setText("")
        findViewById<EditText>(R.id.ed_codigoEstudinateMateria).setText("")
    }

    // Convertir materia a un mapa para almacenar en Firestore
    private fun Materia.toMap(): Map<String, Any?> {
        return hashMapOf(
            "nombreMateria" to nombreMateria,
            "creditos" to creditos,
            "costo" to costo,
            "esObligatorio" to esObligatorio,
            "CodigoEstudiante" to CodigoEstudiante
        )
    }
}
