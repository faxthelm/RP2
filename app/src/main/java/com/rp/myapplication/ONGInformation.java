package com.rp.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ONGInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onginformation);
        TextView contatosOng = (TextView)findViewById(R.id.contatosOng);
        contatosOng.setText("Contatos ONG");

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
        agenciaAssociacao.setText("\nProjeto: Projeto: Amzol atende mulheres vítimas de violência\n" +
                "E-mail: amzol@ig.com.br\n\n" +
                "Endereço: Endereço: 669 – R. Pedro Soares de Andrade, 619 – Vila Rosaria, São Paulo – SP, 08021-040\n\n");
        TextView tituloAssociacao = (TextView)findViewById(R.id.tituloAssociacao);
        tituloAssociacao.setText("Associação de Mulheres da Zona Leste");


    }
}