package com.example.emiliano.prueba.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class SisJuego {

    public Integer id;
    public String sistemajuego;


    public SisJuego(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.sistemajuego = object.getString("nombre");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public SisJuego() {

    }

    public static ArrayList<SisJuego> fromJson(JSONArray jsonObjects) {
        ArrayList<SisJuego> sisJuegos = new ArrayList<SisJuego>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                sisJuegos.add(new SisJuego(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sisJuegos;
    }


    public String getSistemajuego() {
        return sistemajuego;
    }
    public void setSistemajuego(String sistemajuego) {
        this.sistemajuego = sistemajuego;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer nrojug) {
        this.id = nrojug;
    }



}
