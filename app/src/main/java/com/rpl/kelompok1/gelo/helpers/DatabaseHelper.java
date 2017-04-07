package com.rpl.kelompok1.gelo.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rpl.kelompok1.gelo.models.Laundry;
import com.rpl.kelompok1.gelo.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 06/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Gelo.db";

    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_ALAMAT = "user_alamat";
    private static final String COLUMN_USER_TELEPON = "user_telepon";

    // Admin table name
    public static final String TABLE_ADMIN = "admin";
    // Admin Table Columns names
    public static final String COLUMN_ADMIN_ID = "admin_id";
    public static final String COLUMN_ADMIN_NAME = "admin_name";
    public static final String COLUMN_ADMIN_EMAIL = "admin_email";
    public static final String COLUMN_ADMIN_PASSWORD = "admin_password";

    // Laundry table name
    private static final String TABLE_LAUNDRY = "laundry";
    // Laundry Table Columns names
    private static final String COLUMN_LAUNDRY_ID = "laundry_id";
    private static final String COLUMN_LAUNDRY_NAME = "laundry_name";
    private static final String COLUMN_LAUNDRY_EMAIL = "laundry_email";
    private static final String COLUMN_LAUNDRY_PASSWORD = "laundry_password";
    private static final String COLUMN_LAUNDRY_ALAMAT = "laundry_alamat";
    private static final String COLUMN_LAUNDRY_TELEPON = "laundry_telepon";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_ALAMAT + " TEXT,"
            + COLUMN_USER_TELEPON + " TEXT"
            + ")";

    private String CREATE_ADMIN_TABLE = "CREATE TABLE " + TABLE_ADMIN + "("
            + COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ADMIN_NAME + " TEXT,"
            + COLUMN_ADMIN_EMAIL + " TEXT,"
            + COLUMN_ADMIN_PASSWORD + " TEXT"
            + ")";

    private String CREATE_LAUNDRY_TABLE = "CREATE TABLE " + TABLE_LAUNDRY + "("
            + COLUMN_LAUNDRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_LAUNDRY_NAME + " TEXT,"
            + COLUMN_LAUNDRY_EMAIL + " TEXT,"
            + COLUMN_LAUNDRY_PASSWORD + " TEXT,"
            + COLUMN_LAUNDRY_ALAMAT + " TEXT,"
            + COLUMN_LAUNDRY_TELEPON + " TEXT"
            + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    private String DROP_ADMIN_TABLE = "DROP TABLE IF EXISTS " + TABLE_ADMIN;

    private String DROP_LAUNDRY_TABLE = "DROP TABLE IF EXISTS " + TABLE_LAUNDRY;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_LAUNDRY_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ADMIN_TABLE);
        db.execSQL(DROP_LAUNDRY_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ALAMAT, user.getAlamat());
        values.put(COLUMN_USER_TELEPON, user.getTelepon());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addLaundry(Laundry laundry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LAUNDRY_NAME, laundry.getName());
        values.put(COLUMN_LAUNDRY_EMAIL, laundry.getEmail());
        values.put(COLUMN_LAUNDRY_PASSWORD, laundry.getPassword());
        values.put(COLUMN_LAUNDRY_ALAMAT, laundry.getAlamat());
        values.put(COLUMN_LAUNDRY_TELEPON, laundry.getTelepon());

        // Inserting Row
        db.insert(TABLE_LAUNDRY, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_ALAMAT,
                COLUMN_USER_TELEPON
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ALAMAT)));
                user.setTelepon(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TELEPON)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Laundry> getAllLaundry() {
        String[] columns = {
                COLUMN_LAUNDRY_ID,
                COLUMN_LAUNDRY_EMAIL,
                COLUMN_LAUNDRY_NAME,
                COLUMN_LAUNDRY_PASSWORD,
                COLUMN_LAUNDRY_ALAMAT,
                COLUMN_LAUNDRY_TELEPON
        };
        String sortOrder =
                COLUMN_LAUNDRY_NAME + " ASC";
        List<Laundry> laundryList = new ArrayList<Laundry>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LAUNDRY, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Laundry laundry = new Laundry();
                laundry.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_ID))));
                laundry.setName(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_NAME)));
                laundry.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_EMAIL)));
                laundry.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_PASSWORD)));
                laundry.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_ALAMAT)));
                laundry.setTelepon(cursor.getString(cursor.getColumnIndex(COLUMN_LAUNDRY_TELEPON)));
                laundryList.add(laundry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return laundryList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ALAMAT, user.getAlamat());
        values.put(COLUMN_USER_TELEPON, user.getTelepon());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkAdmin(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ADMIN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_ADMIN_EMAIL + " = ?" + " AND " + COLUMN_ADMIN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_ADMIN, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkLaundry(String email) {

        String[] columns = {
                COLUMN_LAUNDRY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_LAUNDRY_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_LAUNDRY, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkLaundry(String email, String password) {

        String[] columns = {
                COLUMN_LAUNDRY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_LAUNDRY_EMAIL + " = ?" + " AND " + COLUMN_LAUNDRY_PASSWORD + " = ?";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_LAUNDRY, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
