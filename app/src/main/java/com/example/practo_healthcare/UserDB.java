package com.example.practo_healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class UserDB {


    public static final String KEY_NAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DOCNAME="docname";
    public static final String KEY_DOCTYPE="doctype";
    public static final String KEY_AVAIL="avail";
    public static final String KEY_CLINIC="clinic";
    public static final String KEY_CONTACT="contact";
    public static final String KEY_FEEDBACK="feedback";
    public static final String KEY_MED="med";
    private final String FEEDBACK_TABLE="feedbacktable";
    private final String PHARMACY="pharmacy";
    private final String DOCTABLE="doctable";
       private final String DATABASE_NAME = "userDb";
    private final String DATABASE_TABLE = "userTable";
    private final int VERSION = 4;

    private final Context ourContext;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public UserDB(Context context)
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
                        KEY_NAME + " String NOT NULL UNIQUE, " + KEY_PASSWORD + " String NOT NULL);");
            db.execSQL("CREATE TABLE "+FEEDBACK_TABLE+ "(" +KEY_NAME +" String, " +KEY_FEEDBACK+ " String);");
            db.execSQL("CREATE TABLE "+PHARMACY+"(" +KEY_MED+" String UNIQUE, "+KEY_AVAIL+ " Integer);");
            db.execSQL("CREATE TABLE " + DOCTABLE+ "(" +
                    KEY_DOCNAME + " String NOT NULL UNIQUE, " + KEY_DOCTYPE + " String NOT NULL, "+KEY_AVAIL +" String, "+KEY_CLINIC+" String, "+KEY_CONTACT+" String NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DOCTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + FEEDBACK_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PHARMACY);
            onCreate(db);
        }
    }
    public UserDB open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }
    public void addmeds(String name,int stock)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_MED,name);
        cv.put(KEY_AVAIL,stock);
        ourDatabase.insert(PHARMACY,null,cv);
    }
    public void change(String meds,int value)
    {
       String select=KEY_MED+"=?";
       String []selectargs={meds};
       ContentValues contentValues=new ContentValues();
       contentValues.put(KEY_AVAIL,value);
        ourDatabase.update(PHARMACY,contentValues,select,selectargs);
    }
    public boolean checkstock(String meds,int need)
    {
        String []col={KEY_AVAIL};
        String sel=KEY_MED+"=?";
        String [] selargs={meds};
        try {
            Cursor c = ourDatabase.query(PHARMACY, col, sel, selargs, null, null, null);
            c.moveToFirst();
            if (c.getInt(c.getColumnIndex(KEY_AVAIL)) >= need) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(KEY_AVAIL, c.getInt(c.getColumnIndex(KEY_AVAIL)) - need);
                ourDatabase.update(PHARMACY, contentValues, null, null);
                return true;
            }
            return false;
        }
        catch (CursorIndexOutOfBoundsException c)
        {
            return false;
        }

    }

    public long createEntry(String username, String password)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, username);
        cv.put(KEY_PASSWORD, password);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }
    public long docinfo(String name, String type, String contact, String clinic, String available)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_DOCNAME,name);
        contentValues.put(KEY_DOCTYPE,type);
        contentValues.put(KEY_AVAIL,available);
        contentValues.put(KEY_CLINIC,clinic);
        contentValues.put(KEY_CONTACT,contact);
        return ourDatabase.insert(DOCTABLE,null,contentValues);
    }
    public boolean docavail(String name)
    {
        try {
            String[] columns = {KEY_AVAIL};
            String selection = KEY_DOCNAME + " =?";
            String[] selectionArgs = {name};
            Cursor c = ourDatabase.query(DOCTABLE, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            String s = c.getString(c.getColumnIndex(KEY_AVAIL));

            return s.equals("AVAILABLE");
        }
        catch (CursorIndexOutOfBoundsException c)
        {
            return false;
        }


    }
    public ArrayList<String> docdata(String type)
    {
        ArrayList<String> s = new ArrayList<String>();
        try {

            String[] columns = {KEY_DOCTYPE, KEY_DOCNAME, KEY_CONTACT, KEY_CLINIC, KEY_AVAIL};
            String selection = KEY_DOCTYPE + " =?";
            String[] selectionArgs = {type};
            Cursor c = ourDatabase.query(DOCTABLE, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                s.add(c.getString(c.getColumnIndex(KEY_DOCNAME)));
                c.moveToNext();
            }
            c.close();
            return s;
        }
        catch (CursorIndexOutOfBoundsException c)
        {
            return s;
        }

    }
    public void changeavail(String name)
    {
        String []col={KEY_AVAIL};
        String sel=KEY_DOCNAME +"=?";
        String []s={name};
        Cursor c=ourDatabase.query(DOCTABLE,col,sel,s,null,null,null);
        c.moveToFirst();
        String s22=c.getString(c.getColumnIndex(KEY_AVAIL));
        if(s22.equals("AVAILABLE"))
            s22="NOT AVAILABLE";
        else
            s22="AVAILABLE";
        ContentValues cv=new ContentValues();
        cv.put(KEY_AVAIL,s22);
        ourDatabase.update(DOCTABLE,cv,null,null);
    }
    public String retcontact(String name)
    {
        try {
            String[] col = {KEY_CONTACT};
            String sel = KEY_DOCNAME + "=?";
            String[] selargs = {name};
            Cursor c = ourDatabase.query(DOCTABLE, col, sel, selargs, null, null, null);
            c.moveToFirst();
            return c.getString(c.getColumnIndex(KEY_CONTACT));
        }
        catch (CursorIndexOutOfBoundsException c)
        {
            return "0000000000";
        }
    }
    public String retclinic(String name)
    {
        try {
            String[] col = {KEY_CLINIC};
            String sel = KEY_DOCNAME + "=?";
            String[] selargs = {name};
            Cursor c = ourDatabase.query(DOCTABLE, col, sel, selargs, null, null, null);
            c.moveToFirst();
            return c.getString(c.getColumnIndex(KEY_CLINIC));
        }
        catch (CursorIndexOutOfBoundsException c)
        {
            return "XXXXXXXXXX";
        }
    }
    public int getData(String username, String password)
    {
           try {
               String[] columns = {KEY_NAME, KEY_PASSWORD};
               int ctr = 0;
               String selection = KEY_NAME + " =?";
               String[] selectionArgs = {username};
               Cursor c = ourDatabase.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
               c.moveToFirst();
               if (c.getString(c.getColumnIndex(KEY_PASSWORD)).equals(password))
                   ctr++;
               c.close();
               return ctr;
           }
           catch (CursorIndexOutOfBoundsException c)
           {
               return 0;
           }
    }
    public void feed(String user,String feedback)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,user);
        contentValues.put(KEY_FEEDBACK,feedback);
        ourDatabase.insert(FEEDBACK_TABLE,null,contentValues);
    }

}
