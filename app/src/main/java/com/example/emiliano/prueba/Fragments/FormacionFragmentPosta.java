package com.example.emiliano.prueba.Fragments;


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

/**
 * Created by Administrador on 29/09/2016.
 */

public class FormacionFragmentPosta extends Fragment implements View.OnClickListener{
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    OperacionesDB db;

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
        if (bundle != null) {
            Integer jugId = bundle.getInt("Jugador");
            Integer btnId = bundle.getInt("btnId");

            db = OperacionesDB.obtenerInstancia(getActivity());
            Jugador jugador = db.obtenerJugador(jugId);

            Equipo equipo = new Equipo();
            equipo.setIdJugador(jugador.getId());
            equipo.setNroPos(btnId);
            equipo.setSisJuegoId(1);

            db.actualizarEquipo(equipo);

            if (jugador.getPosicion() == 1){
             //   btnId = (TextView) view.findViewById(R.id.txtArq);
                //textArquero.setText(jugador.getNombre());
            }



        }
        ImageButton btn1 = (ImageButton)view.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        ImageButton btn4 = (ImageButton)view.findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        ImageButton btn5 = (ImageButton)view.findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        ImageButton btn6 = (ImageButton)view.findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        ImageButton btn7 = (ImageButton)view.findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        ImageButton btn8 = (ImageButton)view.findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        ImageButton btn9 = (ImageButton)view.findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        ImageButton btn10 = (ImageButton)view.findViewById(R.id.btn10);
        btn10.setOnClickListener(this);
        ImageButton btn11 = (ImageButton)view.findViewById(R.id.btn11);
        btn11.setOnClickListener(this);

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
