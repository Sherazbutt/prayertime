
        package com.example.butt.newbar;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        public class DataBaseHelper extends SQLiteOpenHelper {


            private DataBaseHelper ourHepler;
            private final Context ourContext;
            SQLiteDatabase ourDatabase;



    public static  final String DATABASE_NAME="Prayers_time.db";
    public static  final String DATABASE_TABLE="details";

    public static  final String COL_1="id";
    public static  final String COL_2="city";
    public static  final String COL_3="country";
    public static  final String COL_4="fajar";
    public static  final String COL_5="shruooq";
            public static  final String COL_6="zohar";
            public static  final String COL_7="asar";
            public static  final String COL_8="magrib";
            public static  final String COL_9="isha";






            public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 2);

                ourContext = context;
    }



            @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL("create table "+DATABASE_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,city TEXT,country VARCHAR,fajar VARCHAR,shruooq VARCHAR,zohar VARCHAR,asar VARCHAR,magrib VARCHAR,isha VARCHAR)");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        _db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
        onCreate(_db);

    }
    public boolean insertData(String city,String country,String fajar,String shurooq,String dhur,String asar,String magrib,String isha){



        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,city);
        contentValues.put(COL_3,country);
        contentValues.put(COL_4,fajar);
        contentValues.put(COL_5,shurooq);
        contentValues.put(COL_6,dhur);
        contentValues.put(COL_7,asar);
        contentValues.put(COL_8,magrib);
        contentValues.put(COL_9,isha);

        long result=db.insert(DATABASE_TABLE,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

            public boolean deletesomedata(){
                SQLiteDatabase db= this.getWritableDatabase();

                int doneDelete = 0;
                doneDelete = db.delete(DATABASE_TABLE, null , null);
                 Integer.toString(doneDelete);
                return doneDelete > 0;
            }

            public DataBaseHelper open() throws SQLException{
                ourHepler = new DataBaseHelper(ourContext);
                ourDatabase=  ourHepler.getWritableDatabase();
                return this;
            }
        }