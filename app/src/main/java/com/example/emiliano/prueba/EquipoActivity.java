package com.example.emiliano.prueba;


import android.app.Activity;
import android.database.DatabaseUtils;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.example.emiliano.prueba.Fragments.FormacionFragment;
import com.example.emiliano.prueba.Fragments.FormacionFragmentPosta;
import com.example.emiliano.prueba.Model.Jugador;
import com.example.emiliano.prueba.Model.SisJuego;
import com.example.emiliano.prueba.PostTask.AsyncTaskListener;
import com.example.emiliano.prueba.PostTask.PostTask;
import com.example.emiliano.prueba.SqlHandlers.OperacionesDB;

import org.json.JSONArray;

import java.util.ArrayList;

public class EquipoActivity extends AppCompatActivity {
    OperacionesDB db;
    final Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getApplicationContext().deleteDatabase("DTManBD.db");
        db = OperacionesDB
                .obtenerInstancia(getApplicationContext());
        String url = "jugadores.php";
        String params = "action=getjugadores&fecha=2016-10-26 20:35:04";
        PostTask wscall = new PostTask(activity, url, params, new AsyncTaskListener() {
            @Override
        public void onTaskComplete(JSONArray result) {
                Log.e("equipoactivity","entro"+ result);
                ArrayList<Jugador>  jugadores = Jugador.fromJson(result);
                db.actualizarJugadores(jugadores);
                Log.e("Detalles de jugadores", "Detalles de jugadores");
         //       DatabaseUtils.dumpCursor(db.obtenerJugadores());

                String url2 = "sisjuego.php";
                String params2 = "action=getsisjuego&fecha=2016-10-26 20:35:04";
                PostTask wscall2 = new PostTask(activity, url2, params2, new AsyncTaskListener() {
                    @Override
                    public void onTaskComplete(JSONArray result) {
                        Log.e("SISJUEGOOOOOOOOOOOOO","entro"+ result);
                        ArrayList<SisJuego> sisJuegos = SisJuego.fromJson(result);
                        db.actualizarSisJuegos(sisJuegos);

                        // Begin the transaction
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.phFragment, new FormacionFragmentPosta());
                        ft.commit();
                    }
                });
                wscall2.execute();

            }
        });
        wscall.execute();


    }



}
