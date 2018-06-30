//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Wilian Aparecido Caetano
//******************************************************


package com.example.wacaetano.projetofinalifsp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class cadastroActivity extends AppCompatActivity {

    //Atributo responsável pelas ações da camada do banco de dados
    private DBHelper dbHelper;

    private PessoaFisica pessoa;

    private EditText etNome, etCPF, etIdade, etTelefone, etEmail;
    private Button btnListar, btnCadastrarPessoa, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Ação de preparar objetos utilizados na activity
        popularObjetosView();

        //Ação ao clicar no botao inserir dados
        btnCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCamposPreenchidos()){
                    popularPessoaFisica();
                    dbHelper.insert(pessoa);
                    AlertDialog.Builder adb = new AlertDialog.Builder(cadastroActivity.this);
                    adb.setTitle("Sucesso!");
                    adb.setMessage("Registro inserido com sucesso!");
                    adb.show();
                }else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(cadastroActivity.this);
                    adb.setTitle("Erro!");
                    adb.setMessage("Todos os campos devem ser preenchidos!");
                    adb.show();
                }
                limparCampos();
            }
        });

        //Mostra a lista de pesssoas cadastradas com dialogo
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PessoaFisica> pessoas = dbHelper.queryGetAll();
                if (pessoas == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(cadastroActivity.this);
                    adb.setTitle("Aviso!");
                    adb.setMessage("Não há contatos cadastrados!");
                    adb.show();
                    return;
                }
                for (int posicao = 0; posicao < pessoas.size(); posicao++){
                    AlertDialog.Builder adb = new AlertDialog.Builder(cadastroActivity.this);
                    adb.setTitle("Pessoa Física: " + (posicao + 1));
                    adb.setMessage(pessoas.get(posicao).toString());
                    adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });

        //Retorna para a activity principal do sistema
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(cadastroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //Remove digitação de todos os campos
    public void limparCampos() {
        etNome.setText("");
        etCPF.setText("");
        etIdade.setText("");
        etTelefone.setText("");
        etEmail.setText("");

        this.pessoa = null;
    }

    //Prepara o objeto preenchido para cadastrar no banco
    public void popularObjetosView() {

        this.dbHelper = new DBHelper(this);

        etNome = (EditText) findViewById(R.id.etNome);
        etCPF = (EditText) findViewById(R.id.etCPF);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnListar = (Button) findViewById(R.id.btnListar);
        btnCadastrarPessoa = (Button) findViewById(R.id.btnCadastrarPessoa);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
    }

    //Torna campos obrigatórios
    public boolean validarCamposPreenchidos(){
        return (etNome.getText().length() > 0 && etCPF.getText().length() > 0 && etIdade.getText().length() > 0 && etTelefone.getText().length() > 0 &&
                etEmail.getText().length() > 0);
    }

    //Atribui valores ao objeto do modelo
    public void popularPessoaFisica(){
        this.pessoa = new PessoaFisica(etNome.getText().toString(), etCPF.getText().toString(), etIdade.getText().toString(),
                etTelefone.getText().toString(), etEmail.getText().toString());
    }


}
