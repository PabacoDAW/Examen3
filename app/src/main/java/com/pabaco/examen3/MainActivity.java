package com.pabaco.examen3;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    EditText editNombre;
    RadioGroup rdgGenero, rdgPregunta;
    RadioButton rdRespuesta1, rdRespuesta2, rdRespuesta3,
    rdHombre, rdMujer, rdOtro;
    SwitchCompat swMayor;
    Spinner spPregunta;
    Button btnEnviar;
    CheckBox chLog;

    AdapterView.OnItemSelectedListener adapterSpinner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            CambiarRespuestas();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNombre = findViewById(R.id.editNombre);
        chLog = findViewById(R.id.chLog);
        swMayor = findViewById(R.id.swMayor);
        spPregunta = findViewById(R.id.spPregunta);
        rdgPregunta = findViewById(R.id.rdgRespuesta);
        rdgGenero = findViewById(R.id.rdgGenero);
        rdRespuesta1 = findViewById(R.id.rdRespuesta1);
        rdRespuesta2 = findViewById(R.id.rdRespuesta2);
        rdRespuesta3 = findViewById(R.id.rdRespuesta3);
        rdHombre = findViewById(R.id.rdHombre);
        rdMujer = findViewById(R.id.rdMujer);
        rdOtro = findViewById(R.id.rdOtro);

        findViewById(R.id.btnEnviar).setOnClickListener(v -> {
            EnviarForm();
            EnviarLogs();
        });
        spPregunta.setOnItemSelectedListener(adapterSpinner);
        swMayor.setOnCheckedChangeListener((btn,bool) -> CambiarPreguntas());

    }

    private void EnviarLogs() {
        if(chLog.isChecked()){
            if(PreguntaRespondida() || editNombre.getText().toString().trim().isEmpty()) {
                if (editNombre.getText().toString().trim().isEmpty()) {
                    Log.e("Barragan", "Falta el nombre");
                }
                if (PreguntaRespondida()) {
                    Log.e("Barragan", "Ninguna pregunta ha sido respondida");
                }
            } else {
                Log.i("Barragan", "Nombre: " + editNombre.getText());
                Log.i("Barragan", "Género: " + TextoGenero());
                Log.i("Barragan", "Mayor de edad: " + (chLog.isChecked()? "Sí" : "No"));
                Log.i("Barragan", "Pregunta: " + spPregunta.getSelectedItem().toString());
                Log.i("Barragan", "Respuesta: " + TextoRespuesta());
            }
        }
    }

    private String TextoRespuesta() {
        String txt = "";
        if (rdgPregunta.getCheckedRadioButtonId() == rdRespuesta1.getId()){
            txt = rdRespuesta1.getText().toString();
        } else if (rdgPregunta.getCheckedRadioButtonId() == rdRespuesta2.getId()) {
            txt = rdRespuesta1.getText().toString();
        } else if (rdgPregunta.getCheckedRadioButtonId() == rdRespuesta3.getId()) {
            txt = rdRespuesta1.getText().toString();
        }
        return txt;
    }

    private String TextoGenero() {
        String txt = "";
        if (rdgGenero.getCheckedRadioButtonId() == rdHombre.getId()){
            txt = rdHombre.getText().toString();
        } else if (rdgGenero.getCheckedRadioButtonId() == rdMujer.getId()) {
            txt = rdMujer.getText().toString();
        } else if (rdgGenero.getCheckedRadioButtonId() == rdOtro.getId()) {
            txt = rdOtro.getText().toString();
        }
        return txt;
    }

    private void EnviarForm() {
        if (editNombre.getText().toString().trim().isEmpty() || PreguntaRespondida()){
            Toast.makeText(this, editNombre.getText().toString().trim().isEmpty()?
                    "Es obligatorio el nombre" : "Ninguna respuesta ha sido marcada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Respuesta enviada" , Toast.LENGTH_LONG).show();
        }
    }

    private boolean PreguntaRespondida() {
        return !rdRespuesta1.isChecked() && !rdRespuesta2.isChecked() && !rdRespuesta3.isChecked();
    }

    private void CambiarPreguntas() {
        if(swMayor.isChecked()){
            spPregunta.setAdapter(ArrayAdapter.createFromResource(this, R.array.straPreguntas1, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item));
        } else {
            spPregunta.setAdapter(ArrayAdapter.createFromResource(this, R.array.straPreguntas2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item));
        }
    }

    private void CambiarRespuestas() {
        rdRespuesta1.setChecked(false);
        rdRespuesta2.setChecked(false);
        rdRespuesta3.setChecked(false);
        if(spPregunta.getSelectedItem().toString().equals("Lenguaje de programación favorito")){
            rdRespuesta1.setText(R.string.respuesta1);
            rdRespuesta2.setText(R.string.respuesta2);
            rdRespuesta3.setText(R.string.respuesta3);
        } else if (spPregunta.getSelectedItem().toString().equals("Época del año favorita")) {
            rdRespuesta1.setText(R.string.respuesta6);
            rdRespuesta2.setText(R.string.respuesta5);
            rdRespuesta3.setText(R.string.respuesta4);
        } else if (spPregunta.getSelectedItem().toString().equals("Asignatura favorita")) {
            rdRespuesta1.setText(R.string.respuesta9);
            rdRespuesta2.setText(R.string.respuesta8);
            rdRespuesta3.setText(R.string.respuesta7);
        } else if (spPregunta.getSelectedItem().toString().equals("Idioma que te gustaría hablar")) {
            rdRespuesta1.setText(R.string.respuesta10);
            rdRespuesta2.setText(R.string.respuesta12);
            rdRespuesta3.setText(R.string.respuesta11);
        }
    }
}