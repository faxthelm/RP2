package com.rp.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.rp.myapplication.model.TipoDenuncia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    Spinner         spinnerTipoDenuncia;
    EditText        editTextDataOcorrido;
    EditText        editTextLocal;
    RadioGroup      radioGroupIdade;
    RadioButton     radioButtonPrimeiro;
    RadioButton     radioButtonSegundo;
    RadioButton     radioButtonTerceiro;
    RadioButton     radioButtonQuarto;
    RadioGroup      radioGroupConhecido;
    RadioButton     radioButtonFamiliar;
    RadioButton     radioButtonVista;
    RadioButton     radioButtonDesconhecido;
    RadioGroup      radioGroupAmbiente;
    RadioButton     radioButtonPublico;
    RadioButton     radioButtonPrivado;
    RadioButton     radioButtonDomiciliar;

    DatePickerDialog datePickerDialogDataOcorrido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //CARREGA AS OPÇÕES DE ESTADO CIVIL
        this.CriarComponentes();
        this.CriarEventos();
        this.CarregaTiposViolencia();
    }

    private void CarregaTiposViolencia() {
        ArrayAdapter<TipoDenuncia> arrayAdapter;

        List<TipoDenuncia> itens = new ArrayList<TipoDenuncia>();

        itens.add(new TipoDenuncia("am", "Assédio moral"));
        itens.add(new TipoDenuncia("ap", "Assédio psicologico"));
        itens.add(new TipoDenuncia("as", "Assédio sexual"));
        itens.add(new TipoDenuncia("vd", "Violência doméstica"));
        itens.add(new TipoDenuncia("vf", "Violência física"));
        itens.add(new TipoDenuncia("vs", "Violência sexual"));

        arrayAdapter = new ArrayAdapter<TipoDenuncia>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDenuncia.setAdapter(arrayAdapter);

    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void CriarComponentes() {

        spinnerTipoDenuncia = (Spinner) this.findViewById(R.id.spinnerTipoDenuncia);
        editTextLocal = (EditText) this.findViewById(R.id.editTextLocal);
        editTextDataOcorrido = (EditText) this.findViewById(R.id.editTextDataOcorrido);
        radioGroupIdade = (RadioGroup) this.findViewById(R.id.radioGroupIdade);
        radioButtonPrimeiro = (RadioButton) this.findViewById(R.id.radioButtonPrimeiro);
        radioButtonSegundo = (RadioButton) this.findViewById(R.id.radioButtonSegundo);
        radioButtonTerceiro = (RadioButton) this.findViewById(R.id.radioButtonTerceiro);
        radioButtonQuarto = (RadioButton) this.findViewById(R.id.radioButtonQuarto);
        radioGroupConhecido = (RadioGroup) this.findViewById(R.id.radioGroupConhecido);
        radioButtonFamiliar = (RadioButton) this.findViewById(R.id.radioButtonFamiliar);
        radioButtonVista = (RadioButton) this.findViewById(R.id.radioButtonVista);
        radioButtonDesconhecido = (RadioButton) this.findViewById(R.id.radioButtonDesconhecido);
        radioGroupAmbiente = (RadioGroup) this.findViewById(R.id.radioGroupAmbiente);
        radioButtonPublico = (RadioButton) this.findViewById(R.id.radioButtonPublico);
        radioButtonPrivado = (RadioButton) this.findViewById(R.id.radioButtonPrivado);
        radioButtonDomiciliar = (RadioButton) this.findViewById(R.id.radioButtonDomiciliar);
    }


    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {


        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual = calendarDataAtual.get(Calendar.DAY_OF_MONTH);

        //MONTANDO O OBJETO DE DATA PARA PREENCHER O CAMPOS QUANDO  FOR SELECIONADO
        //FORMATO DD/MM/YYYY
        datePickerDialogDataOcorrido = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {

                //FORMATANDO O MÊS COM DOIS DÍGITOS
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1) : String.valueOf(mesSelecionado));

                editTextDataOcorrido.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);

            }

        }, anoAtual, mesAtual, diaAtual);


        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        editTextDataOcorrido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                datePickerDialogDataOcorrido.show();
            }
        });


        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        editTextDataOcorrido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                datePickerDialogDataOcorrido.show();

            }
        });
    }

    // MENU
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
                Intent intent5 = new Intent(this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.oficial:
                Intent intent2 = new Intent(this, OficialInformation.class);
                startActivity(intent2);
                return true;
            case R.id.ong:
                Intent intent = new Intent(this, ONGInformation.class);
                startActivity(intent);
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
