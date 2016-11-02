package com.example.emiliano.prueba.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emiliano.prueba.EquipoActivity;
import com.example.emiliano.prueba.Fragments.FormacionFrg;
import com.example.emiliano.prueba.MainActivity;
import com.example.emiliano.prueba.Model.Equipo;
import com.example.emiliano.prueba.Model.Jugador;
import com.example.emiliano.prueba.R;
import com.example.emiliano.prueba.ViewHolders.ListJugadoresVH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrador on 27/09/2016.
 */

public class JugadoresAdapter extends RecyclerView.Adapter<ListJugadoresVH> {

    private List<Jugador> mData = Collections.emptyList();
    private String btnId;
    private String btnPos;

    private final Activity activity;
    private final EquipoActivity equipoActivity;

    public JugadoresAdapter(Activity context, List<Jugador> data, String btnId, String btnPos) {
        // Pass context or other static stuff that will be needed.
        this.mData = data;
        this.activity = context;
        this.btnId = btnId;
        this.btnPos = btnPos;
        this.equipoActivity = (EquipoActivity) context;

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListJugadoresVH onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.jugador_item, viewGroup, false);
        return new ListJugadoresVH(itemView);
    }

    @Override
    public void onBindViewHolder(final ListJugadoresVH viewHolder, final int position) {

        viewHolder.nombre.setText(mData.get(position).getNombre());
        viewHolder.precio.setText("$ " + mData.get(position).getPrecio());
        //viewHolder.equipo.setText(mData.get(position).getEquipo());

        viewHolder.itemJug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Jugador itemJug = mData.get(position);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("Jugador", itemJug.getId());
                bundle2.putString("btnId", btnId);
                Log.e("JUG ADAPTER btnId: ", ""+btnId);
                Log.e("JUG ADAPTER ID: ", ""+itemJug.getId());
                Fragment fragment = new FormacionFrg();
                fragment.setArguments(bundle2);
                FragmentManager fm = equipoActivity.getSupportFragmentManager();
                fm.popBackStack();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.phFragment,fragment);
                ft.commit();


            }
        });








    }

}