package com.araskaplan.weatcastapp.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME ="MyDB"
val TABLE_NAME="Cities"
val COL_NAME = "city"
class SQLiteHelper(
    var context:Context
):SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable="CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COL_NAME VARCHAR(64))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    val db: SQLiteDatabase =this.writableDatabase

    fun insertCity(city:String){
        var cv=ContentValues()
        cv.put(COL_NAME,city)
        var result=db.insert(TABLE_NAME,null,cv)
        result.let {
            Toast.makeText(context,"The city is now bookmarked.",Toast.LENGTH_LONG).show()
        }
    }

    fun readData():ArrayList<String>{
        var list= arrayListOf<String>()

        val query="SELECT * FROM $TABLE_NAME"
        val result=db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                var city=result.getString(result.getColumnIndex(COL_NAME))
                list.add(city)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }
    fun removeData(city: String){
        db.delete(TABLE_NAME,"$COL_NAME=?", arrayOf(city))
    }

}