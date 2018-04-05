package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Factory.DataBaseFactory;
import com.example.myapplication.Util.Banco;

/**
 * Created by opet on 04/04/2018.
 */

public class PessoaDAO {

    SQLiteDatabase db;
    DataBaseFactory dbf;

    public PessoaDAO(Context context){
        dbf = new DataBaseFactory(context);
    }

    public long inserirPessoa(Pessoa pessoa){
        ContentValues valor;
        long result;

        db = dbf.getWritableDatabase();
        valor = new ContentValues();

        valor.put(Banco.NOME_PESSOA, pessoa.getNome());
        valor.put(Banco.IDADE_PESSOA, pessoa.getNome());
        valor.put(Banco.SEXO_PESSOA, pessoa.getSexo());

        result = db.insert(Banco.TABELA_PESSOA, null, valor);
        db.close();

        return result;
    }
}
