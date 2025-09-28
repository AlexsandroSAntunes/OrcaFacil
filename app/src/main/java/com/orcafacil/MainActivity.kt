package com.orcafacil;

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pieChart = findViewById<PieChart?>(R.id.pieChart)

        // Lista de entradas (dados fictícios)
        val gastos = ArrayList<PieEntry?>()
        gastos.add(PieEntry(600f, "Alimentação"))
        gastos.add(PieEntry(400f, "Outras despesas"))

        // Configuração do DataSet
        val dataSet = PieDataSet(gastos, "Distribuição de Gastos")
        dataSet.setColors(
            *intArrayOf(
                Color.parseColor("#6A1B9A"),  // Roxo
                Color.parseColor("#FDD835") // Amarelo
            )
        )
        dataSet.setValueTextColor(Color.BLACK)
        dataSet.setValueTextSize(14f)

        // Criar objeto PieData
        val data = PieData(dataSet)
        pieChart.setData(data)

        // Configurações visuais do gráfico
        pieChart.getDescription().setEnabled(false)
        pieChart.setDrawHoleEnabled(true)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setCenterText("Gastos")
        pieChart.setCenterTextSize(16f)
        pieChart.animateY(1000)
    }
}