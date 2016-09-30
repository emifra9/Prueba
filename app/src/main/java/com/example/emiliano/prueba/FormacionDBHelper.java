package com.example.emiliano.prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FERNANDA on 30/09/2016.
 */

public class FormacionDBHelper extends SQLiteOpenHelper {

    //Definimos in Contructor para Instanciar el Database Helper
    public FormacionDBHelper(Context context) {
        super(context, FormacionDB.DATABASE_NAME, null, FormacionDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos las tablas en la Base de datos
        db.execSQL(FormacionDB.NOTES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //El método onUpgrade se ejecuta cada vez que recompilamos e instalamos la app con un
        //nuevo numero de version de base de datos (DATABASE_VERSION), de tal mamera que en este
        // método lo que hacemos es:
        // 1. Con esta sentencia borramos la tabla "jugadors"
        db.execSQL(FormacionDB.NOTES_TABLE_DROP);

        // 2. Con esta sentencia llamamos de nuevo al método onCreate para que se cree de nuevo
        //la tabla "jugadors" la cual seguramente al cambair de versión ha tenido modifciaciones
        // en cuanto a su estructura de columnas
        this.onCreate(db);
    }
 
 
    /*
    * OPERACIONES CRUD (Create, Read, Update, Delete)
    * A partir de aquí se definen los métodos para insertar, leer, actualizar y borrar registros de
    * la base de datos (BD)
    * */

    public void insertNote(Jugador jugador){
        //Con este método insertamos las notas nuevas que el usuario vaya creando

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos un obejto de tipo ContentValues para agregar los pares
        // de Claves de Columna y Valor
        ContentValues values = new ContentValues();
        values.put(FormacionDB.Formacion.NROJUGADOR_COL, jugador.getNrojug()); // nro jugador
        values.put(FormacionDB.Formacion.POSICION_COL, jugador.getPosicion()); // posicion

        // 3. Insertamos los datos en la tabla "jugadors"
        db.insert(FormacionDB.Formacion.TABLE_NAME, null, values);

        // 4. Cerramos la conexión comn la BD
        db.close();
    }

    //Obtener uan Nota dado un ID
    public Jugador getJugadorById(int id){
        // Declaramos un objeto Jugador para instanciarlo con el resultado del query
        Jugador aJugador = null;

        // 1. Obtenemos una reference de la BD con permisos de lectura
        SQLiteDatabase db = this.getReadableDatabase();

        //Definimos un array con los nombres de las columnas que deseamos sacar
        String[] COLUMNS = {FormacionDB.Formacion.ID_COL, FormacionDB.Formacion.NROJUGADOR_COL, FormacionDB.Formacion.POSICION_COL};


        // 2. Contruimos el query
        Cursor cursor =
                db.query(FormacionDB.Formacion.TABLE_NAME,  //Nomre de la tabla
                        COLUMNS, // b. Nombre de las Columnas
                        " id = ?", // c. Columnas de la clausula WHERE
                        new String[] { String.valueOf(id) }, // d. valores de las columnas de la clausula WHERE
                        null, // e. Clausula Group by
                        null, // f. Clausula having
                        null, // g. Clausula order by
                        null); // h. Limte de regsitros

        // 3. Si hemos obtenido algun resultado entonces sacamos el primero de ellos ya que se supone
        //que ha de existir un solo registro para un id
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. Contruimos el objeto Note
            aJugador.setNrojug(Integer.parseInt(cursor.getString(1)));
            // aJugador.setTitle(cursor.getString(1));
            //aJugador.setUrl(cursor.getString(2));
            //aJugador.setDescription(cursor.getString(3));

        }

        // 5. Devolvemos le objeto Note
        return aJugador;
    }


    // Obtener todas las notas
    public List getAllJugadores() {
        //Instanciamos un Array para llenarlo con todos los objetos Notes que saquemos de la BD
        List jugadores = new ArrayList();

        // 1. Aramos un String con el query a ejecutar
        String query = "SELECT  * FROM " + FormacionDB.Formacion.TABLE_NAME;

        // 2. Obtenemos una reference de la BD con permisos de escritura y ejecutamos el query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Iteramos entre cada uno de olos registros y agregarlos al array de Notas
        Jugador aJugador = null;
        if (cursor.moveToFirst()) {
            do {
                aJugador = new Jugador();
                aJugador.setNrojug(Integer.parseInt(cursor.getString(0)));
               // aJugador.setTitle(cursor.getString(1));
                //aJugador.setUrl(cursor.getString(2));
                //aJugador.setDescription(cursor.getString(3));

                // Add book to books
                jugadores.add(aJugador);
            } while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();

        // Devolvemos las notas encontradas o un array vacio en caso de que no se encuentre nada
        return jugadores;
    }


    //Actualizar los datos en una nota
    public int updateJugador(Jugador jugador) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos el objeto ContentValues con las claves "columna"/valor
        // que se desean actualizar
        ContentValues values = new ContentValues();
        values.put(FormacionDB.Formacion.POSICION_COL, jugador.getPosicion());
        values.put(FormacionDB.Formacion.NROJUGADOR_COL, jugador.getPosicion());

        // 3. Actualizamos el registro con el método update el cual devuelve la cantidad
        // de registros actualizados
        int i = db.update(FormacionDB.Formacion.TABLE_NAME, //table
                values, // column/value
                FormacionDB.Formacion.ID_COL+" = ?", // selections
                new String[] { String.valueOf(jugador.getNrojug()) }); //selection args

        // 4. Cerramos la conexión a la BD
        db.close();

        // Devolvemos la cantidad de registros actualizados
        return i;
    }

    // Borrar una Nota
    public void deleteJugador (Jugador jugador) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Borramos el registro
        db.delete(FormacionDB.Formacion.TABLE_NAME,
                FormacionDB.Formacion.ID_COL+" = ?",
                new String[] { String.valueOf(jugador.getNrojug()) });

        // 3. Cerramos la conexión a la Bd
        db.close();
    }
}
