package db.database.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import bean.Operation;

import db.database.db.helper.AbstractDao;
import db.database.db.helper.DbStructure;


public class OperationDao extends AbstractDao<Operation> {


    @Override
    public long create(Operation operation) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.Operation.C_ID, operation.getId());
        contentValues.put(DbStructure.Operation.C_MONTANT, operation.getMontant());
        contentValues.put(DbStructure.Operation.C_TYPE, operation.getType());
        contentValues.put(DbStructure.Operation.C_RIB_COMPTE, operation.getCompte().getRib());
        return db.insert(DbStructure.Operation.T_NOM,null,contentValues);
    }

    @Override
    public long edit(Operation operation) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.Operation.C_ID, operation.getId());
        contentValues.put(DbStructure.Operation.C_MONTANT, operation.getMontant());
        contentValues.put(DbStructure.Operation.C_TYPE, operation.getType());
        contentValues.put(DbStructure.Operation.C_RIB_COMPTE, operation.getCompte().getRib());
        return db.update(DbStructure.Operation.T_NOM,contentValues,DbStructure.Operation.C_ID + " = " + operation.getId(), null) ;

    }

    @Override
    protected Operation transformeCursorToBean(Cursor cursor) {
        return new Operation(cursor.getInt(0),cursor.getDouble(1),cursor.getString(2),cursor.getString(3));
    }
    public List<Operation> findOperationByRibCOmpte(String ribCOmpte){
        return findAll(DbStructure.Operation.C_RIB_COMPTE+"='"+ribCOmpte+"'");
    }

    public OperationDao(Context context) {
        super(context);
        columns= new String[]{DbStructure.Operation.C_ID,DbStructure.Operation.C_MONTANT,
                DbStructure.Operation.C_TYPE,DbStructure.Operation.C_RIB_COMPTE};
        tableName=DbStructure.Operation.T_NOM;
        idName=DbStructure.Operation.C_ID;
    }


}