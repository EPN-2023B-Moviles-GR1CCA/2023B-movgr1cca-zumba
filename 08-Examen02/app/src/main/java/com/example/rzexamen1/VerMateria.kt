package com.example.rzexamen1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class VerMateria : AppCompatActivity() {

    companion object {
        var idMateriaSeleccionada = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_materia)

        val idEstudiante = BEstudiante.idSeleccionado
        val idEstudianteAux = Estudiante(null, "", "", "", "", this)

       /* val textViewPadre = findViewById<TextView>(R.id.tv_padreVerMaterias)
        textViewPadre.text = "Padre:" + idEstudianteAux.getEstudianteById(idEstudiante).getnombreEstudiante()*/

        val btnCrearMaterias = findViewById<Button>(R.id.btn_CrearMaterias)
        btnCrearMaterias.setOnClickListener {
            irActividad(BMateria::class.java)
        }

        showListView(idEstudiante)
    }

    private fun showListView(id: Int) {
        val listviewMaterias = findViewById<ListView>(R.id.lv_Materia)
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("materias")
            .whereEqualTo("CodigoEstudiante", id)
            .get()
            .addOnSuccessListener { documents ->
                val materias = ArrayList<String>()
                val materiaIds = ArrayList<String>()

                for (document in documents) {
                    val nombreMateria = document.getString("nombreMateria") ?: ""
                    materias.add(nombreMateria)
                    materiaIds.add(document.id)
                }

                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    materias
                )
                listviewMaterias.adapter = adaptador

                listviewMaterias.setOnItemClickListener { _, _, position, _ ->
                    idMateriaSeleccionada = materiaIds[position]
                }

                registerForContextMenu(listviewMaterias)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al obtener materias: $exception", Toast.LENGTH_LONG).show()
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
        idMateriaSeleccionada = info.position.toString()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarmateria -> {
                // Aquí puedes implementar la lógica para editar la materia
                return true
            }
            R.id.mi_eliminarmaterias -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta materia?")
        builder.setPositiveButton("SI") { _, _ ->
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("materias").document(idMateriaSeleccionada)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Materia eliminada correctamente", Toast.LENGTH_LONG).show()
                    val idEstudiante = BEstudiante.idSeleccionado
                    showListView(idEstudiante)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al eliminar materia: $e", Toast.LENGTH_LONG).show()
                }
        }
        builder.setNegativeButton("NO", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
