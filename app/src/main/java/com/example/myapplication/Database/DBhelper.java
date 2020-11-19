package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    public static final String Database_Name = "Usersinfo.db";
    public DBhelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_Create_Entries =
                "CREATE TABLE " + UsersMaster.Users.Table_Name + " (" +
                UsersMaster.Users._ID + "INTEGER PRIMARY KEY," +
                UsersMaster.Users.Column_Name_Username + "TEXT," +
                UsersMaster.Users.Column_Name_Password + "TEXT)";

        db.execSQL(SQL_Create_Entries);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addinfo(String Username, String Password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.Column_Name_Username,Username);
        values.put(UsersMaster.Users.Column_Name_Password,Password);

        long newRowid = db.insert(UsersMaster.Users.Table_Name, null, values);
        return newRowid;
    }

    public List readallinfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.Column_Name_Username,
                UsersMaster.Users.Column_Name_Password
        };

        String sortorder = UsersMaster.Users.Column_Name_Username + "DESC";

        Cursor cursor =db.query(UsersMaster.Users.Table_Name,projection,null,null,null,null,sortorder);

        List userNames = new ArrayList<>();
        List passWords = new ArrayList<>();

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_Username));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_Password));
            userNames.add(username);
            passWords.add(password);
        }
        cursor.close();
        //Log.i(null,"readAllinfo:"+ userNames);

        if (req == "user"){
          return userNames;
        }else if (req == "password"){
            return passWords;
        }else {
            return null;
        }
    }

    public void deleteinfo(String username){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.Column_Name_Username + "LIKE ?";

        String[] selectionArgs = { username};
        db.delete(UsersMaster.Users.Table_Name,selection,selectionArgs);
    }

    public int updateinfo(String username, String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.Column_Name_Password,password);

        String selection = UsersMaster.Users.Column_Name_Username + "LIKE ?";
        String[] selectionArgs = { username};

        int count =db.update(
                UsersMaster.Users.Table_Name,
                values,
                selection,
                selectionArgs
        );

        return count;
    }
}
