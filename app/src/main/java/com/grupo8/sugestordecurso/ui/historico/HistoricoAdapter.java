package com.grupo8.sugestordecurso.ui.historico;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoricoAdapter extends BaseAdapter {

    private final Context context;
    private final Cursor cursor;

    public HistoricoAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        cursor.moveToPosition(position);
        return cursor;
    }

    @Override
    public long getItemId(int position) {
        cursor.moveToPosition(position);
        return cursor.getLong(cursor.getColumnIndexOrThrow("id"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        cursor.moveToPosition(position);

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sugest_item, parent, false);
        }

        TextView txtCurso = view.findViewById(R.id.txtCurso);
        TextView txtData = view.findViewById(R.id.txtData);
        TextView txtBase = view.findViewById(R.id.txtBase);

        // Obtém os dados do banco
        long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"));
        String curso1 = cursor.getString(cursor.getColumnIndexOrThrow("curso1"));
        String curso2 = cursor.getString(cursor.getColumnIndexOrThrow("curso2"));
        String curso3 = cursor.getString(cursor.getColumnIndexOrThrow("curso3"));

        // Formata data
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date(timestamp));
        txtData.setText(dataFormatada);

        // Mostra o curso principal e os secundários
        txtCurso.setText("Sugestão: " + curso1);
        txtBase.setText("Outras opções: " + curso2 + ", " + curso3);

        return view;
    }
}
