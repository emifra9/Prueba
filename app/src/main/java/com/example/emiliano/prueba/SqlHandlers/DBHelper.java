package com.example.emiliano.prueba.SqlHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import com.example.emiliano.prueba.Model.Jugador;

import java.util.ArrayList;
import java.util.List;

import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Jugadores;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Equipos;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Puntajes;

/**
 * Created by FERNANDA on 30/09/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Nombre del esquema de Base de Datos
    public static final String DATABASE_NAME = "DTManBD";
    //Version de la Base de Datos (Este parámetro es importante  )
    public static final int DATABASE_VERSION = 1;

    private final Context context;

    interface Tablas{
        String JUGADORES = "jugadores";
        String PUNTAJES = "puntajes";
        String EQUIPOS = "equipos";
    }

    interface Referencias{
        String ID_JUGADOR = String.format("REFERENCE %s(%s)", Tablas.JUGADORES, Jugadores.ID);
        String ID_PUNTAJE = String.format("REFERENCE %s(%s)", Tablas.PUNTAJES, Puntajes.ID);
        String ID_EQUIPO = String.format("REFERENCE %s(%s)", Tablas.EQUIPOS, Equipos.ID);
    }


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }
            else{
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas en la Base de datos
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "%s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL)", Tablas.JUGADORES, BaseColumns._ID,
                Jugadores.ID, Jugadores.NOMBRE, Jugadores.POSICION, Jugadores.PRECIO));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "%s INTEGER NOT NULL, %s INTEGER NOT NULL %s, %s INTEGER NOT NULL)", Tablas.PUNTAJES, BaseColumns._ID,
                Puntajes.ID, Puntajes.N_FECHA, Puntajes.ID_JUGADOR, Referencias.ID_JUGADOR, Puntajes.PUNTAJE));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "%s INTEGER NOT NULL, %s INTEGER NOT NULL %s)", Tablas.EQUIPOS, BaseColumns._ID,
                Equipos.ID, Equipos.N_FECHA, Equipos.ID_JUGADOR, Referencias.ID_JUGADOR));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + Tablas.JUGADORES);
        db.execSQL("DROP TABLE IF EXIST " + Tablas.PUNTAJES);
        db.execSQL("DROP TABLE IF EXIST " + Tablas.EQUIPOS);

        this.onCreate(db);
    }
}
