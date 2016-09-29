package com.example.emiliano.prueba;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrador on 29/09/2016.
 */

public class LtwJugFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.ltwjug_fragment, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
     //   Construct the data source
        ArrayList<Jugador> arrayOfJugadores = new ArrayList<Jugador>();
// Create the adapter to convert the array to views
        JugadoresAdapter adapter = new JugadoresAdapter(getActivity(), arrayOfJugadores);
// Attach the adapter to a ListView

        Jugador newJugador = new Jugador("Marco Ruben", "12");
        adapter.add(newJugador);
        Jugador newJugador1 = new Jugador("Paulo Ferrari", "6");
        adapter.add(newJugador1);
        Jugador newJugador2 = new Jugador("Teo Gutierrez", "8");
        adapter.add(newJugador2);
        //   Jugador newJugador2 = new Jugador("Ferrari", 5);
        //  adapter.add(newJugador2);
        ListView listView = (ListView) view.findViewById(R.id.ltwjugadores);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent i = new Intent(getActivity(), EquipoActivity.class);
                    startActivity(i);
                } else {
                    Intent intent = new Intent(getActivity(), EquipoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
