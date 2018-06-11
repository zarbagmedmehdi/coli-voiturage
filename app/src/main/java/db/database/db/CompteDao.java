package db.database.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import bean.Compte;

import db.database.db.helper.DbStructure;
import db.database.db.helper.AbstractDao;


public class CompteDao extends AbstractDao<Compte> {

    public CompteDao(Context context){
        super(context);
        columns= new String[]{DbStructure.Compte.C_RIB,DbStructure.Compte.C_SOLDE};
        tableName=DbStructure.Compte.T_NOM;
        idName=DbStructure.Compte.C_RIB;

    }

    @Override
    public long create(Compte compte) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.Compte.C_RIB, compte.getRib());
        contentValues.put(DbStructure.Compte.C_SOLDE, compte.getSolde());
        return db.insert(DbStructure.Compte.T_NOM,null,contentValues);
    }

    @Override
    public long edit(Compte compte) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.Compte.C_RIB, compte.getRib());
        contentValues.put(DbStructure.Compte.C_SOLDE, compte.getSolde());
        return db.update(DbStructure.Compte.T_NOM, contentValues, DbStructure.Compte.C_RIB + " = '" + compte.getRib()+"'", null) ;
    }

    @Override
    protected Compte transformeCursorToBean(Cursor cursor) {
        return new Compte(cursor.getString(0),cursor.getDouble(1));
    }








}