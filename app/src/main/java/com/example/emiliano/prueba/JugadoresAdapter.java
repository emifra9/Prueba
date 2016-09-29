package com.example.emiliano.prueba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class JugadoresAdapter extends ArrayAdapter<Jugador> {
    private static class ViewHolder {
        TextView nombre;
        TextView puntos;
    }

    public JugadoresAdapter(Context context, ArrayList<Jugador> jugadores) {
        super(context, R.layout.ltwjugadoritem, jugadores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Jugador jugador = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ltwjugadoritem, parent, false);
            viewHolder.nombre = (TextView) convertView.findViewById(R.id.nombreJugador);
            viewHolder.puntos = (TextView) convertView.findViewById(R.id.puntaje);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.nombre.setText(jugador.nombre);
        viewHolder.puntos.setText(jugador.puntaje);
        // Return the completed view to render on screen
        return convertView;
    }
}