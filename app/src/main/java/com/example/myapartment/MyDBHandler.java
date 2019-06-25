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
    public static final String TABLE_FUEL ="fuelDetail";
    public static final String COLUMN_FUEL_DATE = "fuel_Date";
    public static final String COLUMN_FUEL = "fuel_l";
    public static final String TABLE_GEN_SER = "genSerDetail";
    public static final String COLUMN_GEN_SER_DATE = "gen_serDate";
    public static final String COLUMN_GEN_SER_NOTE = "gen_serNote";
    public static final String TABLE_LIFT_SER = "liftSerDetail";
    public static final String COLUMN_LIFT_SER_DATE = "lift_serDate";
    public static final  String COLUMN_LIFT_SER_NOTE = "lift_serNote";
    public static final String TABLE_ELE_BILL = "eleBill";
    public static final String COLUMN_ELE_BILL_DATE = "ele_billDate";
    public static final String COLUMN_ELE_BILL_NOTE =  "ele_billNote";
    public static final String TABLE_WATER_BILL = "waterBill";
    public static final String COLUMN_WATER_BILL_DATE = "water_billDate";
    public static final String COLUMN_WATER_BILL_NOTE =  "water_billNote";

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
        query = "CREATE TABLE "+TABLE_FUEL + " ( " +
                COLUMN_FUEL_DATE +" DATE PRIMARY KEY, "+
                COLUMN_FUEL + " INTEGER )";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_GEN_SER + " ( " +
                COLUMN_GEN_SER_DATE +" DATE PRIMARY KEY, "+
                COLUMN_GEN_SER_NOTE + " TEXT )";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_LIFT_SER + " ( " +
                COLUMN_LIFT_SER_DATE +" DATE PRIMARY KEY, "+
                COLUMN_LIFT_SER_NOTE + " TEXT )";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_ELE_BILL + " ( " +
                COLUMN_ELE_BILL_DATE +" DATE PRIMARY KEY, "+
                COLUMN_ELE_BILL_NOTE+ " TEXT )";
        db.execSQL(query);

        query = "CREATE TABLE "+TABLE_WATER_BILL + " ( " +
                COLUMN_WATER_BILL_DATE +" DATE PRIMARY KEY, "+
                COLUMN_WATER_BILL_NOTE+ " TEXT )";
        db.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLATS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUEL);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEN_SER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIFT_SER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELE_BILL);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_BILL);
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

    public void addFuel(String Date,int fuel)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FUEL_DATE,Date);
        cv.put(COLUMN_FUEL,fuel);
        db.insert(TABLE_FUEL,null,cv);
        db.close();


    }

    public String getFuelPrint()
    {
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_FUEL +" ORDER BY "+ COLUMN_FUEL_DATE +" DESC;";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();
        String data = "";

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_FUEL))!=null)
            {
                String f = String.valueOf(c.getString(c.getColumnIndex(COLUMN_FUEL)));
                data+= c.getString(c.getColumnIndex(COLUMN_FUEL_DATE)) + " : " + f +"L"+"\n"
                        +"--------------------------\n";
            }
            c.moveToNext();
        }

        return data;

    }

    public Boolean fuelDelete(String date)
    {
        SQLiteDatabase db = getWritableDatabase();

        try{
              db.execSQL("DELETE FROM " + TABLE_FUEL +" WHERE " + COLUMN_FUEL_DATE +"=\"" + date + "\";");
              db.close();
              return  true;

        }
        catch (Exception e)
        {
            return false;
        }



    }
    public void addGenSer(String Date,String note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GEN_SER_DATE,Date);
        cv.put(COLUMN_GEN_SER_NOTE,note);
        db.insert(TABLE_GEN_SER,null,cv);
        db.close();
    }
    public void delGenSer(String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GEN_SER +" WHERE " + COLUMN_GEN_SER_DATE +"=\"" + date + "\";");
        db.close();
    }

    public String getGenSerDetail()
    {
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_GEN_SER +" ORDER BY "+ COLUMN_GEN_SER_DATE +" desc ;";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();
        String data = "";

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_GEN_SER_DATE))!=null)
            {

                data+= c.getString(c.getColumnIndex(COLUMN_GEN_SER_DATE)) + " : " + c.getString(c.getColumnIndex(COLUMN_GEN_SER_NOTE)) +"L"+"\n"+
                                        "----------------------------\n";
            }
            c.moveToNext();
        }

        return data;
    }
    public void addLiftSer(String date,String note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
    cv.put(COLUMN_LIFT_SER_DATE,date);
    cv.put(COLUMN_LIFT_SER_NOTE,note);
    db.insert(TABLE_LIFT_SER,null,cv);
    db.close();


    }
    public void deleteLiftSer(String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LIFT_SER +" WHERE " + COLUMN_LIFT_SER_DATE +"=\"" + date + "\";");
        db.close();


    }
    public String getLiftSerDetail()
    {
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_LIFT_SER +" ORDER BY "+ COLUMN_LIFT_SER_DATE +" desc ;";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();
        String data = "";

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_LIFT_SER_DATE))!=null)
            {

                data+= c.getString(c.getColumnIndex(COLUMN_LIFT_SER_DATE)) + " : " + c.getString(c.getColumnIndex(COLUMN_LIFT_SER_NOTE)) +"\n"+
                        "----------------------------\n";
            }
            c.moveToNext();
        }

        return data;
    }

    public void addEleBill(String date,String note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ELE_BILL_DATE,date);
        cv.put(COLUMN_ELE_BILL_NOTE,note);
        db.insert(TABLE_ELE_BILL,null,cv);
        db.close();
    }
    public void deleteEleBill(String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ELE_BILL +" WHERE " + COLUMN_ELE_BILL_DATE +"=\"" + date + "\";");
        db.close();

    }

    public String getelebillDetail()
    {
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_ELE_BILL +" ORDER BY "+ COLUMN_ELE_BILL_DATE +" desc ;";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();
        String data = "";

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_ELE_BILL_DATE))!=null)
            {

                data+= c.getString(c.getColumnIndex(COLUMN_ELE_BILL_DATE)) + " : " + c.getString(c.getColumnIndex(COLUMN_ELE_BILL_NOTE)) +"\n"+
                        "----------------------------\n";
            }
            c.moveToNext();
        }

        return data;
    }

    public void addWaterBill(String date,String note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WATER_BILL_DATE,date);
        cv.put(COLUMN_WATER_BILL_NOTE,note);
        db.insert(TABLE_WATER_BILL,null,cv);
        db.close();

    }
    public void deleteWaterBill(String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WATER_BILL +" WHERE " + COLUMN_WATER_BILL_DATE +"=\"" + date + "\";");
        db.close();
    }
    public String getWaterBillDetail()
    {
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_WATER_BILL +" ORDER BY "+ COLUMN_WATER_BILL_DATE +" desc ;";

        Cursor c =  db.rawQuery(q,null);
        c.moveToFirst();
        String data = "";

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_WATER_BILL_DATE))!=null)
            {

                data+= c.getString(c.getColumnIndex(COLUMN_WATER_BILL_DATE)) + " : " + c.getString(c.getColumnIndex(COLUMN_WATER_BILL_NOTE)) +"\n"+
                        "----------------------------\n";
            }
            c.moveToNext();
        }

        return data;
    }
}
