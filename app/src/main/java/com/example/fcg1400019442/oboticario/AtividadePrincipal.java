package com.example.fcg1400019442.oboticario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.text.SimpleDateFormat;

import java.util.Date;


@SuppressWarnings("ALL")
public class AtividadePrincipal extends ActionBarActivity {
    private ArrayAdapter<String> mAdaptador;
    private Map<Integer, Long> mapaIds = new HashMap<Integer, Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_principal);

        List<String> oboticario = new ArrayList<>();

        mAdaptador = new ArrayAdapter<>(
                getApplicationContext(), // Contexto atual
                R.layout.item_lista_principal, // Nome do ID do layout
                R.id.item_texto, // ID do TextView a ser preenchido
                oboticario);

        ListView listView = (ListView) findViewById(R.id.lista_principal);
        listView.setAdapter(mAdaptador);

        listView.setOnItemClickListener(new ItemClicado());
    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizaInterface();
    }

    private void atualizar() {
        PegaDadosDoServidor pega = new PegaDadosDoServidor();
        pega.execute();
    }

    private void atualizaInterface() {
        SQLiteOpenHelper dbHelper = new produtosDBHELPER(getApplicationContext());
        SQLiteDatabase db;
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(
                contratoDB.Produto.NOME_TABELA, // Tabela
                null, // colunas (todas)
                null, // colunas para o where
                null, // valores para o where
                null, // group by
                null, // having
                null  // sort by
        );

        mAdaptador.clear();
        mapaIds.clear();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(contratoDB.Produto._ID));
            long data = cursor.getLong(cursor.getColumnIndex(contratoDB.Produto.COLUNA_DATA));
            String titulo = cursor.getString(cursor.getColumnIndex(contratoDB.Produto.COLUNA_NOME));
            String texto = cursor.getString(cursor.getColumnIndex(contratoDB.Produto.COLUNA_DESCRICAO));

            String dataBonita = new SimpleDateFormat("dd/MM/yyyy").format(new Date(data * 1000));

            // Associa a posição do item ao id dele
            mapaIds.put(mAdaptador.getCount(), id);

            // Não estou usando o texto, mas poderia
            mAdaptador.add(id + " - " + dataBonita + " - " + titulo);
        }

        db.close();
        dbHelper.close();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atividade_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.action_refresh:
                atualizar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ItemClicado implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent detailIntent = new Intent(getApplicationContext(), AtividadeDetalhes.class);
            detailIntent.putExtra(Intent.EXTRA_TEXT, mapaIds.get(position));
            startActivity(detailIntent);

        }

    }

    public class PegaDadosDoServidor extends AsyncTask<Void, Void, String[][]> {

        @Override
        protected String[][] doInBackground(Void... voids) {
            ServidorFalso servidor = new ServidorFalso();
            return servidor.pegaDados();
        }

        @Override
        protected void onPostExecute(String[][] result) {
            if (result != null) {

                SQLiteOpenHelper dbHelper = new produtosDBHELPER(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // LIMPA A TABELA INTEIRA!!!!
                db.delete(contratoDB.Produto.NOME_TABELA, null, null);

                for (String linha[] : result) {
                    ContentValues entrada = new ContentValues();
                    entrada.put(contratoDB.Produto.COLUNA_DATA, linha[0]);
                    entrada.put(contratoDB.Produto.COLUNA_NOME, linha[1]);
                    entrada.put(contratoDB.Produto.COLUNA_DESCRICAO, linha[2]);

                    db.insert(contratoDB.Produto.NOME_TABELA, null, entrada);

                }

                db.close();
                dbHelper.close();
            }

            atualizaInterface();
        }
    }
}

