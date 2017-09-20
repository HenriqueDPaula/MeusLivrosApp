package br.com.opet.tds.meuslivrosapp.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.R;

/**
 * Created by Diego on 13/09/2017.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {

    private int resource;
    private List<Livro> livros;

    public LivroAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Livro> objects) {
        super(context, resource, objects);
        this.resource = resource;
        livros = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Livro livro = livros.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textTitulo = (TextView) mView.findViewById(R.id.textTitulo);
        TextView textGenero = (TextView) mView.findViewById(R.id.textGenero);
        TextView textFavorito = (TextView) mView.findViewById(R.id.textFavorito);

        if(textID != null){
            textID.setText(String.valueOf(livro.getID()));
        }
        if(textTitulo != null){
            textTitulo.setText(livro.getTitulo());
        }
        if(textGenero != null){
            textGenero.setText(livro.getGenero());
        }
        if(textFavorito != null){
            textFavorito.setText(String.valueOf(livro.isFavorito()));
        }
        return mView;
    }
}
