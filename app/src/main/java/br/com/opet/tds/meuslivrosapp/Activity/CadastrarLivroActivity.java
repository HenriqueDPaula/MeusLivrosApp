package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.DAO.LivroDAO;
import br.com.opet.tds.meuslivrosapp.R;

public class CadastrarLivroActivity extends Activity {

    private EditText editTitulo;
    private Spinner spinnerGenero;
    private CheckBox checkFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);

        editTitulo = (EditText) findViewById(R.id.editTitulo);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
        checkFavorito = (CheckBox) findViewById(R.id.checkFavorito);
    }

    public void salvarLivro(View v){
        LivroDAO livroDAO = new LivroDAO(this);
        Livro livro = new Livro();
        livro.setTitulo(editTitulo.getText().toString());
        livro.setGenero(spinnerGenero.getSelectedItem().toString());
        livro.setFavorito(checkFavorito.isChecked());
        livro.setId_usuario(MainActivity.usuarioLogado.getID());

        long resultado = livroDAO.insereDado(livro);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarLivros = new Intent(CadastrarLivroActivity.this,ListarLivrosActivity.class);
            startActivity(listarLivros);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
