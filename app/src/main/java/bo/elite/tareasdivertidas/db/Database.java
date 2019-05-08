package bo.elite.tareasdivertidas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.R;

public class Database extends SQLiteOpenHelper {

    private Context context;

    public Database(Context context) {
        super(context,
                Constants.DATABASE_NAME,
                null,
                Constants.DATABASE_VERSION);
        this.context = context;
    }
    //Primer vez

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE miembros (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " nombre VARCHAR NOT NULL," +
                " edad INTEGER," +
                " email VARCHAR NOT NULL)");
        Log.d("Database", "Created");

        db.execSQL("CREATE TABLE premios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " nombre VARCHAR NOT NULL," +
                " puntaje INTEGER NOT NULL," +
                " image INTEGER NOT NULL)");
        Log.d("Database", "Created");

        db.execSQL("CREATE TABLE tareas (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "tareaN VARCHAR NOT NULL," +
                "puntaje INTEGER NOT NULL," +
                "imagen INTEGER NOT NULL)");

        //db.execSQL("INSERT INTO tareas (nombre) VALUES ('Dar comida a mascota'), (puntaje) , (250), (imagen) () ");

        ContentValues values = new ContentValues();
        values.put("tareaN", "Dar comida a mascota");
        values.put("puntaje", 250);
        values.put("imagen", R.drawable.dar_momida_mascota);
        db.insert("tareas", null, values);
        // para agregar mas filas:
        values.clear();

        values.put("tareaN", "Estudiar");
        values.put("puntaje", 320);
        values.put("imagen", R.drawable.estudiar);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Lavar ropa");
        values.put("puntaje", 140);
        values.put("imagen", R.drawable.lavar_ropa);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Limpiar casa");
        values.put("puntaje", 400);
        values.put("imagen", R.drawable.limpiar_casa);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Ordenar cuarto");
        values.put("puntaje", 150);
        values.put("imagen", R.drawable.ordenar_cuarto);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Pasear perro");
        values.put("puntaje", 220);
        values.put("imagen", R.drawable.pasear_perro);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Poner mesa");
        values.put("puntaje", 100);
        values.put("imagen", R.drawable.poner_mesa);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Regar plantas");
        values.put("puntaje", 450);
        values.put("imagen", R.drawable.regar_plantas);
        db.insert("tareas", null, values);
        values.clear();

        values.put("tareaN", "Sacar basura");
        values.put("puntaje", 380);
        values.put("imagen", R.drawable.sacar_basura);
        db.insert("tareas", null, values);
        values.clear();

        values.put("nombre", "Ir al cine");
        values.put("puntaje", 1000);
        values.put("image", R.drawable.ir_al_cine);
        db.insert("premios", null, values);
        values.clear();

        values.put("nombre", "Nuevo libro");
        values.put("puntaje", 1000);
        values.put("image", R.drawable.nuevo_libro);
        db.insert("premios", null, values);
        values.clear();

        values.put("nombre", "Regalo");
        values.put("puntaje", 1000);
        values.put("image", R.drawable.regalo);
        db.insert("premios", null, values);
        values.clear();

        values.put("nombre", "Nuevo videojuegos");
        values.put("puntaje", 1000);
        values.put("image", R.drawable.videojuegos);
        db.insert("premios", null, values);
        values.clear();

    }

    // Migracion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

