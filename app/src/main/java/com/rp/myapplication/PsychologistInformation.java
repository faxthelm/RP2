package com.rp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class PsychologistInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist_information);

        TextView contatosOng = (TextView)findViewById(R.id.contatosOng);
        contatosOng.setText(Html.fromHtml("<u>Contatos de Psicólogos - Gratuitos ou a Preços Populares</u>"));
        contatosOng.setTextSize(20f);

        TextView tituloUnip = (TextView)findViewById(R.id.tituloUnip);
        tituloUnip.setText("Centro de Psicologia Aplicada UNIP");
        TextView unip = (TextView)findViewById(R.id.agenciaPatricia);
        unip.setText("\nDe segunda a sexta, das 8h às 21h\n" +
                "Telefone: 3341-4250\n" +
                "Endereço: Rua Apeninos, 595\n");

        TextView tituloMack = (TextView)findViewById(R.id.tituloMack);
        tituloMack.setText("Atendimento Psicológico Mackenzie");
        TextView mack = (TextView)findViewById(R.id.mack);
        mack.setText("\nDe segunda a sexta, das 8h às 18h\n" +
                "Telefone: 3256-6827 ou 3256-6217\n" +
                "Endereço: Rua Piauí, 181 - 1º andar\n");

        TextView tituloUnib = (TextView)findViewById(R.id.tituloUnib);
        tituloUnib.setText("Clínica de Psicologia UNIB - Universidade Ibirapuera");
        TextView unib = (TextView)findViewById(R.id.unib);
        unib.setText("\nDe segunda a sexta, das 13h às 21h. Sábados, das 8h às 13h.\n" +
                "Telefone: 5694-7961\n" +
                "Endereço: Avenida Interlagos, 1329\n");


        TextView tituloSAP = (TextView)findViewById(R.id.tituloSAP);
        tituloSAP.setText("Serviço de Aconselhamento Psicológico (SAP)");
        TextView sap = (TextView)findViewById(R.id.sap);
        sap.setText("\nAtendimento em esquema plantão. De segunda a sexta, das 7h às 19h. Sábados das 8h às 13h.\n" +
                "Telefone: 3091-5015\n" +
                "Endereço: Avenida Professor Mello de Morais, 1721 - Bloco D - Butantã\n");

        TextView tituloUniban = (TextView)findViewById(R.id.tituloUniban);
        tituloUniban.setText("Clínica de Psicologia - Uniban Maria Cândida");
        TextView uniban = (TextView)findViewById(R.id.uniban);
        uniban.setText("\nDe segunda a sexta, das 7h às 22h\n" +
                "Telefone: 2967-9035 ou 2967-9031\n" +
                "Endereço: Rua Maria Cândida, 1813 - Vila Guilherme\n");

        TextView tituloFmu = (TextView)findViewById(R.id.tituloFmu);
        tituloFmu.setText(" Clínica de Psicologia FMU - Campus Santo Amaro");
        TextView fmu = (TextView)findViewById(R.id.fmu);
        fmu.setText("\nDe segunda a sexta, das 8h às 21h. Sábados, das 8h às 15h\n" +
                "Telefone: 3040-3400\n" +
                "Endereço: Avenida Santo Amaro, 1239 - Vila Nova Conceição\n");

        TextView tituloPan = (TextView)findViewById(R.id.tituloPan);
        tituloPan.setText("PAN - Programa de Atendimento Neuro-psico-geriátrico");
        TextView pan = (TextView)findViewById(R.id.pan);
        pan.setText("\nDe segunda à sexta, das 8h às 17h\n" +
                "Telefone: 5576-4991\n" +
                "Endereço: Rua Borges Lagoa, 570\n");

            TextView tituloPropisc = (TextView)findViewById(R.id.tituloPropisc);
        tituloPropisc.setText("PROPISC - Programa de Psicoterapia");
        TextView propisc = (TextView)findViewById(R.id.propisc);
        propisc.setText("\nDe segunda à sexta, das 8h às 17h\n" +
                "Telefone: 5904-3961\n" +
                "Endereço: Rua Coronel Lisboa, 969\n");

        TextView tituloUsp = (TextView)findViewById(R.id.tituloUsp);
        tituloUsp.setText("Instituto de Psicologia da USP (Serviço de Aconselhamento Psicológico - SAP)\n");
        TextView usp = (TextView)findViewById(R.id.usp);
        usp.setText("\nDe segunda à sexta, das 7h às 19h. Sábados, das 8h às 13h.\n" +
                "Telefone: 3091-5015\n" +
                "Endereço: Avenida Professor Mello de Morais, 1721 - Bloco D - Butantã\n");


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
                Intent intent2 = new Intent(this, OficialInformation.class);
                startActivity(intent2);
                return true;
            case R.id.ong:
                Intent intent3 = new Intent(this, ONGInformation.class);
                startActivity(intent3);
                return true;
            case R.id.psychologist:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
