package com.rpl.kelompok1.gelo.helpers;

/**
 * Created by Lenovo on 08/04/2017.
 */

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class InsertAdmin {
    public static void insertAdminData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_ADMIN_EMAIL, "bosmul@bosmul.com");
        cv.put(DatabaseHelper.COLUMN_ADMIN_PASSWORD, "bosmul");
        list.add(cv);

        cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_ADMIN_EMAIL, "tes@tes.com");
        cv.put(DatabaseHelper.COLUMN_ADMIN_PASSWORD, "tes");
        list.add(cv);
        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (DatabaseHelper.TABLE_ADMIN,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(DatabaseHelper.TABLE_ADMIN, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }
    }
}
