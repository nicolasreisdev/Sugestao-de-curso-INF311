package com.grupo8.sugestordecurso.ui.register;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyCadastro;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class Register extends AppCompatActivity {

    private BodyCadastro contato;
    private TextInputEditText editTextCPF;
    private TextInputEditText editTextTelefone;
    private TextInputEditText editTextData;
    private Uri selectedImageUri;
    private Uri cameraOutputUri;
    private CircleImageView imageViewPerfil;
    //launcher para lidar com requisição de imagem
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private ActivityResultLauncher<String[]> requestPermissionLauncher;
    private LoadScreen LoadScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LoadScreen = new LoadScreen(); //cria tela de carregamento para ser chamada quando necessario

        editTextCPF = findViewById(R.id.CPF);
        editTextTelefone = findViewById(R.id.Telefone);
        editTextData = findViewById(R.id.Nascimento);

        //imagem de perfil do usuario
        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        //ao clicar no icone permite ao usuario escolher uma imagem da galeria ou tirar foto com a camera
        imageViewPerfil.setOnClickListener(v -> showImagePickerDialog());

        //cria uma mascara dinamicamente para que o input do usuario tenha formato xxx.xxx.xxx-xx
        editTextCPF.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String oldText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");

                if (isUpdating) {
                    oldText = str;
                    isUpdating = false;
                    return;
                }

                StringBuilder formatted = new StringBuilder();

                int len = str.length();

                if (len > 0) {
                    for (int i = 0; i < len && i < 11; i++) {
                        char c = str.charAt(i);
                        if (i == 3 || i == 6) {
                            formatted.append('.');
                        } else if (i == 9) {
                            formatted.append('-');
                        }
                        formatted.append(c);
                    }
                }

                isUpdating = true;
                editTextCPF.setText(formatted.toString());
                editTextCPF.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        //cria mascara para o input do usuario de data ser no formato YYYY-MM-DD
        editTextData.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String oldText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");

                if (isUpdating) {
                    oldText = str;
                    isUpdating = false;
                    return;
                }

                StringBuilder formatted = new StringBuilder();

                int len = str.length();

                if (len > 0) {
                    for (int i = 0; i < len && i < 8; i++) {
                        if (i == 4 || i == 6) {
                            formatted.append("-");
                        }
                        formatted.append(str.charAt(i));
                    }
                }

                isUpdating = true;
                editTextData.setText(formatted.toString());
                editTextData.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        //define um launcher caso ele decida escolher da galeria
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) { //se permitido
                        selectedImageUri = result.getData().getData(); //pega uri da imagem selecionada
                        try { //pega a imagem e garante que ela esteja na rotação correta
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                            Bitmap rotatedBitmap = rotateBitmapIfRequired(bitmap, inputStream, selectedImageUri);
                            imageViewPerfil.setImageBitmap(rotatedBitmap); //seta imagem no icone
                        } catch (IOException e) {
                            //caso haja erro, avisa o usuario
                            Toast.makeText(this, "Erro ao carregar imagem da galeria.", Toast.LENGTH_SHORT).show();
                        }
                    } else if (result.getResultCode() == RESULT_CANCELED) { //se nao permitido, avisa usuario
                        Toast.makeText(this, "Seleção da galeria cancelada.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        //launcher para tirar foto com a câmera
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                success -> {
                    if (success) {
                        selectedImageUri = cameraOutputUri; //a imagem foi salva no URI que passamos
                        try { //guarda imagem e garante que esteja na rotação correta
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                            Bitmap rotatedBitmap = rotateBitmapIfRequired(bitmap, inputStream, selectedImageUri);
                            imageViewPerfil.setImageBitmap(rotatedBitmap);
                        } catch (IOException e) {
                            //toast caso haja erro no carregamento da foto
                            Toast.makeText(this, "Erro ao carregar foto capturada.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Captura de foto cancelada ou falhou.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        //launcher para solicitar permissões
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), //pede permissoes de camera e galeria
                permissionsGranted -> {
                    boolean allGranted = true;
                    for (Boolean granted : permissionsGranted.values()) { //verifica se usuario deu todas permissoes necessarias
                        if (!granted) {
                            allGranted = false;
                            break;
                        }
                    }

                    if (allGranted) { //se tiver permissoes
                        Toast.makeText(this, "Permissões concedidas!", Toast.LENGTH_SHORT).show();
                        // Aqui você precisa saber qual ação (câmera ou galeria) o usuário queria.
                        // Uma forma é ter uma flag ou chamar um método que verifica e inicia a intent.
                        // Para simplicidade, vou apenas mostrar o toast.
                        // O ideal seria que a `showImagePickerDialog()` fosse chamada novamente
                        // ou a ação original fosse retomada.
                    } else {
                        Toast.makeText(this, "Permissões negadas. Não é possível realizar a ação.", Toast.LENGTH_LONG).show();
                    }
                }
        );

        //configura o clique no ícone de perfil
        imageViewPerfil.setOnClickListener(v -> showImagePickerDialog());

    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolher Imagem") //define uma aba com escolhas pro usuario
                .setItems(new CharSequence[]{"Tirar Foto", "Escolher da Galeria"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Tirar Foto
                                checkAndRequestCameraPermissions();
                                break;
                            case 1: // Escolher da Galeria
                                checkAndRequestStoragePermissions();
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void checkAndRequestCameraPermissions() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // Android 10+
            permissions = new String[]{Manifest.permission.CAMERA};
        } else {
            permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }

        boolean allGranted = true;
        for (String perm : permissions) { //verifica se tem todas permissoes
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) { //se tem as permissoes, manda intent para tirar foto
            dispatchTakePictureIntent();
        } else {
            requestPermissionLauncher.launch(permissions); //caso contrario, pede permissao
        }
    }

    private void checkAndRequestStoragePermissions() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 (API 33) e superior
            permissions = new String[]{Manifest.permission.READ_MEDIA_IMAGES};
        } else { // Android 12 (API 31) e anteriores
            permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        }

        boolean allGranted = true;
        for (String perm : permissions) { //verifica se tem permissoes necessarias
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) { //se tem, envia intent para galeria
            dispatchPickImageIntent();
        } else { //se nao, pede permissoes
            requestPermissionLauncher.launch(permissions);
        }
    }

    //Intent para escolher da Galeria
    private void dispatchPickImageIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    //Intent para tirar foto com a Câmera
    private void dispatchTakePictureIntent() {
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            Toast.makeText(this, "Erro ao criar arquivo para foto.", Toast.LENGTH_LONG).show();
            return;
        }

        // Usa FileProvider para obter um URI seguro
        cameraOutputUri = FileProvider.getUriForFile(this,
                getApplicationContext().getPackageName() + ".fileprovider",
                photoFile);
        takePictureLauncher.launch(cameraOutputUri);
    }

    // Método auxiliar para criar um arquivo temporário para a foto da câmera
    private File createImageFile() throws IOException {
        String timeStamp = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        }
        //define nome do arquivo baseado na data
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(getCacheDir(), "images"); //salva na pasta cache do app
        storageDir.mkdirs(); //cria o diretório se não existir

        File image = File.createTempFile(
                imageFileName,  /* prefixo */
                ".jpg",         /* sufixo */
                storageDir      /* diretório */
        );
        return image;
    }

    //metodo para verificar se imagem esta na rotação correta e roda-la se necessario
    private Bitmap rotateBitmapIfRequired(Bitmap img, InputStream inputStream, Uri uri) throws IOException {
        ExifInterface ei = getExifInterface(inputStream, uri);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);
    //verifica rotação atual da imagem e a gira caso necessario
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateBitmap(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateBitmap(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateBitmap(img, 270);
            default:
                return img;
        }
    }


    @NonNull
    private static ExifInterface getExifInterface(InputStream inputStream, Uri uri) throws IOException {
        ExifInterface ei = null;
        //tenta criar ExifInterface a partir do InputStream
        if (inputStream != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ei = new ExifInterface(inputStream);
            }
        } else {
            ei = new ExifInterface(Objects.requireNonNull(uri.getPath()));
        }

        assert ei != null;
        return ei;
    }

    //rotaciona a imagem
    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }


    public void onClickRegister(View v){
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextNomeSocial = findViewById(R.id.NomeSocial);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);

        String Nome = editTextNome.getText().toString();
        String NomeSocial = editTextNomeSocial.getText().toString();
        String Cpf = editTextCPF.getText().toString();
        String Telefone = editTextTelefone.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Data = editTextNascimento.getText().toString();

        if(Nome.trim().isEmpty() || NomeSocial.trim().isEmpty() || Cpf.trim().isEmpty() || Telefone.trim().isEmpty() || Email.trim().isEmpty() || Data.trim().isEmpty()){
            Toast.makeText(this,"Por favor, preencha todos os dados",Toast.LENGTH_SHORT).show();
            return;
        }

        LoadScreen.showLoading(getSupportFragmentManager(),"Cadastrando..."); //chama tela de carregamento enquanto faz comunicações com a api
        contato = new BodyCadastro();
        // Recebe os dados
        /* FORMATO DOS DADOS
        {
            "nome": "TesteAPI",
            "nomeSocial": "Teste",
            "dataNascimento": "2024-12-31",
            "emailPrincipal": "grupo8@gmail.com",
            "telefonePrincipal": "38999999999",
            "cpf": "333.333.333-33",
            "origem": 7,
            "token": "f2240ed12dca63c0a425f028cd88500e"
        }
        */

        contato.setNome(Nome);
        contato.setNomeSocial(NomeSocial);
        contato.setCpf(Cpf);
        contato.setTelefonePrincipal(Telefone);
        contato.setEmailPrincipal(Email);
        contato.setDataNascimento(Data);

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.cadastrarContato(contato, new ContatoCallback() {
            @Override
            public void onSuccess(RespostaCadastro response) {
                Log.i("API Teste", "Navegando para a tela de cadastro de notas");
                if (selectedImageUri != null) {
                    //define a imagem e envia pra api rubeus
                } else {
                    // Se o usuário não selecionou uma imagem, pode optar por cadastrar sem foto
                    // Ou forçar a seleção de uma foto
                }
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
                //navega para a página do usuário
                Intent it = new Intent(Register.this, RegisterData.class);
                it.putExtra("user", user);
                startActivity(it);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
}