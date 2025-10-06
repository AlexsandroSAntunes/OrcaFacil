package com.orcafacil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orcafacil.databinding.ActivityRegisterBinding;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db; // Instância do Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializa o Firebase Auth e Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.btnRegistrar.setOnClickListener(v -> {
            String nome = binding.edtNome.getText().toString().trim();
            String email = binding.edtEmail.getText().toString().trim();
            String senha = binding.edtSenha.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // 1. Cria o usuário no Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                // 2. Salva os dados adicionais do usuário no Firestore
                                salvarDadosUsuario(firebaseUser.getUid(), nome, email);
                            }
                        } else {
                            // Se o cadastro falhar, exibe uma mensagem
                            Toast.makeText(CadastroActivity.this, "Falha no registro: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private void salvarDadosUsuario(String userId, String nome, String email) {
        // Cria um mapa com os dados do usuário
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", nome);
        usuario.put("email", email);

        // Salva os dados na coleção "usuarios" com o ID do usuário
        db.collection("usuarios").document(userId).set(usuario)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(CadastroActivity.this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Volta para a tela de Login
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finishAffinity(); // Limpa o histórico de telas para não voltar ao cadastro
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CadastroActivity.this, "Erro ao salvar dados: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("FirestoreError", "Erro ao salvar usuário", e);
                });
    }
}
