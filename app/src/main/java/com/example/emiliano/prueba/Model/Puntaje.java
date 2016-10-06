package com.example.emiliano.prueba.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Puntaje {

    public Integer id;
    public String nFecha;
    public Integer idJugador;
    public Integer puntaje;


    public Puntaje(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.nFecha = object.getString("n_fecha");
            this.idJugador = object.getInt("id_jugador");
            this.puntaje = object.getInt("puntaje");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Puntaje() {

    }

    public static ArrayList<Puntaje> fromJson(JSONArray jsonObjects) {
        ArrayList<Puntaje> product = new ArrayList<Puntaje>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                product.add(new Puntaje(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnFecha() {
        return nFecha;
    }

    public void setnFecha(String nFecha) {
        this.nFecha = nFecha;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
}
