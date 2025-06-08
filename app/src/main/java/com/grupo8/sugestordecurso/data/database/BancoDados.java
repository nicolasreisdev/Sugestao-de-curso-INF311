package com.grupo8.sugestordecurso.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
