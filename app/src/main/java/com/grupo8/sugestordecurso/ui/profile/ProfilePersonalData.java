package com.grupo8.sugestordecurso.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyCadastro;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.Utils.CheckConexion;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.register.RegisterData;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class ProfilePersonalData extends AppCompatActivity {
    //editTexts com os dados do usuário passíveis de serem alterados
    EditText edtNome, edtNomeSocial, edtEmail, edtTelefone, edtCPF, edtDataN;
    Button buttonSalvar;
    LoadScreen LoadScreen;
    private CheckConexion verificadorConexao; //verificador de conexao
    private boolean isConectado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal_data);

        LoadScreen = new LoadScreen();

        verificadorConexao = new CheckConexion(getApplicationContext());

        ImageButton btnVoltar = findViewById(R.id.btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para voltar: Simula o botão "voltar" do sistema
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Observa as mudanças no estado da conexão
        verificadorConexao.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean conectado) {
                isConectado = conectado;
                if (!conectado) {
                    // Exibe uma mensagem de erro persistente se não houver conexão
                    View view = findViewById(android.R.id.content); // View raiz da sua activity
                    Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        edtNome = findViewById(R.id.Nome);
        edtNomeSocial = findViewById(R.id.NomeSocial);
        edtEmail = findViewById(R.id.Email);
        edtTelefone = findViewById(R.id.Telefone);
        edtCPF = findViewById(R.id.CPF);
        edtDataN = findViewById(R.id.Nascimento);

        buttonSalvar = findViewById(R.id.BtSalvar);

        User user = User.getInstance();
        edtNome.setText(user.getNome());
        edtNomeSocial.setText(user.getNomeSocial());
        edtEmail.setText(user.getEmail());
        edtTelefone.setText(user.getTelefone());
        edtCPF.setText(user.getCpf());
        edtDataN.setText(user.getDataNascimento());

    }

    public void onClickUpdateData(View v){
        if(isConectado) {
            LoadScreen.showLoading(getSupportFragmentManager(), "Salvando");
            //Atualização dos dados via api
            BodyCadastro contato = new BodyCadastro();

            TextInputEditText editTextNome = findViewById(R.id.Nome);
            TextInputEditText editTextNomeSocial = findViewById(R.id.NomeSocial);
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
                    LoadScreen.dismissLoading(); //fecha tela de carregamento
                    //volta para a página do usuário
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    LoadScreen.dismissLoading();
                }
            });

            Toast.makeText(ProfilePersonalData.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
        } else{
            View view = findViewById(android.R.id.content); // View raiz da sua activity
            Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
        }
    }
}