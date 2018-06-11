package db.database.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbConnect extends SQLiteOpenHelper {



    public DbConnect(Context context) {
        super(context, DbStructure.dbName, null, DbStructure.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbStructure.TypeVehicule.SQL_CREATE);
        db.execSQL(DbStructure.Ville.SQL_CREATE);
        db.execSQL(DbStructure.Compte.SQL_CREATE);
        db.execSQL(DbStructure.Operation.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbStructure.Ville.SQL_DROP);
        db.execSQL(DbStructure.Compte.SQL_DROP);
        db.execSQL(DbStructure.Operation.SQL_DROP);
        onCreate(db);
    }


}
