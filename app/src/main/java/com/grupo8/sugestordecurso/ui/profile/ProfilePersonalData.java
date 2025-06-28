package com.grupo8.sugestordecurso.ui.profile;

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

import java.util.Objects;

public class ProfilePersonalData extends AppCompatActivity {
    private LoadScreen LoadScreen;
    private boolean isConectado = false;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal_data);

        LoadScreen = new LoadScreen();
        user = User.getInstance();

        onClickVoltar();
        verificaConexao();

        setDados();

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

            contato.setNome(Objects.requireNonNull(editTextNome.getText()).toString());
            contato.setNomeSocial(Objects.requireNonNull(editTextNomeSocial.getText()).toString());
            contato.setTelefonePrincipal(Objects.requireNonNull(editTextTelefone.getText()).toString());
            contato.setEmailPrincipal(Objects.requireNonNull(editTextEmail.getText()).toString());

            // Cria conexão com APIRubeus
            RequestRepository contatoRepository = new RequestRepository();
            // Envia chamada
            contatoRepository.cadastrarContato(contato, new ContatoCallback() {
                @Override
                public void onSuccess(RespostaCadastro response) {
                    Log.i("API Teste", "Dados atualizados");
                    updateDados(response, contato);

                    Log.i("API Teste", "ID: " + user.getId());
                    LoadScreen.dismissLoading(); //fecha tela de carregamento
                    Toast.makeText(ProfilePersonalData.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                    //volta para a página do usuário
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    LoadScreen.dismissLoading();
                    Toast.makeText(ProfilePersonalData.this, "Erro ao atualizar dados", Toast.LENGTH_SHORT).show();
                }
            });
        } else{
            View view = findViewById(android.R.id.content);
            Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
        }
    }

    private void onClickVoltar(){
        ImageButton btnVoltar = findViewById(R.id.btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para voltar: Simula o botão "voltar" do sistema
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void verificaConexao(){
        //verificador de conexao
        CheckConexion verificadorConexao = new CheckConexion(getApplicationContext());

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
    }

    private void setDados(){
        //editTexts com os dados do usuário
        EditText edtNome = findViewById(R.id.Nome);
        EditText edtNomeSocial = findViewById(R.id.NomeSocial);
        EditText edtEmail = findViewById(R.id.Email);
        EditText edtTelefone = findViewById(R.id.Telefone);
        EditText edtCPF = findViewById(R.id.CPF);
        EditText edtDataN = findViewById(R.id.Nascimento);

        edtNome.setText(user.getNome());
        edtNomeSocial.setText(user.getNomeSocial());
        edtEmail.setText(user.getEmail());
        edtTelefone.setText(user.getTelefone());
        edtCPF.setText(user.getCpf());
        edtDataN.setText(user.getDataNascimento());
    }

    private void updateDados(RespostaCadastro response, BodyCadastro contato){
        user.setId(response.getDados());
        user.setNome(contato.getNome());
        user.setNomeSocial(contato.getNomeSocial());
        user.setEmail(contato.getEmailPrincipal());
        user.setTelefone(contato.getTelefonePrincipal());
        user.setCpf(contato.getCpf());
        user.setDataNascimento(contato.getDataNascimento());
    }
}