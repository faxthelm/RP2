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

public class ONGInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onginformation);
        

        TextView contatosOng = (TextView)findViewById(R.id.contatosOng);
        contatosOng.setText(Html.fromHtml("<u>Contatos ONG</u>"));
        contatosOng.setTextSize(20f);

        TextView agenciaPatricia = (TextView)findViewById(R.id.agenciaPatricia);
        agenciaPatricia.setText("\nProjeto: A Agência Patrícia Galvão é uma iniciativa do Instituto Patrícia Galvão – " +
                "Mídia e Direitos e foi criada em 2009 para atuar na produção de notícias e conteúdos sobre os direitos das mulheres brasileiras. \n\n" +
                "E-mail: contato@patriciagalvao.org.br\n" +
                "Site: www.agenciapatriciagalvao.org.br\n" +
                "Telefone: (11) 3266-5434\n\n"+
                "Endereço: Av. Brigadeiro Luís Antônio, 2050 – Bela Vista, São Paulo – SP, 01317-000\n");
        TextView tituloPatricia = (TextView)findViewById(R.id.tituloPatricia);
        tituloPatricia.setText("Agência Patrícia Galvão");

        TextView agenciaAssociacao = (TextView)findViewById(R.id.agenciaAssociacao);
        agenciaAssociacao.setText("\nProjeto: Projeto: Amzol atende mulheres vítimas de violência\n\n" +
                "E-mail: amzol@ig.com.br\n\n" +
                "Endereço: Endereço: 669 – R. Pedro Soares de Andrade, 619 – Vila Rosaria, São Paulo – SP, 08021-040\n\n");
        TextView tituloAssociacao = (TextView)findViewById(R.id.tituloAssociacao);
        tituloAssociacao.setText("Associação de Mulheres da Zona Leste");

        TextView tituloBemMeQuer = (TextView)findViewById(R.id.tituloBemMeQuer);
        tituloBemMeQuer.setText("Bem Me Quer");
        TextView bemMeQuer = (TextView)findViewById(R.id.bemMeQuer);
        bemMeQuer.setText("\nProjeto: Núcleo de atenção integral à mulher em situação de violência sexual, Hospital Pérola Byington\n\n" +
                "E-mail: crsm-ouvidoria@saude.sp.gov.br\n" +
                "Telefone: 3248-8000\n\n" +
                "Endereço: Avenida Brig. Luís Antônio, 683 Bela Vista – São Paulo\n");

        TextView tituloCasaBrasilandia = (TextView)findViewById(R.id.tituloCasaBrasilandia);
        tituloCasaBrasilandia.setText("Casa Brasilândia");
        TextView casaBrasilandia = (TextView) findViewById(R.id.casaBrasilandia);
        casaBrasilandia.setText("\nProjeto: Oferecer assistência psicossocial e jurídica à mulher em situação de violência\n\n" +
                "E-mail: casabrasilandia@ig.com.br\n" +
                "Telefone: (0xx11) 3983.4294; 3984.9816\n\n" +
                "Endereço: R. Silvio Bueno Peruche, nº 538 – Vila Brasilândia\n");

        TextView tituloCasaLilith = (TextView)findViewById(R.id.tituloCasaLilith);
        tituloCasaLilith.setText("Casa da Mulher Lilith");
        TextView casaLilith = (TextView)findViewById(R.id.casaLilith);
        casaLilith.setText("\nProjeto: Prevenção da saúde e assistência jurídica às mulheres vítimas de violência\n\n" +
                "E-mail: casalilith@uol.com.br\n" +
                "Telefone: (11) 2917-3710\n\n" +
                "Endereço: R. Paratiquara, 33 – ap 4, São Paulo – SP\n");

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
                return true;
            case R.id.psychologist:
                Intent intent3 = new Intent(this, PsychologistInformation.class);
                startActivity(intent3);
                return true;
            case R.id.violence:
                Intent intent4 = new Intent(this, ViolenceTypes.class);
                startActivity(intent4);
                return true;
            case R.id.report:
                Intent intent5 = new Intent(this, ReportActivity.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}