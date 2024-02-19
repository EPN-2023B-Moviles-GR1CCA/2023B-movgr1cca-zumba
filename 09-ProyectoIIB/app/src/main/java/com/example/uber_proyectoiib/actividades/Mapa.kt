package com.example.uber_proyectoiib.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uber_proyectoiib.R
import com.example.uber_proyectoiib.databinding.ActivityMapaBinding
import com.example.uber_proyectoiib.databinding.ActivityRegistroBinding

class Mapa : AppCompatActivity() {
    private lateinit var binding: ActivityMapaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}