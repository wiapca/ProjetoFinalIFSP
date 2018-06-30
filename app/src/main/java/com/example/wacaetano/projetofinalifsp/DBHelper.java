//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Wilian Aparecido Caetano
//******************************************************

package com.example.wacaetano.projetofinalifsp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    //Atributos para ações na base de dados
    private static final String DATABASE_NAME = "meubancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoa_fisica";

    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    //SQL de inserção
    private static final String INSERT = " INSERT INTO " + TABLE_NAME + " (nome, cpf, idade, telefone, email) VALUES (?,?,?,?,?)";

    //Construtor para preparar objeto responsável pelas ações no banco de dados
    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    //Método para popular statement que contém a query de inserção
    public long insert(PessoaFisica pessoaFisica) {
        this.insertStmt.bindString(1, pessoaFisica.getNome());
        this.insertStmt.bindString(2, pessoaFisica.getCpf());
        this.insertStmt.bindString(3, pessoaFisica.getIdade());
        this.insertStmt.bindString(4, pessoaFisica.getTelefone());
        this.insertStmt.bindString(5, pessoaFisica.getEmail());
        return this.insertStmt.executeInsert();
    }

    //Método para limpeza de banco de dados
    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<PessoaFisica> queryGetAll() {
        List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"}, null, null, null, null, null, null);
            int nRegistros = cursor.getCount();
            if (nRegistros > 0) {
                cursor.moveToFirst();
                do {
                    PessoaFisica pessoa = new PessoaFisica(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    pessoas.add(pessoa);
                } while (cursor.moveToNext());

                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                    return pessoas;
                }
            } else {
                return null;
            }


        } catch (Exception erro) {
            return null;
        }
        return null;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            String sql = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        }

    }


}
