package com.example.tdpa_3p_ex.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context, name: String, factory: CursorFactory?, version: Int):
    SQLiteOpenHelper(context, name, factory,version)
{
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE alumnos(" +
                "_id INTEGER primary key autoincrement," +
                "nombre text," +
                "materia text," +
                "calPrim real," +
                "calSeg real)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}