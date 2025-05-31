package com.grupo8.sugestordecurso.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Contato;

public class Register extends AppCompatActivity {

    private Contato contato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void onClickRegister(View v){
        /*contato = new Contato();
        // Recebe os dados
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);
        TextInputEditText editTextTelefone = findViewById(R.id.Telefone);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextSenha = findViewById(R.id.Senha);

        contato.setNome(editTextNome.getText().toString());
        contato.setCpf(editTextCPF.getText().toString());
        contato.setTelefone(editTextTelefone.getText().toString());
        contato.setEmail(editTextEmail.getText().toString());
        contato.setNascimento(editTextNascimento.getText().toString());
        contato.setSenha(editTextSenha.getText().toString());

        // Cria conexão com APIRubeus
        ContatoRepository contatoRepository = new ContatoRepository();
        // Envia chamada
        contatoRepository.cadastrarContato(contato).observe(this, resposta -> {
            if(resposta != null && resposta.isSuccess()){
                // intent para página do usuário (é necessário passar Activity.this pois
                // está dentro do onResponse que está apontando para o Callback e não para
                // a Activity
                Intent ituserPage = new Intent(Register.this, UserPage.class);
                // passa os dados do usuário
                ituserPage.putExtra("User", contato);
                // inicializa a página do usuário
                startActivity(ituserPage);
            }else{
                // exibir mensagem de erro
            }
        });*/

        //navega para a próxima etapa de cadastro
        Intent it = new Intent(Register.this,RegisterData.class);
        startActivity(it);

    }
}