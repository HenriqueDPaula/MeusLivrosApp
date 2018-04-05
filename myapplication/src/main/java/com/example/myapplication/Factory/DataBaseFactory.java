package com.example.myapplication.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Util.Banco;

/**
 * Created by opet on 04/04/2018.
 */

public class DataBaseFactory extends SQLiteOpenHelper {

    public DataBaseFactory(Context context){
        super(context, Banco.NOME_BANCO, null, Banco.VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + Banco.TABELA_PESSOA + "("
                +Banco.ID_PESSOA + "integer not null primary key autoincrement, "
                + Banco.NOME_PESSOA + "text, "
                + Banco.IDADE_PESSOA + "integer, "
                + Banco.SEXO_PESSOA + "text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Banco.TABELA_PESSOA);

    }
}








