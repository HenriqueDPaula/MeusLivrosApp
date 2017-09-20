package br.com.opet.tds.meuslivrosapp.DAO;

/**
 * Created by Diego on 13/09/2017.
 */

public class Livro {
    private int ID;
    private String titulo;
    private String genero;
    private boolean favorito;

    public Livro() {
    }

    public Livro(int ID, String titulo, String genero, boolean favorito) {
        this.ID = ID;
        this.titulo = titulo;
        this.genero = genero;
        this.favorito = favorito;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
