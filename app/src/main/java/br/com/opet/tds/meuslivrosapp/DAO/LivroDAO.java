package br.com.opet.tds.meuslivrosapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.opet.tds.meuslivrosapp.Factory.DatabaseFactory;
import br.com.opet.tds.meuslivrosapp.Util.BancoUtil;

/**
 * Created by Diego on 13/09/2017.
 */

public class LivroDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public LivroDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Livro livro) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_LIVRO, livro.getTitulo());
        valores.put(BancoUtil.GENERO_LIVRO, livro.getGenero());
        valores.put(BancoUtil.LIVRO_FAVORITO, livro.isFavorito() ? 1 : 0);

        resultado = db.insert(BancoUtil.TABELA_LIVRO, null, valores);
        db.close();

        return resultado;

    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LIVRO, BancoUtil.TITULO_LIVRO, BancoUtil.GENERO_LIVRO, BancoUtil.LIVRO_FAVORITO};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_LIVRO, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosFavoritos() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LIVRO, BancoUtil.TITULO_LIVRO, BancoUtil.GENERO_LIVRO, BancoUtil.LIVRO_FAVORITO};
        db = banco.getReadableDatabase();
        String where =  BancoUtil.LIVRO_FAVORITO + " == 1";
        cursor = db.query(BancoUtil.TABELA_LIVRO, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Livro> carregaDadosListaFav() {
        Cursor cursor = carregaDadosFavoritos();
        List<Livro> livros = new ArrayList<>();

        try {
            do {
                Livro livro = new Livro();
                int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_LIVRO));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_LIVRO));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.GENERO_LIVRO));
                int favorito = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.LIVRO_FAVORITO));

                livro.setID(ID);
                livro.setTitulo(titulo);
                livro.setGenero(genero);
                if (favorito == 1)
                    livro.setFavorito(true);
                else
                    livro.setFavorito(false);

                livros.add(livro);
            } while (cursor.moveToNext());
        } finally {
            cursor.close();
        }

        return livros;
    }

    public List<Livro> carregaDadosLista() {
        Cursor cursor = carregaDados();
        List<Livro> livros = new ArrayList<>();

        try {
            do  {
                Livro livro = new Livro();
                int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_LIVRO));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_LIVRO));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.GENERO_LIVRO));
                int favorito = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.LIVRO_FAVORITO));

                livro.setID(ID);
                livro.setTitulo(titulo);
                livro.setGenero(genero);
                if(favorito == 1)
                    livro.setFavorito(true);
                else
                    livro.setFavorito(false);

                livros.add(livro);
            }while(cursor.moveToNext());
        } finally {
            cursor.close();
        }

        return livros;
    }
}