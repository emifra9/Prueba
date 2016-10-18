package com.example.emiliano.prueba.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Equipo {

    public Integer id;
    public Integer idJugador;
    public String fechaModif;
    public Integer sisJuegoId;
    public Integer nroPos;



    public Equipo(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.idJugador = object.getInt("id_jugador");
            this.fechaModif = object.getString("fechaModif");
            this.sisJuegoId = object.getInt("sisjuegoid");
            this.nroPos = object.getInt("nropos");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Equipo() {

    }

    public static ArrayList<Equipo> fromJson(JSONArray jsonObjects) {
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                equipos.add(new Equipo(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return equipos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fechaModif) { this.fechaModif = fechaModif;  }

    public Integer getSisJuegoId() {  return sisJuegoId; }

    public void setSisJuegoId(Integer sisJuegoId) { this.sisJuegoId = sisJuegoId;  }

    public Integer getNroPos() {  return nroPos; }

    public void setNroPos(Integer nroPos) { this.nroPos = nroPos;  }

}
