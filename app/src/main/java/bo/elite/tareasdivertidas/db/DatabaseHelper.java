package bo.elite.tareasdivertidas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.singleton.MiembrosSingleton;

public class DatabaseHelper {
    private SQLiteDatabase mDatabase;
    //private List<Miembro> miembros = new ArrayList<>();
    private static Database instancia;
    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        instancia = new Database(context);
        this.mDatabase = instancia.getWritableDatabase();
        //this.miembros = new ArrayList<>();
        //instance = new DatabaseHelper(context);
    }

    public void addMiembro(Miembro miembro){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", miembro.getNombre());
        contentValues.put("edad", miembro.getEdad());
        contentValues.put("email", miembro.getCorreoElectronico());
        this.mDatabase.insert("miembros",null, contentValues);
        this.mDatabase.close();
        //miembros.add(miembro);

    }

    public List<Miembro> getMiembros() {
        List<Miembro> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " nombre," +
                " edad," +
                " email" +
                " FROM miembros", null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                int edad = cursor.getInt(1);
                String email = cursor.getString(2);

                Miembro miembro = new Miembro();
                miembro.setNombre(nombre);
                miembro.setEdad(edad);
                miembro.setCorreoElectronico(email);

                //Adicionar a la lista
                results.add(miembro);
            } while (cursor.moveToNext());
        }
        return results;
        }


    //public static DatabaseHelper getInstance(){
        //return instance;
    //}
}
