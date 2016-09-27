package com.example.emiliano.prueba;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class EquipoActivity extends AppCompatActivity implements View.OnClickListener{
private ImageButton buttonJug ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonJug = (ImageButton)findViewById(R.id.btnJug);
        buttonJug.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, LtwJugadoresActivity.class);
        startActivity(i);

    }

}
