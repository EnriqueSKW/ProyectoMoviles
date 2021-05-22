package com.example.meetandfix.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.meetandfix.UserModel.UserModel

val DATABASE_NAME="DBUSUARIO"
val TABLE_NAME ="Usuario"
val COL_CORREO="Correo"
val COL_CONTRASEÑA="Contraseña"

class DataBaseHandler( var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME+"("+
                COL_CORREO+" VARCHAR(100),"+
                COL_CONTRASEÑA+" VARCHAR(100))"
        db?.execSQL(CREATE_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun InsertData(user:UserModel)
    {
        val db:SQLiteDatabase = this.writableDatabase

        var cv = ContentValues()
        cv.put(COL_CONTRASEÑA,user.contraseña)
        cv.put(COL_CORREO,user.correo)

        var result = db.insert(TABLE_NAME,null,cv)
        if(result==-1.toLong())
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Succes",Toast.LENGTH_SHORT).show()
        }

    }


}