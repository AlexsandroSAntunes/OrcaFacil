package com.orcafacil;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.orcafacil.databinding.ActivityAddTransactionBinding;

public class AddTransactionActivity extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSalvar.setOnClickListener(v -> {
            String valor = binding.edtValor.getText().toString();
            String descricao = binding.edtDescricao.getText().toString();

            if (!valor.trim().isEmpty() && !descricao.trim().isEmpty()) {
                Toast.makeText(this, "Transação salva!", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a tela e volta para a MainActivity
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
