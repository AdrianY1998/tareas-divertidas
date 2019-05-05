package bo.elite.tareasdivertidas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.singleton.MiembrosSingleton;

public class DatabaseHelper {
    private SQLiteDatabase mDatabase;
    private List<Miembro> miembros;
    private static Database instancia;
    public DatabaseHelper(Context context) {
        instancia = new Database(context);
        this.mDatabase = instancia.getWritableDatabase();
        this.miembros = new ArrayList<>();
    }
    public static Database getInstance() {
        return instancia;
    }

}
