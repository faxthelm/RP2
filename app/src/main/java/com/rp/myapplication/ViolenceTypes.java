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

public class ViolenceTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violence_types);

        TextView violenceTypes = (TextView)findViewById(R.id.violenceTypes);
        violenceTypes.setText(Html.fromHtml("<u>Tipos de Violência</u>"));
        violenceTypes.setTextSize(20f);

        TextView tituloFisica = (TextView)findViewById(R.id.tituloFisica);
        tituloFisica.setText("Violência Física");
        TextView fisica = (TextView)findViewById(R.id.fisica);
        fisica.setText("Qualquer ação que ofenda a integridade ou a saúde " +
                "corporal da mulher. \n Exemplos: tapas, socos, chutes, puxão de cabelo, " +
                "arremesso de objetos, tentativas de estrangulamento.");

        TextView tituloSexual = (TextView)findViewById(R.id.tituloSexual);
        tituloSexual.setText("Violência Sexual");
        TextView sexual = (TextView)findViewById(R.id.sexual);
        sexual.setText("Qualquer conduta que force a mulher a assistir, " +
                "manter ou participar de relação sexual não desejada, impedí-la de usar camisinha ou " +
                "pílula anticoncepcional, forçá-la a casar, engravidar, abortar ou se prostituir.\n");

        TextView tituloPsicologica = (TextView)findViewById(R.id.tituloPsicologica);
        tituloPsicologica.setText("Violência Psicológica");
        TextView psicologica = (TextView)findViewById(R.id.psicologica);
        psicologica.setText("Qualquer conduta que gera diminuição da auto-estima, " +
                "dano emocional, humilhação ou que desqualifique as ações, comportamento, " +
                "crenças e decisões da mulher, por meio da ameaça, insulto, xingamento, perseguição, chantagem, " +
                "ridicularização, isolamento, perseguição ou qualquer outro meio que cause prejuízo à saúde psicológica " +
                "e à autodeterminação.\n");

        TextView tituloPatrimonial = (TextView)findViewById(R.id.tituloPatrimonial);
        tituloPatrimonial.setText("Violência Patrimonial");
        TextView patrimonial = (TextView)findViewById(R.id.patrimonial);
        patrimonial.setText("Qualquer conduta que configure quebra, venda, destruição ou " +
                "subtração de objetos, instrumentos de trabalho, documentos pessoais, dinheiro, cheques ou cartões de banco.");

        TextView tituloMoral = (TextView)findViewById(R.id.tituloMoral);
        tituloMoral.setText("Violência Moral");
        TextView moral = (TextView)findViewById(R.id.moral);
        moral.setText("Caluniar, difamar ou injuriar a mulher, prejudicando a sua reputação e dignidade.\n");

        TextView tituloDomestica = (TextView)findViewById(R.id.tituloDomestica);
        tituloDomestica.setText("Violência Doméstica");
        TextView domestia = (TextView)findViewById(R.id.domestica);
        domestia.setText("É qualquer agressão física, psicológica, moral, " +
                "sexual ou patrimonial, contra a mulher, partindo de homens ou mulheres:\n Com quem ela more ou tenha morado," +
                " seja aparentada por laços de família ou de amizade;\n " +
                "Tenha ou tenha tido vínculo amoroso, mesmo sem ter morado junto. \n");




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
                Intent intent4 = new Intent(this, PsychologistInformation.class);
                startActivity(intent4);
                return true;
            case R.id.violence:
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
