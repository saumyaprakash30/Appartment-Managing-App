package com.example.myapartment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "flatdata.db";
    public static final String TABLE_FLATS = "flatDetail";
    public static final  String COLUMN_FLATNUMBER = "_fnumber";
    public static final String COLUMN_OWNERNAME = "fowner_name";
    public static final String COLUMN_OWNERNUMBER = "fowner_number";
    public static final String COLUMN_RESNAME = "fres_name";
    public static final String COLUMN_RESNUMBER = "fres_number";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " +TABLE_FLATS +" ( " +
                COLUMN_FLATNUMBER + " TEXT PRIMARY KEY ," +
                COLUMN_OWNERNAME + "TEXT ," +
                COLUMN_OWNERNUMBER + " TEXT," +
                COLUMN_RESNAME + " TEXT," +
                COLUMN_RESNUMBER + " TEXT" +
                " );";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLATS);
        onCreate(db);

    }
    // adding to database

    public void addFlat(flat list)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FLATNUMBER,list.getFlatName());
        values.put(COLUMN_OWNERNAME,list.getFlatOwner());
        values.put(COLUMN_OWNERNUMBER,list.getFlatOwnerMobile());
        values.put(COLUMN_RESNAME,list.getFlatResident());
        values.put(COLUMN_RESNUMBER,list.getFlatResidentMobile());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FLATS,null,values);
        db.close();

    }


}
