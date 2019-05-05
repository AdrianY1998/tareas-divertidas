package bo.elite.tareasdivertidas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import bo.elite.tareasdivertidas.Constants;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context,
                Constants.DATABASE_NAME,
                null,
                Constants.DATABASE_VERSION);
    }
    //Primer vez

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE miembros (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " nombre VARCHAR NOT NULL," +
                " edad INTEGER," +
                " email VARCHAR NOT NULL)");
        Log.d("Database", "Created");
    }

        // Migracion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     }
}

