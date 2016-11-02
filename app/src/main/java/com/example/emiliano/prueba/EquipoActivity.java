package com.example.emiliano.prueba;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.emiliano.prueba.Fragments.FormacionFrg;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Mi equipo");
        setSupportActionBar(toolbar);




        //getApplicationContext().deleteDatabase("DTManBD.db");
        db = OperacionesDB
                .obtenerInstancia(getApplicationContext());
        String url = "jugadores.php";
        String params = "action=getjugadores&fecha=2016-09-02 20:35:04";
        PostTask wscall = new PostTask(activity, url, params, new AsyncTaskListener() {
            @Override
        public void onTaskComplete(JSONArray result) {
                Log.e("equipoactivity","entro"+ result);
                ArrayList<Jugador>  jugadores = Jugador.fromJson(result);
                db.actualizarJugadores(jugadores);
                Log.e("Detalles de jugadores", "Detalles de jugadores");
         //       DatabaseUtils.dumpCursor(db.obtenerJugadores());

                String url2 = "sisjuego.php";
                String params2 = "action=getsisjuego&fecha=2016-09-26 20:35:04";
                PostTask wscall2 = new PostTask(activity, url2, params2, new AsyncTaskListener() {
                    @Override
                    public void onTaskComplete(JSONArray result) {
                        Log.e("SISJUEGOOOOOOOOOOOOO","entro"+ result);
                        ArrayList<SisJuego> sisJuegos = SisJuego.fromJson(result);
                        db.actualizarSisJuegos(sisJuegos);

                        // Begin the transaction
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.phFragment, new FormacionFrg());
                        ft.commit();
                    }
                });
                wscall2.execute();

            }
        });
        wscall.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_equipo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.tactica){
            Log.e("BTN TACTICA", "ASASASASAASASAS");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Táctica");
            builder.setItems(R.array.itemsTactica, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("TACTICA", ""+which);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(id == R.id.filtro){

        }


        return super.onOptionsItemSelected(item);
    }

}
