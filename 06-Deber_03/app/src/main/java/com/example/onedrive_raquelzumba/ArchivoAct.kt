package com.example.onedrive_raquelzumba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class ArchivoAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archivo)
        var listaArchivos = arrayListOf<Archivo>()
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 1","25 MB","Feb 17, 2024"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 2","75 MB","Jun 17, 2024"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 3","80 MB","Mar 10, 2021"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_dataset_24,"Tarea 4","25 MB","Mar 17, 2020"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_description_24,"Tarea 5","75 MB","Jul 17, 2020"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_image_24,"Tarea 6","80 MB","Oct 10, 2023"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 7","25 MB","Nov 17, 2022"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 8","75 MB","Mar 27, 2022"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_width_wide_24,"Tarea 9","80 MB","Ene 11, 2024"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 10","25 MB","Feb 17, 2024"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 11","75 MB","Jun 17, 2024"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 12","80 MB","Mar 10, 2021"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_dataset_24,"Tarea 13","25 MB","Mar 17, 2020"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_description_24,"Tarea 14","75 MB","Jul 17, 2020"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_image_24,"Tarea 15","80 MB","Oct 10, 2023"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 16","25 MB","Nov 17, 2022"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_feed_24,"Tarea 17","75 MB","Mar 27, 2022"))
        listaArchivos.add(Archivo(R.drawable.ic_baseline_width_wide_24,"Tarea 18","80 MB","Ene 11, 2024"))


        val recyclerView = findViewById<RecyclerView>(R.id.rvArchivo)
        initRecyclerView(listaArchivos, recyclerView)
    }

    private fun initRecyclerView(listaArchivos: ArrayList<Archivo>, recyclerView: RecyclerView) {
        val adapter = Adaptador2(listaArchivos,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}