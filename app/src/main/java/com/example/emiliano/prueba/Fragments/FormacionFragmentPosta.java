package com.example.emiliano.prueba.Fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.emiliano.prueba.Model.Equipo;
import com.example.emiliano.prueba.Model.Jugador;
import com.example.emiliano.prueba.R;
import com.example.emiliano.prueba.SqlHandlers.OperacionesDB;
import com.example.emiliano.prueba.Utilidades.Utilidades;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrador on 29/09/2016.
 */

public class FormacionFragmentPosta extends Fragment implements View.OnClickListener{
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    OperacionesDB db;
    Utilidades util;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.formacion_frg_1433, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        db = OperacionesDB.obtenerInstancia(getActivity());

        if (bundle != null) {
            Integer jugId = bundle.getInt("Jugador");
            String btnId = bundle.getString("btnId");

            Jugador jugador = db.obtenerJugador(jugId);

            Equipo equipo = new Equipo();
            equipo.setIdJugador(jugador.getId());
            equipo.setNroPos(Integer.parseInt(btnId));
            equipo.setSisJuegoId(1);

            db.nuevoEquipo(equipo);

        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ArrayList<Equipo> arrEquipos = db.obtenerEquipos(dateFormat.format(date));

        for (Equipo equip:arrEquipos) {
            int resTxtId = getResources().getIdentifier("txt"+equip.getNroPos(), "id", getActivity().getPackageName());
            TextView txtId = (TextView) view.findViewById(resTxtId);
            Jugador jug = db.obtenerJugador(equip.getIdJugador());
            txtId.setText(jug.getNombre());
        }

        for(int i=1; i<12; i++){
            int resBtnId = getResources().getIdentifier("btn"+i, "id", getActivity().getPackageName());
            ImageButton btn = (ImageButton)view.findViewById(resBtnId);
            btn.setOnClickListener(this);
        }

    }
    @Override
    public void onClick(View view) {
        String btnTag = view.getTag().toString();
        String btnId = btnTag.split(";")[0];
        String btnPosicion = btnTag.split(";")[1];
        Bundle bundle = new Bundle();
        bundle.putString("Id", btnId);
        bundle.putString("Posicion", btnPosicion);
        Fragment fragment = new LtwJugFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.phFragment, fragment);
        //   ft.addToBackStack(TAG);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }

}
