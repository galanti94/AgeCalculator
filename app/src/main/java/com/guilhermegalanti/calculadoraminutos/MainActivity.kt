package com.guilhermegalanti.calculadoraminutos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retirar barra de título
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_main)


        //Prgramando botão
        val btnDate: Button = findViewById(R.id.btn_data)
        btnDate.setOnClickListener{
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        Toast.makeText(this, "Botão pressionado!",Toast.LENGTH_LONG).show()

    }
}