package com.rp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OficialInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_information);

        TextView contatosOficiais = findViewById(R.id.contatosOficiais);
        contatosOficiais.setText(Html.fromHtml("<u>Contatos Oficiais</u>"));
        contatosOficiais.setTextSize(20f);

        TextView centralAtendimento = findViewById(R.id.centralAtendimento);
        centralAtendimento.setText("Central de Atendimento à Mulher");

        TextView secretariaPolitica = findViewById(R.id.secretariaPoliticas);
        secretariaPolitica.setText("Secretaria de Políticas para as Mulheres");

        TextView ligue180 = findViewById(R.id.ligue180);
        ligue180.setText("Ligue 180");

        TextView secretariaText1 = findViewById(R.id.secretariaText1);
        secretariaText1.setText("Recebe depoimentos através:");

        TextView secretariaText2 = findViewById(R.id.secretariaText2);
        secretariaText2.setText("ouvidoria@spm.gov.br  spmulheres@spmulheres.gov.br");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.oficial:
                return true;
            case R.id.ong:
                Intent intent2 = new Intent(this, ONGInformation.class);
                startActivity(intent2);
                return true;
            case R.id.psychologist:
                Intent intent3 = new Intent(this, PsychologistInformation.class);
                startActivity(intent3);
                return true;
            case R.id.violence:
                Intent intent4 = new Intent(this, ViolenceTypes.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
