package com.rp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OficialInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_information);

        TextView contatosOficiais = findViewById(R.id.contatosOng);
        contatosOficiais.setText(Html.fromHtml("<u>Contatos Oficiais</u>"));

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
}
