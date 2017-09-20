package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.com.opet.tds.meuslivrosapp.Adapter.LivroAdapter;
import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.DAO.LivroDAO;
import br.com.opet.tds.meuslivrosapp.R;

public class ListarLivrosActivity extends Activity {


    private ListView listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        listaLivros = (ListView) findViewById(R.id.listLivros);
        LivroDAO livroDAO = new LivroDAO(this);
        List<Livro> livros = livroDAO.carregaDadosLista();
        LivroAdapter myAdapter = new LivroAdapter(this,R.layout.item_livro,livros);
        listaLivros.setAdapter(myAdapter);
    }
}
