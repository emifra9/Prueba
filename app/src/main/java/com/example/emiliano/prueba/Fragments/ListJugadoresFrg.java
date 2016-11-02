package com.example.emiliano.prueba.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

public class ListJugadoresFrg extends Fragment {

    View rootView;
    OperacionesDB db;
    private RecyclerView mRecyclerView;
    private JugadoresAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.my_toolbar);
        MenuItem filter_menu = toolbar.getMenu().findItem(R.id.filtro);
        if(filter_menu != null) {
            filter_menu.setVisible(true);
        }
        MenuItem tactica_menu = toolbar.getMenu().findItem(R.id.tactica);
        if(tactica_menu != null) {
            tactica_menu.setVisible(false);
        }

        final Bundle bundle = this.getArguments();
        final String btnId = bundle.getString("Id");
        String btnPos = bundle.getString("Posicion");

        rootView = inflater.inflate(R.layout.list_jugadores_frg, parent, false);
        db = OperacionesDB.obtenerInstancia(getActivity());
        ArrayList<Jugador> arrayOfJugadores = new ArrayList<Jugador>();
        arrayOfJugadores = db.obtenerJugadoresxPos(btnPos);

        mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.ltwjugadores);
        // If the size of views will not change as the data changes.
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
// Setting the adapter.
        mAdapter = new JugadoresAdapter(getActivity(), arrayOfJugadores, btnId, btnPos);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;

    }
}
