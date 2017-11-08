package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.opet.tds.meuslivrosapp.DAO.Livro;
import br.com.opet.tds.meuslivrosapp.DAO.LivroDAO;
import br.com.opet.tds.meuslivrosapp.R;

public class AtualizarLivrosActivity extends Activity {

    private int ID_LIVRO;
    private LivroDAO livroDAO;
    private Livro livro;

    private EditText editTitulo;
    private Spinner spinnerGenero;
    private CheckBox checkFavorito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_livros);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_LIVRO")){
            ID_LIVRO = intent.getIntExtra("ID_LIVRO",0);
        }
        livroDAO = new LivroDAO(this);
        livro = livroDAO.carregaLivroPorID(ID_LIVRO);

        editTitulo = (EditText) findViewById(R.id.editTituloUpdate);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGeneroUpdate);
        checkFavorito = (CheckBox) findViewById(R.id.checkFavoritoUpdate);

        editTitulo.setText(livro.getTitulo());
        selectSpinnerItemByValue(spinnerGenero,livro.getGenero());
        checkFavorito.setChecked(livro.isFavorito());
    }

    public void atualizarLivro(View v){
        livro.setTitulo(editTitulo.getText().toString());
        livro.setGenero(spinnerGenero.getSelectedItem().toString());
        livro.setFavorito(checkFavorito.isChecked());
        if(livroDAO.atualizaLivro(livro))
            Toast.makeText(AtualizarLivrosActivity.this, "Livro atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AtualizarLivrosActivity.this, "Erro ao atualizar livro.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerLivro(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                livroDAO.deletaRegistro(ID_LIVRO);
                Toast.makeText(AtualizarLivrosActivity.this, "Livro removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarLivrosActivity.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}
