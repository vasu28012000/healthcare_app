package com.example.practo_healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class User {



    public static final String KEY_FIRSTNAME= "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_FAD= "fad";
    public static final String KEY_PHONENUMBER = "phonenumber";
    private final String DATABASE_NAME = "user";
    private final String DATABASE_TABLE = "userT";
    private final int VERSION = 4;
    private final Context ourContext;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;
    public User(Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context)
        {
            super(context,DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" +
                    KEY_FIRSTNAME + " TEXT PRIMARY KEY, " + KEY_LASTNAME + " TEXT NOT NULL,"+
                    KEY_FAD + "  TEXT NOT NULL, "+ KEY_PHONENUMBER +  " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }
    }

    public User open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String fname, String lname,String fad,String phn)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_FIRSTNAME,fname);
        cv.put(KEY_LASTNAME,lname);
        cv.put(KEY_FAD,fad);
        cv.put(KEY_PHONENUMBER,phn);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData(String phno)
    {
        try {
            String[] columns = {KEY_FIRSTNAME, KEY_LASTNAME, KEY_FAD};
            String selection = KEY_PHONENUMBER + "=?";
            String[] s12 = {phno};
            Cursor c = ourDatabase.query(DATABASE_TABLE, columns, selection, s12, null, null, null);
            String s = " ";
            c.moveToFirst();
            s += c.getString(c.getColumnIndex(KEY_FAD)) + " " + c.getString(c.getColumnIndex(KEY_FIRSTNAME)) + " " + c.getString(c.getColumnIndex(KEY_LASTNAME));
            return s;
        }
        catch(CursorIndexOutOfBoundsException c) {
            return "ERROR";
        }

    }

}
