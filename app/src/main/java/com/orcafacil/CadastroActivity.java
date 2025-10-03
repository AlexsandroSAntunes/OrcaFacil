package com.orcafacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroActivity.this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                // Certifique-se que você tem uma LoginActivity.class criada
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finaliza a tela de cadastro
            }
        });
    }
}
