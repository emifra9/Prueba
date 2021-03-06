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

public class FormacionFragment extends Fragment implements View.OnClickListener{
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    OperacionesDB db;
    private ImageButton buttonArquero ;
    private TextView textArquero;
    private TextView textDef1;
    private TextView textMed1;
    private TextView textDel1;
    private TextView textDel2;
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
    private final String TAG = "equipo";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.formacion_fragment, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Integer jugId = bundle.getInt("Jugador");
            db = OperacionesDB.obtenerInstancia(getActivity());
            Jugador jugador = db.obtenerJugador(jugId);


            if (jugador.getPosicion() == 1){
                textArquero = (TextView) view.findViewById(R.id.txtArq);
                textArquero.setText(jugador.getNombre());
            }
            if (jugador.getPosicion() == 2){
                textDef1 = (TextView) view.findViewById(R.id.txtDef1);
                textDef1.setText(jugador.getNombre());
            }
            if (jugador.getPosicion() == 3){
                textMed1 = (TextView) view.findViewById(R.id.txtMed1);
                textMed1.setText(jugador.getNombre());
            }
            if (jugador.getPosicion() == 4){
                textDel1 = (TextView) view.findViewById(R.id.txtDel1);
                textDel1.setText(jugador.getNombre());
            }


        }

        buttonArquero = (ImageButton)view.findViewById(R.id.btnArq);
        buttonArquero.setOnClickListener(this);
        buttonDef1 = (ImageButton)view.findViewById(R.id.btnDef1);
        buttonDef1.setOnClickListener(this);
        buttonDef2 = (ImageButton)view.findViewById(R.id.btnDef2);
        buttonDef2.setOnClickListener(this);
        buttonDef3 = (ImageButton)view.findViewById(R.id.btnDef3);
        buttonDef3.setOnClickListener(this);
        buttonDef4 = (ImageButton)view.findViewById(R.id.btnDef4);
        buttonDef4.setOnClickListener(this);
        buttonMed1 = (ImageButton)view.findViewById(R.id.btnMed1);
        buttonMed1.setOnClickListener(this);
        buttonMed2 = (ImageButton)view.findViewById(R.id.btnMed2);
        buttonMed2.setOnClickListener(this);
        buttonMed3 = (ImageButton)view.findViewById(R.id.btnMed3);
        buttonMed3.setOnClickListener(this);
        buttonDel1 = (ImageButton)view.findViewById(R.id.btnDel1);
        buttonDel1.setOnClickListener(this);
        buttonDel2 = (ImageButton)view.findViewById(R.id.btnDel2);
        buttonDel2.setOnClickListener(this);
        buttonDel3 = (ImageButton)view.findViewById(R.id.btnDel3);
        buttonDel3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        Log.e("IDBTN", ""+view.getId());
        Log.e("TAGBTN", ""+view.getTag());
        ft.replace(R.id.phFragment, new LtwJugFragment());
     //   ft.addToBackStack(TAG);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }

}
