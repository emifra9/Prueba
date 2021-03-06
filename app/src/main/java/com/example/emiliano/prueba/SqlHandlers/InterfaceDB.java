package com.example.emiliano.prueba.SqlHandlers;

import java.util.Random;
import java.util.UUID;

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
        String FECHAMODIF = "fecha_modif";
        String ID_JUGADOR = "id_jugador";
        String NROPOS = "nropos";
        String ID_SISJUEGO = "id_sisjuego";
    }
    interface ColumnasSisJuego{
        String ID = "id";
        String SISTEMAJUEGO = "sistemajuego";
    }

    public static class Jugadores implements ColumnasJugador{
        public static String generarIdJugador() {
            return "JUG-" + UUID.randomUUID().toString();
        }
    }
    public static class Puntajes implements ColumnasPuntaje{}
    public static class Equipos implements ColumnasEquipo{}
    public static class SisJuegos implements ColumnasSisJuego{}

}
