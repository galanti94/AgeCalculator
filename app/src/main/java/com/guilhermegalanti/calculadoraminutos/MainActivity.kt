package com.guilhermegalanti.calculadoraminutos

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDataSelecionada: TextView? = null //Nulável; privada, só pode ser chamada por aqui
    private var tvIdadeMinutos: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retirar barra de título
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_main)

        tvDataSelecionada = findViewById(R.id.tvDataSelecionada) // Localizando campo de texto
        tvIdadeMinutos = findViewById(R.id.idadeEmMinutos)

        //Prgramando botão
        val btnDate: Button = findViewById(R.id.btn_data)
        btnDate.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        // Trabalhando calendário
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        // Truque para facilitar a programação
        val dpd = DatePickerDialog(this,
            { _, anoSelecionado, mesSelecionado, diaSelecionado ->
                val dataSelecionada = "$diaSelecionado/${mesSelecionado+1}/$anoSelecionado "
                tvDataSelecionada?.text = dataSelecionada

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val data = sdf.parse(dataSelecionada)

                // let{} só vai executar se não for null
                data?.let{
                    val dataEmMintos = data.time / 6000
                    val dataAtual = sdf.parse(sdf.format(System.currentTimeMillis())).time / 6000

                    dataAtual?.let {
                        tvIdadeMinutos?.text = "${dataAtual-dataEmMintos}"
                    }

                }


            },
            year,
            month, //Mês é calculado tendo janeiro como zero; precisa add +1
            day)

        dpd.datePicker.maxDate = System.currentTimeMillis() // Não se pode nascer no futuro
        dpd.show()
    }

}