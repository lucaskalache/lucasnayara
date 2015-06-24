package com.example.fcg1400019442.oboticario;

import android.content.Intent;
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
        String detailText = intent.getStringExtra(Intent.EXTRA_TEXT);

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            TextView detailTextView = (TextView) findViewById(R.id.detalhe_de_texto);
            detailTextView.setText(detailText);
        }

        // acha o botao
        // este botao, quem vai lidar com clique dele eh a classe tal

        Button buttonView = (Button) findViewById(R.id.botao);

        buttonView.setOnClickListener(new BotaoClicado());


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class BotaoClicado implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "teste")
                    .putExtra(AlarmClock.EXTRA_HOUR, 12)
                    .putExtra(AlarmClock.EXTRA_MINUTES, 34);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        }
    }


}
