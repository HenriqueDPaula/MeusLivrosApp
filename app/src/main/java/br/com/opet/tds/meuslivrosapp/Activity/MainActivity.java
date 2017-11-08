package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.opet.tds.meuslivrosapp.DAO.Usuario;
import br.com.opet.tds.meuslivrosapp.DAO.UsuarioDAO;
import br.com.opet.tds.meuslivrosapp.R;

public class MainActivity extends Activity {

    public static Usuario usuarioLogado;
    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWelcome = (TextView) findViewById(R.id.textWelcome);

        Intent resultado = getIntent();
        long id = resultado.getLongExtra("ID_USUARIO",0);
        if(usuarioLogado == null)
            usuarioLogado = new UsuarioDAO(this).carregaUsuarioPorID(id);

        textWelcome.setText("Olá! " + usuarioLogado.getLogin());
    }

    public void carregaItemMenu(View v){
        switch (v.getId()){
            case R.id.btnInserir:
                carregarIntent(CadastrarLivroActivity.class);
                break;
            case R.id.btnListar:
                carregarIntent(ListarLivrosActivity.class);
                break;
            case R.id.btnFavoritos:
                carregarIntent(ListarLivrosFavoritosActivity.class);
                break;
        }
    }

    public void deslogar(View v){
        usuarioLogado = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void carregarIntent(Class classe){
        Intent intent = new Intent(MainActivity.this,classe);
        startActivity(intent);
    }
}
