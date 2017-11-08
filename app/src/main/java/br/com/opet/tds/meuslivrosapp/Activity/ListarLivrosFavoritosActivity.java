package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.com.opet.tds.meuslivrosapp.Adapter.LivroAdapter;
import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.DAO.LivroDAO;
import br.com.opet.tds.meuslivrosapp.R;
import br.com.opet.tds.meuslivrosapp.Util.BancoUtil;

public class ListarLivrosFavoritosActivity extends Activity {

    private ListView listaLivros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros_favoritos);

        listaLivros = (ListView) findViewById(R.id.listLivrosFav);
        LivroDAO livroDAO = new LivroDAO(this);
        List<Livro> livros = livroDAO.carregaDadosLista(LivroDAO.LIVROS_FAVORITOS,MainActivity.usuarioLogado.getID());
        LivroAdapter myAdapter = new LivroAdapter(this,R.layout.item_livro,livros);
        listaLivros.setAdapter(myAdapter);
    }
}
