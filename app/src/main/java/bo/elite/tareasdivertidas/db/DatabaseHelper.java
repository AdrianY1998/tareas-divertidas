package bo.elite.tareasdivertidas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.Premio;
import bo.elite.tareasdivertidas.models.Tarea;
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

    public void addMiembro(Miembro miembro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", miembro.getNombre());
        contentValues.put("edad", miembro.getEdad());
        contentValues.put("email", miembro.getCorreoElectronico());
        this.mDatabase.insert("miembros", null, contentValues);
        this.mDatabase.close();
        //miembros.add(miembro);

    }

    public List<Miembro> getMiembros() {
        List<Miembro> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " nombre," +
                " edad," +
                " email," +
                "id" +
                " FROM miembros", null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                int edad = cursor.getInt(1);
                String email = cursor.getString(2);
                int id = cursor.getInt(3);

                Miembro miembro = new Miembro();
                miembro.setId(id);
                miembro.setNombre(nombre);
                miembro.setEdad(edad);
                miembro.setCorreoElectronico(email);

                //Adicionar a la lista
                results.add(miembro);
            } while (cursor.moveToNext());
        }
        return results;
    }

    public void insertTarea(Tarea tarea){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tareaN", tarea.getNameTarea());
        contentValues.put("puntaje", tarea.getPointTarea());
        contentValues.put("imagen", tarea.getImageTarea());

        this.mDatabase.insert("tareas",
                null,
                contentValues);
        this.mDatabase.close();
    }


    public List<Tarea> getTareas(){
        List<Tarea> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " id," +
                " tareaN," +
                " puntaje," +
                " imagen" +
                " FROM tareas", null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String tareaN = cursor.getString(1);
                int puntaje = cursor.getInt(2);
                int imagen = cursor.getInt(3);

                Tarea tarea = new Tarea();
                tarea.setId(id);
                tarea.setNameTarea(tareaN);
                tarea.setPointTarea(puntaje);
                tarea.setImageTarea(imagen);

                results.add(tarea);
            }while (cursor.moveToNext());
        }
        return results;
    }

    public void deleteT(int id) {
        if (id>9) {
            String[] params = new String[1];
            params[0] = String.valueOf(id);

            mDatabase.delete("tareas", "id=?", params);
        }
    }

    public void updateT(int id, String nombreT, int puntajeT){
        if(id>9){
            String[] params = new String[1];
            params[0] = String.valueOf(id);

            ContentValues cv = new ContentValues();
            cv.put("tareaN", nombreT);
            cv.put("puntaje", puntajeT);

            mDatabase.update("tareas", cv, "id=?", params);
        }
    }

    public void addPremio(Premio premio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", premio.getNombrePremio());
        contentValues.put("edad", premio.getPuntaje());
        this.mDatabase.insert("premios", null, contentValues);
        this.mDatabase.close();

    }

    public List<Premio> getPremios() {
        List<Premio> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " nombre," +
                " puntaje," +
                " imagen" +
                "id" +
                " FROM premios", null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                int puntaje = cursor.getInt(1);
                int imagen = cursor.getInt(2);
                int id = cursor.getInt(3);

                    Premio premio= new Premio();
                premio.setId(id);
                premio.setNombrePremio(nombre);
                premio.setPuntaje(puntaje);
                premio.setImage(imagen);

                results.add(premio);
            } while (cursor.moveToNext());
        }
        return results;
    }



    //public static DatabaseHelper getInstance(){
    //return instance;
    //}
}
