package com.example.emiliano.prueba;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class EquipoActivity extends AppCompatActivity implements View.OnClickListener{
private ImageButton buttonArquero ;
private ImageButton buttonDef1 ;
private ImageButton buttonDef2 ;
private ImageButton buttonDef3 ;
private ImageButton buttonDef4 ;
private ImageButton buttonMed1 ;
private ImageButton buttonMed2 ;
private ImageButton buttonMed3 ;
private ImageButton buttonDel1 ;
private ImageButton buttonDel2 ;
private ImageButton buttonDel3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonArquero = (ImageButton)findViewById(R.id.btnArq);
        buttonArquero.setOnClickListener(this);
        buttonDef1 = (ImageButton)findViewById(R.id.btnDef1);
        buttonDef1.setOnClickListener(this);
        buttonDef2 = (ImageButton)findViewById(R.id.btnDef2);
        buttonDef2.setOnClickListener(this);
        buttonDef3 = (ImageButton)findViewById(R.id.btnDef3);
        buttonDef3.setOnClickListener(this);
        buttonDef4 = (ImageButton)findViewById(R.id.btnDef4);
        buttonDef4.setOnClickListener(this);
        buttonMed1 = (ImageButton)findViewById(R.id.btnMed1);
        buttonMed1.setOnClickListener(this);
        buttonMed2 = (ImageButton)findViewById(R.id.btnMed2);
        buttonMed2.setOnClickListener(this);
        buttonMed3 = (ImageButton)findViewById(R.id.btnMed3);
        buttonMed3.setOnClickListener(this);
        buttonDel1 = (ImageButton)findViewById(R.id.btnDel1);
        buttonDel1.setOnClickListener(this);
        buttonDel2 = (ImageButton)findViewById(R.id.btnDel2);
        buttonDel2.setOnClickListener(this);
        buttonDel3 = (ImageButton)findViewById(R.id.btnDel3);
        buttonDel3.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, LtwJugadoresActivity.class);
        startActivity(i);

    }

}
