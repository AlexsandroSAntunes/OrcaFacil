package com.orcafacil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.orcafacil.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupFabClickListener();
        updateFinancialInfo();
        setupPieChart();
    }

    private void setupFabClickListener() {
        binding.fabAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });
    }

    private void updateFinancialInfo() {
        double saldo = 1250.50;
        String saldoFormatado = String.format("%.2f", saldo);
        // CORREÇÃO: Chamando getString diretamente, que é o método padrão da Activity.
        // Isto funcionará assim que você adicionar 'saldo_label' ao strings.xml.
        binding.tvSaldo.setText(getString(R.string.saldo_label, saldoFormatado));
    }

    private void setupPieChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(450f, "Alimentação"));
        entries.add(new PieEntry(200f, "Transporte"));
        entries.add(new PieEntry(600f, "Moradia"));
        entries.add(new PieEntry(150f, "Lazer"));
        entries.add(new PieEntry(300f, "Outros"));

        PieDataSet dataSet = new PieDataSet(entries, "Despesas por Categoria");

        List<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);

        PieData pieData = new PieData(dataSet);

        PieChart pieChart = binding.pieChart;
        pieChart.setData(pieData);
        pieChart.getDescription().setText("Distribuição de Despesas");
        pieChart.getDescription().setTextSize(12f);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}
