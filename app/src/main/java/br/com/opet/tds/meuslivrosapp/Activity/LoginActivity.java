package br.com.opet.tds.meuslivrosapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.opet.tds.meuslivrosapp.DAO.Usuario;
import br.com.opet.tds.meuslivrosapp.DAO.UsuarioDAO;
import br.com.opet.tds.meuslivrosapp.R;
import br.com.opet.tds.meuslivrosapp.Util.Util;

public class LoginActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = (EditText) findViewById(R.id.editLoginUsuario);
        editSenha = (EditText) findViewById(R.id.editSenhaUsuario);
    }

    public void logarUsuario(View v){
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        long idUsuario = usuarioDAO.validaUsuario(login,Util.toMD5(senha));
        if(idUsuario > 0){
            Toast.makeText(this, "Usuário Logado com Sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("ID_USUARIO",idUsuario);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Usuário não Cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarUsuario(View v){
        Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(intent);
    }
}
