package com.example.emiliano.prueba.SqlHandlers;

/**
 * Created by leo on 05/10/16.
 */
public class InterfaceDB {

    private InterfaceDB(){}

    interface ColumnasJugador{
        String ID = "id";
        String NOMBRE = "nombre";
        String POSICION = "posicion";
        String PRECIO = "precio";
    }

    interface ColumnasPuntaje{
        String ID = "id";
        String N_FECHA = "n_fecha";
        String ID_JUGADOR = "id_jugador";
        String PUNTAJE = "puntaje";
    }

    interface ColumnasEquipo{
        String ID = "id";
        String N_FECHA = "n_fecha";
        String ID_JUGADOR = "id_jugador";
    }

    public static class Jugadores implements ColumnasJugador{}
    public static class Puntajes implements ColumnasPuntaje{}
    public static class Equipos implements ColumnasEquipo{}

}
