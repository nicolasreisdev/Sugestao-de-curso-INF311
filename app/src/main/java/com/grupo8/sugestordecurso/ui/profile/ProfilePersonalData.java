package com.grupo8.sugestordecurso.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyCadastro;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.register.RegisterData;

public class ProfilePersonalData extends Activity {
    //editTexts com os dados do usuário passíveis de serem alterados
    EditText edtNome, edtEmail, edtTelefone, edtCPF, edtDataN;
    Button buttonSalvar;
    LoadScreen LoadScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal_data);

        edtNome = findViewById(R.id.Nome);
        edtEmail = findViewById(R.id.Email);
        edtTelefone = findViewById(R.id.Telefone);
        edtCPF = findViewById(R.id.CPF);
        edtDataN = findViewById(R.id.Nascimento);

        buttonSalvar = findViewById(R.id.BtSalvar);

        User user = User.getInstance();
        edtNome.setText(user.getNome());
        edtEmail.setText(user.getEmail());
        edtTelefone.setText(user.getTelefone());
        edtCPF.setText(user.getCpf());
        edtDataN.setText(user.getDataNascimento());

    }

    public void onClickUpdateData(View v){
        String nome = edtNome.getText().toString();

        //Atualização dos dados via api
        BodyCadastro contato = new BodyCadastro();

        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextNomeSocial = findViewById(R.id.Nascimento);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextTelefone = findViewById(R.id.Telefone);

        contato.setNome(editTextNome.getText().toString());
        contato.setNomeSocial(editTextNomeSocial.getText().toString());
        contato.setTelefonePrincipal(editTextTelefone.getText().toString());
        contato.setEmailPrincipal(editTextEmail.getText().toString());

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.cadastrarContato(contato, new ContatoCallback() {
            @Override
            public void onSuccess(RespostaCadastro response) {
                Log.i("API Teste", "Dados atualizados");
                User user = User.getInstance();
                user.setId(response.getDados());
                user.setNome(contato.getNome());
                user.setNomeSocial(contato.getNomeSocial());
                user.setEmail(contato.getEmailPrincipal());
                user.setTelefone(contato.getTelefonePrincipal());
                user.setCpf(contato.getCpf());
                user.setDataNascimento(contato.getDataNascimento());
                Log.i("API Teste", "ID: " + user.getId());
                LoadScreen = new LoadScreen();
                LoadScreen.dismissLoading(); //fecha tela de carregamento
                //navega para a página do usuário
                Intent it = new Intent(ProfilePersonalData.this, RegisterData.class);
                it.putExtra("user", user);
                startActivity(it);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

        Toast.makeText(ProfilePersonalData.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();

        //volta para tela anterior
        finish();
    }
}