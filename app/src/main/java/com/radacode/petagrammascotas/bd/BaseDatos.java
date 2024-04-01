package com.radacode.petagrammascotas.bd;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.radacode.petagrammascotas.R;
import com.radacode.petagrammascotas.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO      + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE    + " TEXT" +
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "("+ ConstantesBaseDatos.TABLE_MASCOTA_ID +")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setFotoMascota(registros.getInt(1));
            mascotaActual.setNombreMascota(registros.getString(2));

            /*String queryLikes = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +") as likes" +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setNumeroLikes(registrosLikes.getInt(0));
            }else {
                mascotaActual.setNumeroLikes(0);
            }*/

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();

        // Evita estar agregando registros cada que
        // se ejecuta o cambia de pestaña
        String query = "SELECT COUNT(*) FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);
        cursor.close();
        if(count <= 4){
            db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
            db.close();
        }
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public ArrayList<Mascota> obtenerUltimasCincoMascotas() {
        ArrayList<Mascota> ultimasCincoMascotas = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        // Consulta para obtener los últimos cinco registros de likes
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " DESC LIMIT 5";
        Cursor cursor = db.rawQuery(query, null);

        // Recorrer el cursor para obtener los datos de los últimos cinco registros de likes
        while (cursor.moveToNext()) {
            // Obtener los datos del registro de likes
            int idMascota = cursor.getInt(0);
            int numeroLikes = cursor.getInt(1);

            // Consulta para obtener los datos de la mascota correspondiente
            String queryMascota = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_ID + " = " + idMascota;
            Cursor cursorMascota = db.rawQuery(queryMascota, null);

            // Verificar si se encontró la mascota
            if (cursorMascota.moveToNext()) {
                // Obtener los datos de la mascota
                int foto = cursorMascota.getInt(1);
                String nombre = cursorMascota.getString(2);

                // Crear un objeto Mascota y agregarlo a la lista
                Mascota mascota = new Mascota();
                mascota.setFotoMascota(foto);
                mascota.setNombreMascota(nombre);
                mascota.setNumeroLikes(numeroLikes);

                ultimasCincoMascotas.add(mascota);
            }

            // Cerrar el cursor de la mascota correspondiente
            cursorMascota.close();
        }

        // Cerrar el cursor de likes y la base de datos
        cursor.close();
        db.close();

        /*ArrayList<Mascota> example = new ArrayList<>();
        example.add(new Mascota(R.drawable.p3, "Lalo",12));
        example.add(new Mascota(R.drawable.p3, "Lalo",12));
        example.add(new Mascota(R.drawable.p3, "Lalo",12));
        example.add(new Mascota(R.drawable.p3, "Lalo",12));
        example.add(new Mascota(R.drawable.p3, "Lalo",12));*/

        // Devolver la lista de las últimas cinco mascotas
        return ultimasCincoMascotas;
    }


    public int obtenerLikesMascota(Mascota mascota){
        SQLiteDatabase db = this.getWritableDatabase();

        int likes = 0;

        String query = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();


        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }
}
