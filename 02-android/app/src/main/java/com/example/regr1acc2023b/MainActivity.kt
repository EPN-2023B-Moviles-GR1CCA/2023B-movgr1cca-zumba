package com.example.regr1acc2023b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.regr1acc2023b.ui.theme.REgr1acc2023BTheme

import  androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida:: class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonCicloVida.setOnClickListener{
            irActividad(BListView:: class.java)
        }
    }

fun irActividad( clase: Class<*>){
    val intent = Intent(this, clase)
    startActivity(intent)
}
}


