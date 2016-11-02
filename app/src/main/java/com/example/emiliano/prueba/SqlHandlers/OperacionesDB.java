package com.example.emiliano.prueba.SqlHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.emiliano.prueba.Model.Jugador;
import com.example.emiliano.prueba.Model.Equipo;
import com.example.emiliano.prueba.Model.Puntaje;
import com.example.emiliano.prueba.Model.SisJuego;
import com.example.emiliano.prueba.SqlHandlers.DBHelper.Tablas;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Equipos;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Jugadores;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.Puntajes;
import com.example.emiliano.prueba.SqlHandlers.InterfaceDB.SisJuegos;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


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

    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }


    // [OPERACIONES_JUGADORES]
    public ArrayList<Jugador> obtenerJugadores() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
       ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        String sql = String.format("SELECT * FROM %s", Tablas.JUGADORES);

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            Jugador jugador = new Jugador();
            jugador.setId(cursor.getInt(cursor.getColumnIndex(Jugadores.ID)));
            jugador.setNombre(cursor.getString(cursor.getColumnIndex(Jugadores.NOMBRE)));
            jugador.setPosicion(cursor.getInt(cursor.getColumnIndex(Jugadores.POSICION)));
            jugador.setPrecio(cursor.getInt(cursor.getColumnIndex(Jugadores.PRECIO)));

            jugadores.add(jugador);

        } while (cursor.moveToNext());
        return jugadores;
    }
    public ArrayList<Jugador> obtenerJugadoresxPos(String posicion) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        String sql = String.format("SELECT * FROM %s where %s=%s", Tablas.JUGADORES,Jugadores.POSICION,posicion);

        Cursor cursor = db.rawQuery(sql, null);
        try {
            while (cursor.moveToNext()) {
                Jugador jugador = new Jugador();
                jugador.setId(cursor.getInt(cursor.getColumnIndex(Jugadores.ID)));
                jugador.setNombre(cursor.getString(cursor.getColumnIndex(Jugadores.NOMBRE)));
                jugador.setPosicion(cursor.getInt(cursor.getColumnIndex(Jugadores.POSICION)));
                jugador.setPrecio(cursor.getInt(cursor.getColumnIndex(Jugadores.PRECIO)));

                jugadores.add(jugador);
            }
        }finally {
            cursor.close();
        }
        return jugadores;
    }

    public Jugador obtenerJugador(int idJug) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
         String sql = String.format("SELECT * FROM %s where %s=%s", Tablas.JUGADORES, Jugadores.ID, idJug);

        Jugador jugador = new Jugador();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            jugador.setId(cursor.getInt(cursor.getColumnIndex(Jugadores.ID)));
            jugador.setNombre(cursor.getString(cursor.getColumnIndex(Jugadores.NOMBRE)));
            jugador.setPosicion(cursor.getInt(cursor.getColumnIndex(Jugadores.POSICION)));
            jugador.setPrecio(cursor.getInt(cursor.getColumnIndex(Jugadores.PRECIO)));

        } while (cursor.moveToNext());
        return jugador;
    }
    public String insertarJugador(Jugador jugador) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put(Jugadores.ID, jugador.getId());
        valores.put(Jugadores.NOMBRE, jugador.getNombre());
        valores.put(Jugadores.POSICION, jugador.getPosicion());
        valores.put(Jugadores.PRECIO, jugador.getPrecio());

        return db.insertOrThrow(Tablas.JUGADORES, null, valores) > 0 ? Integer.toString(jugador.getId()) : null;
    }
    public void EliminaDB() {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        baseDatos.EliminaDB(db);
    }
    public boolean actualizarJugador(Jugador jugador) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Jugadores.ID, jugador.getId());
        valores.put(Jugadores.NOMBRE, jugador.getNombre());
        valores.put(Jugadores.POSICION, jugador.getPosicion());
        valores.put(Jugadores.PRECIO, jugador.getPrecio());

        String whereClause = String.format("%s=?", Jugadores.ID);
        final String[] whereArgs = {Integer.toString(jugador.getId())};

        int resultado = db.update(Tablas.JUGADORES, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public void actualizarJugadores(ArrayList<Jugador> jugadores) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(Jugador jugador:jugadores){
                ContentValues valores = new ContentValues();
                valores.put(Jugadores.ID, jugador.getId());
                valores.put(Jugadores.NOMBRE, jugador.getNombre());
                valores.put(Jugadores.POSICION, jugador.getPosicion());
                valores.put(Jugadores.PRECIO, jugador.getPrecio());
            String whereClause = String.format("%s=?", Jugadores.ID);
            final String[] whereArgs = {Integer.toString(jugador.getId())};
            int resultado = 0;
            try {
                 resultado = db.update(Tablas.JUGADORES, valores, whereClause, whereArgs);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            if (resultado == 0) {
                db.insert(Tablas.JUGADORES, null, valores);
            }

        }
       // db.close();
    }
     // [/OPERACIONES_JUGADORES]
    // [OPERACIONES_SISJUEGO]

    public void actualizarSisJuegos(ArrayList<SisJuego> sistemasJuegos) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(SisJuego sisJuego:sistemasJuegos){
            ContentValues valores = new ContentValues();
            valores.put(SisJuegos.ID, sisJuego.getId());
            valores.put(SisJuegos.SISTEMAJUEGO, sisJuego.getSistemajuego());
            String whereClause = String.format("%s=?", SisJuegos.ID);
            final String[] whereArgs = {Integer.toString(sisJuego.getId())};
            int resultado = 0;
            try {
                resultado = db.update(Tablas.SISJUEGO, valores, whereClause, whereArgs);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            if (resultado == 0) {
                db.insert(Tablas.SISJUEGO, null, valores);
            }
        }
        // db.close();
    }


    // [/OPERACIONES_SISJUEGO]
    // [OPERACIONES_EQUIPO]
    public void nuevoEquipo(Equipo equipo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Equipos.ID_JUGADOR, equipo.getIdJugador());
        valores.put(Equipos.ID_SISJUEGO, equipo.getSisJuegoId());
        valores.put(Equipos.NROPOS, equipo.getNroPos());

         db.insert(Tablas.EQUIPOS, null, valores);
    }
    public Equipo obtenerEquipo(int nroPos) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s=%s  ORDER BY %s DESC LIMIT 1", Tablas.EQUIPOS, Equipos.NROPOS, nroPos, Equipos.ID);

        Equipo equipo = new Equipo();

        Cursor cursor = db.rawQuery(sql, null);
        try {
            while (cursor.moveToNext()) {
                equipo.setId(cursor.getInt(cursor.getColumnIndex(Equipos.ID)));
                equipo.setIdJugador(cursor.getInt(cursor.getColumnIndex(Equipos.ID_JUGADOR)));
                equipo.setSisJuegoId(cursor.getInt(cursor.getColumnIndex(Equipos.ID_SISJUEGO)));
                equipo.setNroPos(cursor.getInt(cursor.getColumnIndex(Equipos.NROPOS)));
            }
        }finally {
            cursor.close();
        }

        return equipo;
    }

    public ArrayList<Equipo> obtenerEquipos(String fecha) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
     //   String sql = String.format("SELECT * FROM %s WHERE %s <= Datetime('%s') ORDER BY %s DESC", Tablas.EQUIPOS, Equipos.FECHAMODIF, fecha, Equipos.ID);

        String sql2 = String.format(" select * from %s\n" +
                "        where %s = (select max(%s) from  %s as f \n" +
                "        where f.%s  <= Datetime('%s')and f.%s = %s.%s);  ",Tablas.EQUIPOS,Equipos.FECHAMODIF,Equipos.FECHAMODIF,
                Tablas.EQUIPOS,Equipos.FECHAMODIF,fecha,Equipos.NROPOS,Tablas.EQUIPOS,Equipos.NROPOS);
        Log.e("SQL", sql2);


        ArrayList<Equipo> arrEquipos = new ArrayList<Equipo>();
        Cursor cursor = db.rawQuery(sql2, null);
        Log.e("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
        try {
            while (cursor.moveToNext()) {
                Equipo equipo = new Equipo();
                equipo.setId(cursor.getInt(cursor.getColumnIndex(Equipos.ID)));
                equipo.setIdJugador(cursor.getInt(cursor.getColumnIndex(Equipos.ID_JUGADOR)));
                equipo.setSisJuegoId(cursor.getInt(cursor.getColumnIndex(Equipos.ID_SISJUEGO)));
                equipo.setNroPos(cursor.getInt(cursor.getColumnIndex(Equipos.NROPOS)));
                arrEquipos.add(equipo);
            }
        }finally {
            cursor.close();
        }

        return arrEquipos;
    }

    // [/OPERACIONES_EQUIPO]



}
