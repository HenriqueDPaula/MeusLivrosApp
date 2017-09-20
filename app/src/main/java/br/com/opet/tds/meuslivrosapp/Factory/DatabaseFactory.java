package br.com.opet.tds.meuslivrosapp.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.opet.tds.meuslivrosapp.Util.BancoUtil;

/**
 * Created by Diego on 13/09/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "CREATE TABLE "+ BancoUtil.TABELA_LIVRO+"("
                + BancoUtil.ID_LIVRO+ " integer primary key autoincrement,"
                + BancoUtil.TITULO_LIVRO + " text,"
                + BancoUtil.GENERO_LIVRO + " text,"
                + BancoUtil.LIVRO_FAVORITO + " integer"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_LIVRO);

        onCreate(db);
    }
}
