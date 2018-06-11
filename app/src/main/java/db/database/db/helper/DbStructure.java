package db.database.db.helper;
import android.provider.BaseColumns;

public final class DbStructure {

    public static final String dbName = "colivoitruage";
    public static final int DB_VERSION = 3;

    public static abstract class Ville implements BaseColumns {

        public static final String T_NOM = "ville";

        public static final String C_NOM = "nom";
        public static final String C_ADRESSE1 = "adresse1";
        public static final String C_ADRESSE2 = "adresse2";
        public static final String C_ADRESSE3 = "adresse3";
        public static final String C_ADRESSE4 = "adresse4";

        public static final String SQL_CREATE = "create table " + T_NOM + "("
               +C_NOM + " TEXT PRIMARY KEY ,"
               +C_ADRESSE1+ " TEXT ,"+C_ADRESSE2+ " TEXT ,"+C_ADRESSE3+ " TEXT ,"+C_ADRESSE4+")";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NOM;
    }
    public static abstract class TypeVehicule  implements BaseColumns {


        public static final String T_NOM = "TypeVehicule";
        public static final String C_ID = "id";
        public static final String C_NOMTYPE= "nomType";
        public static final String C_POIDSMAX= "poidsMax";
        public static final String C_VOLUMEMAX= "VOLUMEMAX";


        public static final String SQL_CREATE = "create table " + T_NOM + "("
                + C_ID + " INTEGER PRIMARY KEY ," + C_NOMTYPE
                + " TEXT"+C_POIDSMAX+" DOUBLE "+C_VOLUMEMAX+"DOUBLE)";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NOM;
    }


    public static abstract class Compte implements BaseColumns {

        public static final String T_NOM = "compte";
        public static final String C_RIB = "rib";
        public static final String C_SOLDE = "solde";
        public static final String C_ID_USER = "idUser";
        public static final String SQL_CREATE = "create table " + T_NOM + "("
                + C_RIB + " TEXT PRIMARY KEY ," + C_SOLDE
                + " DOUBLE"+ " )";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NOM;
    }
    public static abstract class Operation implements BaseColumns {

        public static final String T_NOM = "operation";
        public static final String C_ID = "id";
        public static final String C_MONTANT = "montant";
        public static final String C_TYPE = "type";
        public static final String C_RIB_COMPTE = "rib_compte";
        public static final String SQL_CREATE = "create table " + T_NOM + "("
                + C_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY ," + C_MONTANT
                + " DOUBLE, "+C_TYPE+" TEXT ,"+C_RIB_COMPTE+" TEXT,  FOREIGN KEY ( "
                + C_RIB_COMPTE + " ) REFERENCES " + Compte.T_NOM + "(" + Compte.C_RIB + ") )";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NOM;
    }


}
