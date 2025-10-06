package com.orcafacil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orcafacil.databinding.ActivityAddTransactionBinding;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddTransactionActivity extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        binding.btnSalvar.setOnClickListener(v -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser == null) {
                // Ninguém está logado, não deve acontecer, mas é bom previnir
                Toast.makeText(this, "Erro: Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
                // O ideal seria redirecionar para a tela de login
                finish();
                return;
            }
            salvarTransacao(currentUser.getUid());
        });
    }

    private void salvarTransacao(String userId) {
        String valorStr = binding.edtValor.getText().toString().trim();
        String descricao = binding.edtDescricao.getText().toString().trim();

        if (valorStr.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show();
            return;
        }

        String tipo = binding.rbReceita.isChecked() ? "RECEITA" : "DESPESA";

        // Cria um mapa com os dados da transação
        Map<String, Object> transacao = new HashMap<>();
        transacao.put("userId", userId);
        transacao.put("valor", valor);
        transacao.put("descricao", descricao);
        transacao.put("tipo", tipo);
        transacao.put("data", new Date()); // Salva a data e hora atual da transação

        // Adiciona um novo documento com um ID gerado automaticamente na coleção "transacoes"
        db.collection("transacoes").add(transacao)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddTransactionActivity.this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Fecha a tela e volta para a MainActivity
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddTransactionActivity.this, "Erro ao salvar transação: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("FirestoreError", "Erro ao salvar transação", e);
                });
    }
}
