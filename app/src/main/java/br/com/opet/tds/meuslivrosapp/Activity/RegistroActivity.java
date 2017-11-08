package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.opet.tds.meuslivrosapp.DAO.Usuario;
import br.com.opet.tds.meuslivrosapp.DAO.UsuarioDAO;
import br.com.opet.tds.meuslivrosapp.R;
import br.com.opet.tds.meuslivrosapp.Util.Util;

public class RegistroActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editLogin = (EditText) findViewById(R.id.editLoginUsuarioReg);
        editSenha = (EditText) findViewById(R.id.editSenhaUsuarioReg);
    }

    public void gravarUsuario(View v){
        Usuario usuario = new Usuario();
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        usuario.setLogin(login);
        usuario.setSenha(Util.toMD5(senha));

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long result = usuarioDAO.insereDado(usuario);
        if(result > 0){
            Toast.makeText(this, "Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Usuário não Cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }
}
