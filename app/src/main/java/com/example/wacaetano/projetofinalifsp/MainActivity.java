
//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Wilian Aparecido Caetano
//******************************************************


package com.example.wacaetano.projetofinalifsp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Botão que realizará ação de encaminhar para outra activity
    private Button btnCadastrarPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarPessoa = (Button) findViewById(R.id.btnCadastrarPessoa);

        //Ação de click chamando activity responsável pelo cadastro
        btnCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, cadastroActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
