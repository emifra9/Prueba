package com.example.emiliano.prueba.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Jugador {

    public Integer id;
    public String nombre;
    public Integer posicion;
    public Integer precio;


    public Jugador(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.nombre = object.getString("nombre");
            this.posicion = object.getInt("posicion");
            this.precio = object.getInt("precio");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Jugador() {

    }

    public static ArrayList<Jugador> fromJson(JSONArray jsonObjects) {
        ArrayList<Jugador> product = new ArrayList<Jugador>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                product.add(new Jugador(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return product;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntaje() {
        return precio;
    }

    public void setPuntaje(Integer puntaje) {
        this.precio = puntaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer nrojug) {
        this.id = nrojug;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

}
