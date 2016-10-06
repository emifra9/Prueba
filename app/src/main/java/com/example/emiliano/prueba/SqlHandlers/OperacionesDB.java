package com.example.emiliano.prueba.SqlHandlers;

import android.content.Context;

/**
 * Created by leo on 05/10/16.
 */
public class OperacionesDB {

    private static DBHelper baseDatos;
    private static OperacionesDB instancia = new OperacionesDB();

    private OperacionesDB(){}

    public static OperacionesDB obtenerInstancia(Context context){
        if(baseDatos == null){
            baseDatos = new DBHelper(context);
        }

        return instancia;
    }
}
