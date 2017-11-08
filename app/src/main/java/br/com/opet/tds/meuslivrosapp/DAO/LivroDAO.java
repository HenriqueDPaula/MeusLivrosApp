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
 *
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Livro.
 *
 */

public class LivroDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public static final int LIVROS_TOTAL = 1;
    public static final int LIVROS_FAVORITOS = 2;


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
        valores.put(BancoUtil.LIVRO_USUARIO,livro.getId_usuario());

        resultado = db.insert(BancoUtil.TABELA_LIVRO, null, valores);
        db.close();

        return resultado;

    }

    public Livro carregaLivroPorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LIVRO, BancoUtil.TITULO_LIVRO, BancoUtil.GENERO_LIVRO, BancoUtil.LIVRO_FAVORITO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_LIVRO + " = " + id;

        cursor = db.query(BancoUtil.TABELA_LIVRO, campos, where, null, null, null, null, null);

        Livro livro = new Livro();
        if (cursor != null) {
            cursor.moveToFirst();

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
        }
        db.close();
        return livro;
    }

    public Cursor carregaDados(long id_usuario) {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LIVRO, BancoUtil.TITULO_LIVRO, BancoUtil.GENERO_LIVRO, BancoUtil.LIVRO_FAVORITO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.LIVRO_USUARIO + " = " + id_usuario;

        cursor = db.query(BancoUtil.TABELA_LIVRO, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosFavoritos(long id_usuario) {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LIVRO, BancoUtil.TITULO_LIVRO, BancoUtil.GENERO_LIVRO, BancoUtil.LIVRO_FAVORITO};
        db = banco.getReadableDatabase();
        String where =  BancoUtil.LIVRO_FAVORITO + " == 1";
        where += " and " + BancoUtil.LIVRO_USUARIO + " = " + id_usuario;

        cursor = db.query(BancoUtil.TABELA_LIVRO, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Livro> carregaDadosLista(int TIPO_LIVROS, long id_usuario) {
        Cursor cursor;
        if(TIPO_LIVROS == LIVROS_TOTAL)
            cursor = carregaDados(id_usuario);
        else
            cursor = carregaDadosFavoritos(id_usuario);

        List<Livro> livros = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
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
            }
        } finally {
            cursor.close();
        }

        return livros;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_LIVRO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_LIVRO,where,null);
        db.close();
    }

    public boolean atualizaLivro(Livro livro){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_LIVRO + " = " + livro.getID();

        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_LIVRO, livro.getTitulo());
        valores.put(BancoUtil.GENERO_LIVRO, livro.getGenero());
        valores.put(BancoUtil.LIVRO_FAVORITO, livro.isFavorito());

        int resultado = db.update(BancoUtil.TABELA_LIVRO,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}