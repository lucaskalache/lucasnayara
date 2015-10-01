package com.example.fcg1400019442.oboticario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("deprecation")
public class AtividadeDetalhes extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_detalhes);

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            long id = intent.getLongExtra(Intent.EXTRA_TEXT, 0L);

            SQLiteOpenHelper dbHelper = new produtosDBHELPER(getApplicationContext());
            SQLiteDatabase db;
            db = dbHelper.getWritableDatabase();

            Cursor cursor = db.query(
                    contratoDB.Produto.NOME_TABELA, // Tabela
                    null, // colunas (todas)
                    contratoDB.Produto._ID + " = " + id, // colunas para o where
                    null, // valores para o where
                    null, // group by
                    null, // having
                    null  // sort by
            );

            if (cursor.moveToNext()) {

                long data = cursor.getLong(cursor.getColumnIndex(contratoDB.Produto.COLUNA_DATA));
                String titulo = cursor.getString(cursor.getColumnIndex(contratoDB.Produto.COLUNA_NOME));
                String texto = cursor.getString(cursor.getColumnIndex(contratoDB.Produto.COLUNA_DESCRICAO));

                String dataBonita = new SimpleDateFormat("dd/MM/yyyy").format(new Date(data * 1000));


                SharedPreferences cliente = PreferenceManager.getDefaultSharedPreferences(this);
                String nome_cliente = cliente.getString(getString(R.string.conf_rep_chave), getString(R.string.conf_rep_padrao));

                TextView detailTextView1 = (TextView) findViewById(R.id.texto_data);
                detailTextView1.setText(dataBonita);

                TextView detailTextView2 = (TextView) findViewById(R.id.texto_nome);
                detailTextView2.setText(titulo);

                TextView detailTextView3 = (TextView) findViewById(R.id.texto_descricao);
                detailTextView3.setText(texto);
            }
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atividade_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent detailIntent = new Intent(getApplicationContext(), AtividadeConfiguracao.class);
            startActivity(detailIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

