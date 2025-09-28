package com.orcafacil

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MainActivity : AppCompatActivity() {
    private var pieChart: PieChart? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pieChart = findViewById(R.id.pieChart)
        setupChart()
    }

    private fun setupChart() {
        val entries: java.util.ArrayList<PieEntry?> = java.util.ArrayList<PieEntry?>()
        entries.add(PieEntry(65f, "Alimentação"))
        entries.add(PieEntry(35f, "Outras"))

        val dataSet: PieDataSet = PieDataSet(entries, "")
        dataSet.setColors(
            Color.parseColor("#6A1B9A"),
            Color.parseColor("#F7C20A")
        )
        dataSet.setValueTextColor(Color.WHITE)
        dataSet.setValueTextSize(14f)

        val data: PieData = PieData(dataSet)
        pieChart.setData(data)
        pieChart.getDescription().setEnabled(false)
        pieChart.setCenterText("Gastos")
        pieChart.animateY(600)
        pieChart.invalidate()
    }
}