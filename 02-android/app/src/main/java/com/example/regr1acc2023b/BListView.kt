package com.example.regr1acc2023b

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Snackbar

class BListView : AppCompatActivity() {
//Crear arreglo
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
//crear adaptador
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_andir_list_view)
        botonAnadirListView.setOnClickListener { anadirEntrenador(adaptador) }
    }

    var positionItemSelecionado = 0

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        val info = menuInfo as  AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        positionItemSelecionado = posicion
    }

  fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
      arreglo.add(BEntrenador(2,"Josue", "b@b.com"))
      adaptador.notifyDataSetChanged()
  }

    fun mostrarSnacbark(texto:String){
        Snackbar().make(findViewById( R.id.lv_list_view), texto, Snackbar.LENGTH_LONG).show()


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId){
            R.id.mi_editar ->{
                mostrarSnacbark("${positionItemSelecionado}")
                return  true
            }
            R.id.mi_eliminar->{
                mostrarSnacbark("${positionItemSelecionado}")
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun abrirDialogo(){
        val builder = AlertDialog().Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which -> mostrarSnacbark("Eliminar Aceptado") })

        builder.setNegativeButton("Cancelar", null)

        val opciones  = resources.getStringArray(R.array.string_array_opciones_dialogo)
        val seleccionPrevia = booleanArrayOf(true, false, false)
        builder.setMultiChoiceItems(opciones, seleccionPrevia{
            dialog, which, isChecked->
            mostrarSnacbark("Dio clicc en el item ")
        })
    }



}

