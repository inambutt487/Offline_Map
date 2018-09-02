package com.example.waseem.gpss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 10/10/2017.
 */

public class DtabaeHelper extends SQLiteOpenHelper {
    public static final String database_name="favlist.db";
    public static final String table_name="favitem";

    public DtabaeHelper(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table "+ table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Fav TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST"+ table_name);
        onCreate(sqLiteDatabase);

    }

    public void deleteRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name+ " WHERE "+"Fav"+"='"+value+"'");
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,null,null);
        db.close();
    }

    public boolean insertitem(String code)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();

        newcontent.put("Fav",code);

        long result=db.insert(table_name,null,newcontent);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor checkitems()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +table_name,null);
        return  res;
    }
    public boolean updateitems(String id,String update1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();
        newcontent.put("Fav",update1);

        db.update(table_name,newcontent, "ID = ? ",new String[] {id});
        return true;



    }

}
