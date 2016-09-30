package com.example.emiliano.prueba;

/**
 * Created by FERNANDA on 30/09/2016.
 */

public class FormacionDB {
    //Nombre del esquema de Base de Datos
    public static final String DATABASE_NAME = "DTManBD";
    //Version de la Base de Datos (Este parámetro es importante  )
    public static final int DATABASE_VERSION = 1;

    //Una clase estatica en la que se definen las propiedaes que determinan la tabla Notes
    // y sus 4 columnas
    public static class Formacion {
        //Nombre de la tabla
        public static final String TABLE_NAME = "formacion";
        //Nombre de las Columnas que contiene la tabla
        public static final String ID_COL = "id";
        public static final String POSICION_COL = "posicion";
        public static final String NROJUGADOR_COL = "nrojugador";
    }

    //Setencia SQL que permite crear la tabla Notes
    public static final String NOTES_TABLE_CREATE =
            "CREATE TABLE " + Formacion.TABLE_NAME + " (" +
                    Formacion.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Formacion.POSICION_COL + " INTEGER, " +
                    Formacion.NROJUGADOR_COL + " TEXT);";

    //Setencia SQL que permite eliminar la tabla Notes
    public static final String NOTES_TABLE_DROP = "DROP TABLE IF EXISTS " + Formacion.TABLE_NAME;

}
