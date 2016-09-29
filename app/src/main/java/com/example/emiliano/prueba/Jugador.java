package com.example.emiliano.prueba;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Jugador {
    String nombre;
    String puntaje;
    public Jugador(String nombre, String puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public Jugador() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }



}
