package com.example.fcg1400019442.oboticario;

import android.content.Intent;
import android.content.SharedPreferences;
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


@SuppressWarnings("deprecation")
public class AtividadeDetalhes extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_detalhes);

        Intent intent = getIntent();
        long id = intent.getLongExtra(Intent.EXTRA_TEXT, 0L);

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            TextView detailTextView = (TextView) findViewById(R.id.detalhe_de_texto);

          SharedPreferences cliente = PreferenceManager.getDefaultSharedPreferences(this);

          String nome_cliente = cliente.getString(getString(R.string.conf_rep_chave),getString(R.string.conf_rep_padrao));

          detailTextView.setText(Long.toString(id));
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
            Intent detailIntent = new Intent(getApplicationContext(),AtividadeConfiguracao.class);
            startActivity(detailIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
