package com.example.myapartment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

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
                COLUMN_FLATNUMBER + " TEXT PRIMARY KEY ,    " +
                COLUMN_OWNERNAME + " TEXT ," +
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

    //deleting in database

    public void deleteFlat(String flatNumber)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_FLATS +" WHERE " + COLUMN_FLATNUMBER +"=\"" + flatNumber + "\";");
    }


    public ArrayList<flat> getFlats()
    {
        ArrayList<flat> allFlats = new ArrayList<flat>();

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_FLATS+ " ORDER BY " +  COLUMN_FLATNUMBER + " ASC";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_FLATNUMBER))!= null)
            {
                String fnum,oName,rName,oNum,rNum;

                fnum = c.getString(c.getColumnIndex(COLUMN_FLATNUMBER));
                oName = c.getString(c.getColumnIndex(COLUMN_OWNERNAME));
                oNum = c.getString(c.getColumnIndex(COLUMN_OWNERNUMBER));
                rName = c.getString(c.getColumnIndex(COLUMN_RESNAME));
                rNum = c.getString(c.getColumnIndex(COLUMN_RESNUMBER));
                flat f = new flat(fnum,oName,rName,oNum,rNum);
                allFlats.add(f);
            }
            c.moveToNext();
        }

        db.close();
        return allFlats;

    }




    public flat getFlatDetail(String fname)
    {
        flat f;
        String fnum=fname,oName="b",rName="c",oNum="d",rNum="e";



        SQLiteDatabase db =getWritableDatabase();
        String q= "SELECT * FROM " + TABLE_FLATS + " WHERE " + COLUMN_FLATNUMBER + " =\"" + fname + "\";";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_FLATNUMBER))!=null)
            {

                fnum = c.getString(c.getColumnIndex(COLUMN_FLATNUMBER));
                oName = c.getString(c.getColumnIndex(COLUMN_OWNERNAME));
                oNum = c.getString(c.getColumnIndex(COLUMN_OWNERNUMBER));
                rName = c.getString(c.getColumnIndex(COLUMN_RESNAME));
                rNum = c.getString(c.getColumnIndex(COLUMN_RESNUMBER));


            }
            c.moveToNext();
        }

        f = new flat(fnum,oName,rName,oNum,rNum);
        return f;
    }
}
