package com.example.emiliano.prueba.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.emiliano.prueba.Adapters.JugadoresAdapter;
import com.example.emiliano.prueba.Model.Jugador;
import com.example.emiliano.prueba.R;
import com.example.emiliano.prueba.SqlHandlers.OperacionesDB;

import java.util.ArrayList;
/**
 * Created by Administrador on 29/09/2016.
 */

public class LtwJugFragment extends Fragment {
    OperacionesDB db;
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
        Bundle bundle = this.getArguments();
        String btnId = bundle.getString("Id");
        String btnPos = bundle.getString("Posicion");

        db = OperacionesDB.obtenerInstancia(getActivity());
        arrayOfJugadores = db.obtenerJugadoresxPos(btnPos);
// Create the adapter to convert the array to views
        final JugadoresAdapter adapter = new JugadoresAdapter(getActivity(), arrayOfJugadores);
// Attach the adapter to a ListView
        final ListView listView = (ListView) view.findViewById(R.id.ltwjugadores);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Jugador itemJug = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("Jugador", itemJug.getId());
                Fragment fragment = new FormacionFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.phFragment,fragment);
                ft.commit();


            }
        });
    }

}
