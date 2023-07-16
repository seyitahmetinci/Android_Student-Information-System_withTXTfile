package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    private static final String DBName = "StudentDB";
    private static final String DBTableName = "Students";
    private static final int DBVersion = 1;
    private static final String StudentNumber = "_ID";
    private static final String StudentName = "Name";
    private static final String StudentSurname = "Surname";
    private static final String StudentProgram = "Program";

    private static final String CREATE_TABLE = "CREATE TABLE " + DBTableName + " (" +
            StudentNumber + " STRING PRIMARY KEY, " +
            StudentName + " STRING NOT NULL, " +
            StudentSurname + " STRING NOT NULL, " +
            StudentProgram + " STRING NOT NULL)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DBTableName;
    private Context context;

    public MyDB(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentNumber, student.getStudentNo());
        contentValues.put(StudentName, student.getName());
        contentValues.put(StudentSurname, student.getSurname());
        contentValues.put(StudentProgram, student.getProgram());

        try {
            db.insert(DBTableName, null, contentValues);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cursor getStudents() {
        String readAllQuery = "SELECT * FROM " + DBTableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(readAllQuery, null);
        }
        return cursor;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(StudentNumber, student.getStudentNo());
        values.put(StudentName, student.getName());
        values.put(StudentSurname, student.getSurname());
        values.put(StudentProgram, student.getProgram());

        db.update(DBTableName, values, "_ID=?", new String[]{student.studentNo});
        db.close();
    }

}
