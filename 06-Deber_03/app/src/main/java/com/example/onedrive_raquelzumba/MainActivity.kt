package com.example.onedrive_raquelzumba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listaDirectorios = arrayListOf<Directorio>()
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Seguridad Informatica","16 GB","Mar 20, 2021"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Aplicaciones Moviles","25 GB","Feb 10, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Ingles","10 GB","Jun 11, 2022"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Base de datos","4 GB","Feb 03, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Emprendimiento","10 GB","Jun 06, 2024"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"HCI","40 GB","Feb 03, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Redes de computadores","10 GB","Jun 06, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Tesis","12 GB","Feb 12, 2024"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Redes de computadores","10 GB","Jun 06, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Tesis","12 GB","Feb 12, 2024"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Tesis","12 GB","Feb 12, 2024"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Redes de computadores","10 GB","Jun 06, 2023"))
        listaDirectorios.add(Directorio(R.drawable.ic_baseline_folder_24,"Tesis","12 GB","Feb 12, 2024"))


        val recyclerView = findViewById<RecyclerView>(R.id.rvDirectorio)
        initRecyclerView(listaDirectorios, recyclerView)
    }
    private fun initRecyclerView(listaDirectorios: ArrayList<Directorio>, recyclerView: RecyclerView) {
        val adapter = Adaptador1(listaDirectorios,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, ArchivoAct::class.java)
            startActivity(intent)
        }
    }
}