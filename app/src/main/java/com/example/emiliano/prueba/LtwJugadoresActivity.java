package com.example.emiliano.prueba;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LtwJugadoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltw_jugadores);

// Construct the data source
        ArrayList<Jugador> arrayOfJugadores = new ArrayList<Jugador>();
// Create the adapter to convert the array to views
        JugadoresAdapter adapter = new JugadoresAdapter(this, arrayOfJugadores);
// Attach the adapter to a ListView

        Jugador newJugador = new Jugador("Marco Ruben", "12");
        adapter.add(newJugador);
        Jugador newJugador1 = new Jugador("Paulo Ferrari", "6");
        adapter.add(newJugador1);
        Jugador newJugador2 = new Jugador("Teo Gutierrez", "8");
        adapter.add(newJugador2);
     //   Jugador newJugador2 = new Jugador("Ferrari", 5);
      //  adapter.add(newJugador2);
        ListView listView = (ListView) findViewById(R.id.ltwjugadores);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(LtwJugadoresActivity.this, "llega", Toast.LENGTH_SHORT).show();
            }
        });

    }




}