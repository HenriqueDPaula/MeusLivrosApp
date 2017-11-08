package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.opet.tds.meuslivrosapp.Adapter.LivroAdapter;
import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.DAO.LivroDAO;
import br.com.opet.tds.meuslivrosapp.R;

public class ListarLivrosActivity extends Activity {


    private ListView listaLivros;
    private LivroAdapter myAdapter;
    LivroDAO livroDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        carregarElementos();
    }

    public void carregarElementos(){
        listaLivros = (ListView) findViewById(R.id.listLivros);
        livroDAO = new LivroDAO(this);
        List<Livro> livros = livroDAO.carregaDadosLista(LivroDAO.LIVROS_TOTAL,MainActivity.usuarioLogado.getID());
        myAdapter = new LivroAdapter(this,R.layout.item_livro,livros);
        listaLivros.setAdapter(myAdapter);
        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livro = (Livro)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarLivrosActivity.this,AtualizarLivrosActivity.class);
                atualizarIntent.putExtra("ID_LIVRO",livro.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}
