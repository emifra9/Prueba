package com.example.emiliano.prueba.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Jugador {

    public Integer id;
    public String nombre;
    public Integer posicion;
    public Integer precio;
   public String fechamodif;
    public Integer activo;


    public Jugador(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.nombre = object.getString("nombre");
            this.posicion = object.getInt("posicion");
            this.precio = object.getInt("precio");
            this.fechamodif = object.getString("fecha_mod");
            this.activo = object.getInt("activo");

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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getFechamodif() {
        return fechamodif;
    }

    public void setFechamodif(String fechamodif) {
        this.fechamodif = fechamodif;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }



}
