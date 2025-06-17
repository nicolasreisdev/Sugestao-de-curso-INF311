package com.grupo8.sugestordecurso.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupo8.sugestordecurso.data.models.Utils.User;

import org.json.JSONObject;

public final class BancoDados {
    protected SQLiteDatabase db;
    private final String NOME_BANCO = "predicoes";
    private static BancoDados INSTANCE;

    //private final String[] SCRIPT_DATABASE_CREATE;

    private BancoDados() {
        //Obtem contexto da aplicação usando a classe criada para essa finalidade
        Context ctx = MyApp.getAppContext();
        // Abre o banco de dados já existente ou então cria um banco novo
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
        // Chama a função que cria as tabelas do banco se não existirem
        String createTable = "CREATE TABLE historico_predicoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER, " +
                "area_preferencia TEXT, " +
                "matematica REAL, portugues REAL, literatura REAL, redacao REAL, " +
                "quimica REAL, fisica REAL, biologia REAL, " +
                "filosofia REAL, sociologia REAL, geografia REAL, historia REAL, artes REAL, " +
                "curso1 TEXT, prob1 REAL, " +
                "curso2 TEXT, prob2 REAL, " +
                "curso3 TEXT, prob3 REAL, " +
                "timestamp INTEGER)";

        /*

        //busca por tabelas existentes no banco igual "show tables" do MySQL
        //SELECT * FROM sqlite_master WHERE type = "table"
        Cursor c = buscar("sqlite_master", null, "type = 'table'", "");
        //Cria tabelas do banco de dados caso o mesmo estiver vazio.
        //bancos criados pelo método openOrCreateDatabase() POSSUEM UMA TABELA
        //PADRÃO "android_metadata"
        if(c.getCount() == 1){
            for(int i = 0; i < SCRIPT_DATABASE_CREATE.length; i++){
                db.execSQL(SCRIPT_DATABASE_CREATE[i]);
            }
            Log.i("BANCO_DADOS", "Criou tabelas do banco e as populou.");
        }
        c.close();


        */
    }

    public void salvarPredicao(User user,
                               String curso1, float prob1,
                               String curso2, float prob2,
                               String curso3, float prob3) {

        ContentValues values = new ContentValues();

        // Dados do usuário
        values.put("id_usuario", user.getId());
        values.put("area_preferencia", user.getAreaPreferencia());

        // Notas
        values.put("matematica", user.getNotaMatematica());
        values.put("portugues", user.getNotaPortugues());
        values.put("literatura", user.getNotaLiteratura());
        values.put("redacao", user.getNotaRedacao());
        values.put("quimica", user.getNotaQuimica());
        values.put("fisica", user.getNotaFisica());
        values.put("biologia", user.getNotaBiologia());
        values.put("filosofia", user.getNotaFilosofia());
        values.put("sociologia", user.getNotaSociologia());
        values.put("geografia", user.getNotaGeografia());
        values.put("historia", user.getNotaHistoria());
        values.put("artes", user.getNotaArtes());

        // Cursos recomendados
        values.put("curso1", curso1);
        values.put("prob1", prob1);
        values.put("curso2", curso2);
        values.put("prob2", prob2);
        values.put("curso3", curso3);
        values.put("prob3", prob3);

        // Timestamp
        values.put("timestamp", System.currentTimeMillis());

        db.insert("historico_predicoes", null, values);
        db.close();
    }


    public Cursor listarPredicoes() {
        return buscar("predicoes_curso", null, "", "data_hora DESC");
    }

    public long inserir(String tabela, ContentValues valores) {
        long id = db.insert(tabela, null, valores);
        Log.i("BANCO_DADOS", "Cadastrou registro com o id [" + id + "]");
        return id;
    }

    public int atualizar(String tabela, ContentValues valores, String where) {
        int count = db.update(tabela, valores, where, null);
        Log.i("BANCO_DADOS", "Atualizou [" + count + "] registros");
        return count;
    }

    public int deletar(String tabela, String where) {
        int count = db.delete(tabela, where, null);
        Log.i("BANCO_DADOS", "Deletou [" + count + "] registros");
        return count;
    }

    public Cursor buscar(String tabela, String colunas[], String where, String orderBy) {
        Cursor c;
        if(!where.equals(""))
            c = db.query(tabela, colunas, where, null, null, null, orderBy);
        else
            c = db.query(tabela, colunas, null, null, null, null, orderBy);
        Log.i("BANCO_DADOS", "Realizou uma busca e retornou [" + c.getCount() + "] registros.");
        return c;
    }

    private void abrir() {
        Context ctx = MyApp.getAppContext();
        if(!db.isOpen()){
        // Abre o banco de dados já existente
            db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
            Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
        }else{
            Log.i("BANCO_DADOS", "Conexão com o banco já está aberta.");
        }
    }

    public static BancoDados getInstance(){
        if(INSTANCE == null)
            INSTANCE = new BancoDados();
        INSTANCE.abrir(); //abre conexão se estiver fechada
        return INSTANCE;
    }

    public void fechar() {
        if (db != null && db.isOpen()) {
            db.close();
            Log.i("BANCO_DADOS", "Fechou conexão com o Banco.");
        }
    }

}
