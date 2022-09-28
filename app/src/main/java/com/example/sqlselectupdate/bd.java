package com.example.sqlselectupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.security.Key;

public class bd extends SQLiteOpenHelper
{
    private final Context context;
    private static final String DataBase_Name = "AC.db";
    private static final int DataBase_Version = 1;

    private static final String Table_Name = "User";
    private static final String ID = "id";
    private static String KEY = "key1";
    private static final String VALUE = "value";

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

    void search(String key)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select VALUE from " + Table_Name + " where " + KEY + " = ?", new String[] {key});
        if(cursor.getCount() == 0){
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Ваш аккаунт есть", Toast.LENGTH_SHORT).show();
        }

    }


}
