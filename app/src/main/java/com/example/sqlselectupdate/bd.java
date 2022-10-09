package com.example.sqlselectupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class bd extends SQLiteOpenHelper
{
    private final Context context;
    private static final String DataBase_Name = "AC.db";
    private static final int DataBase_Version = 1;

    private static final String Table_Name = "User";
    private static final String ID = "id";
    private static String KEY = "key1";
    private static final String VALUE = "value";
    SQLiteDatabase db = this.getReadableDatabase();

    public bd(@Nullable Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "Create Table " + Table_Name +
                        " (" + ID + " Integer PRIMARY KEY AUTOINCREMENT, " +
                        KEY + " Text, " +
                        VALUE + " Text )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exists " + Table_Name);
        onCreate(db);
    }

    public void Upgr(String key, String NewValue)
    {
        ContentValues cv = new ContentValues();

        cv.put(KEY, key);
        cv.put(VALUE,NewValue);

         db.update(Table_Name, cv, "key1 = ?", new String[]{key});

    }

    public Cursor search(String key)
    {
        Cursor cursor = db.rawQuery("Select value from " + Table_Name + " where " + KEY + " =?", new String[] {key});
        return cursor;
    }

    void add(String key, String value)
    {
        ContentValues cv =new ContentValues();

        cv.put(KEY, key);
        cv.put(VALUE, value);

        long result = db.insert(Table_Name,null,cv);
        if(result == -1)
        {
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean delete(String key)
    {
        Cursor cursor = db.rawQuery("Select * from " + Table_Name + " where " + KEY + " =?", new String[] {key});
        if (cursor.getCount() > 0){
            long result = db.delete(Table_Name, KEY + " =?",new String[]{key});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;

    }




}
