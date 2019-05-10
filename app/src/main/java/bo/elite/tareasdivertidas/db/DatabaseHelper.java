package bo.elite.tareasdivertidas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.Premio;
import bo.elite.tareasdivertidas.models.Tarea;

public class DatabaseHelper {
    private SQLiteDatabase mDatabase;
    //private List<Miembro> miembros = new ArrayList<>();
    private static Database instancia;
    //private static DatabaseHelper instance;

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

    public void modifyMiembro(int id, String nombre, int edad) {
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("edad", edad);
        mDatabase.update("miembros", contentValues, "id=?", params);
        mDatabase.close();
    }

    public void eliminarMiembro(int id) {
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        mDatabase.delete("miembros", "id=?", params);
    }

    public void añadirPremioMiembro(int idMiembro, int idPremio) {
        String[] params = new String[1];
        params[0] = String.valueOf(idMiembro);
        ContentValues contentValues = new ContentValues();
        contentValues.put("premioID", idPremio);
        int updated = mDatabase.update("miembros", contentValues, "id=?", params);
        Log.d("Registros actualizados", "" + updated);
        mDatabase.close();
    }

    public List<Miembro> getMiembros() {
        List<Miembro> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " id," +
                " nombre," +
                " edad," +
                " email," +
                " premioID" +
                " FROM miembros", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int edad = cursor.getInt(2);
                String email = cursor.getString(3);
                int premioId = cursor.getInt(4);
                Miembro miembro = new Miembro();
                miembro.setId(id);
                miembro.setNombre(nombre);
                miembro.setEdad(edad);
                miembro.setCorreoElectronico(email);
                miembro.setIDPremio(premioId);
                //Adicionar a la lista
                results.add(miembro);
            } while (cursor.moveToNext());
        }
        return results;
    }

    public Premio getPremio(int idPremio) {
        String[] params = new String[1];
        params[0] = String.valueOf(idPremio);
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " nombre," +
                " puntaje," +
                " image" +
                " FROM premios WHERE id=?", params);

        Premio premio = new Premio();
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(0);
            int puntaje = cursor.getInt(1);
            int image = cursor.getInt(2);
            premio.setId(idPremio);
            premio.setNombrePremio(nombre);
            premio.setPuntaje(puntaje);
            premio.setImage(image);
        }
        return premio;
    }

    public void insertTarea(Tarea tarea) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tareaN", tarea.getNameTarea());
        contentValues.put("puntaje", tarea.getPointTarea());
        contentValues.put("imagen", tarea.getImageTarea());

        this.mDatabase.insert("tareas",
                null,
                contentValues);
        this.mDatabase.close();
    }


    public List<Tarea> getTareas() {
        List<Tarea> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " id," +
                " tareaN," +
                " puntaje," +
                " imagen" +
                " FROM tareas", null);

        if (cursor.moveToFirst()) {
            do {
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
            } while (cursor.moveToNext());
        }
        return results;
    }

    public void deleteT(int id) {
        if (id > 9) {
            String[] params = new String[1];
            params[0] = String.valueOf(id);

            mDatabase.delete("tareas", "id=?", params);
        }
    }

    public void updateT(int id, String nombreT, int puntajeT) {
        if (id > 9) {
            String[] params = new String[1];
            params[0] = String.valueOf(id);

            ContentValues cv = new ContentValues();
            cv.put("tareaN", nombreT);
            cv.put("puntaje", puntajeT);

            mDatabase.update("tareas", cv, "id=?", params);
        }
    }

    public void insertTareaMiembro(Tarea tarea, Miembro miembro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("idTarea", tarea.getId());
        contentValues.put("idMiembro", miembro.getId());
        contentValues.put("nombre", miembro.getNombre());
        contentValues.put("edad", miembro.getEdad());
        contentValues.put("email", miembro.getCorreoElectronico());

        this.mDatabase.insert("relaciontm",
                null,
                contentValues);
        this.mDatabase.close();
    }

    public void addPremio(Premio premio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", premio.getNombrePremio());
        contentValues.put("puntaje", premio.getPuntaje());
        contentValues.put("image", premio.getImage());
        this.mDatabase.insert("premios", null, contentValues);
        this.mDatabase.close();

    }

    public List<Premio> getPremios() {
        List<Premio> results = new ArrayList<>();
        Cursor cursor = this.mDatabase.rawQuery("SELECT " +
                " nombre," +
                " puntaje," +
                " image," +
                "id" +
                " FROM premios", null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(0);
                int puntaje = cursor.getInt(1);
                int imagen = cursor.getInt(2);
                int id = cursor.getInt(3);

                Premio premio = new Premio();
                premio.setId(id);
                premio.setNombrePremio(nombre);
                premio.setPuntaje(puntaje);
                premio.setImage(imagen);

                results.add(premio);
            } while (cursor.moveToNext());
        }

        return results;
    }

    public void eliminarP(int id) {
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        mDatabase.delete("premios", "id=?", params);
    }

    public void editarP(int id, String nuevoN, int nuevoP) {
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        ContentValues cv = new ContentValues();
        cv.put("nombre", nuevoN);
        cv.put("puntaje", nuevoP);

        mDatabase.update("premios", cv, "id=?", params);
    }


    //public static DatabaseHelper getInstance(){
    //return instance;
    //}

}
