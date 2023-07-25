package com.example.bitterfitness;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME= "BitterFitnessDB";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME= "BitterFitness";
    private static final String ID_FIELD= "id";
    private static final String USERNAME_FIELD = "Username";
    private static final String FIRSTNAME_FIELD = "FirstName";
    private static final String LASTNAME_FIELD = "LastName";
    private static final String PASSWORD_FIELD = "Password";
    private static final String EMAIL_FIELD = "Email";

    @SuppressWarnings("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append(" (")
                .append(ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(EMAIL_FIELD)
                .append(" TEXT, ")
                .append(USERNAME_FIELD)
                .append(" TEXT, ")
                .append(FIRSTNAME_FIELD)
                .append(" TEXT, ")
                .append(LASTNAME_FIELD)
                .append(" TEXT, ")
                .append(PASSWORD_FIELD)
                .append(" TEXT)");

            db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkUser(String email){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EMAIL_FIELD};
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor != null && cursor.getCount() > 0);

        if (cursor != null) {
            cursor.close();
        }

        return exists;
    }

    public void addUser(String email, String shadow, String name) {
        boolean hasFirstLastName = false;
        String firstName = "";
        String lastName = "";
        if (name.contains(" ")) {
            String[] names = name.split(" ");
            if (names.length == 2) {
                 firstName = names[0];
                 lastName = names[1];
                hasFirstLastName = true;
            }
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_FIELD, email);
        values.put(PASSWORD_FIELD, shadow);
        if (hasFirstLastName) {
            values.put(FIRSTNAME_FIELD, firstName);
            values.put(LASTNAME_FIELD, lastName);
        } else {
            values.put(FIRSTNAME_FIELD, name);
        }
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.i("DATABASE INFO: ", "User Saved: " + name);
    }

    public boolean loginUser(Context context, String email, String shadow) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {ID_FIELD};
        String selection = EMAIL_FIELD + " = ? AND " + PASSWORD_FIELD + " = ?";
        String[] selectionArgs = {email, shadow};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean userExists = (cursor != null && cursor.getCount() > 0);

        if (userExists) {
            cursor.close();
            return true;
        } else {
            // User does not exist in the database or the password doesn't match
            return false;
        }
    }


    @SuppressLint("Range")
    public String getNameByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EMAIL_FIELD};
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String username = null;

        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(USERNAME_FIELD));
            cursor.close();
        }

        return username;
    }
}
