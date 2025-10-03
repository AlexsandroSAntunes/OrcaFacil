package com.orcafacil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.orcafacil.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // A variável binding para acessar os componentes do layout
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Infla o layout usando ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Define o layout inflado como o conteúdo da tela
        setContentView(binding.getRoot());

        // Configura as funcionalidades da tela
        setupFabClickListener();
        updateFinancialInfo();
        setupPieChart();
    }

    // Configura o clique do botão flutuante (+)
    private void setupFabClickListener() {
        binding.fabAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });
    }

    // Atualiza as informações financeiras (saldo)
    private void updateFinancialInfo() {
        double saldo = 1250.50;
        String saldoFormatado = String.format("%.2f", saldo);
        // Usa o recurso de string para definir o texto
        binding.tvSaldo.setText(ContextCompat.getString(R.string.saldo_label, saldoFormatado));
    }

    // Configura e popula o gráfico de pizza
    private void setupPieChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(450f, "Alimentação"));
        entries.add(new PieEntry(200f, "Transporte"));
        entries.add(new PieEntry(600f, "Moradia"));
        entries.add(new PieEntry(150f, "Lazer"));
        entries.add(new PieEntry(300f, "Outros"));

        PieDataSet dataSet = new PieDataSet(entries, "Despesas por Categoria");

        // Define as cores para o gráfico
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

        // Aplica os dados e personaliza o gráfico
        PieChart pieChart = binding.pieChart;
        pieChart.setData(pieData);
        pieChart.getDescription().setText("Distribuição de Despesas");
        pieChart.getDescription().setTextSize(12f);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateY(1000);
        pieChart.invalidate(); // Atualiza o gráfico
    }
}
