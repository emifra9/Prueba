package com.example.emiliano.prueba;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 27/09/2016.
 */

public class Jugador implements Parcelable {
    String nombre;
    String puntaje;
    public Jugador(String nombre, String puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public Jugador() {

    }

    protected Jugador(Parcel in) {
        nombre = in.readString();
        puntaje = in.readString();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(puntaje);
    }
}
