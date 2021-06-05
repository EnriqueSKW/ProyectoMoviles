package com.example.meetandfix.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.meetandfix.UserModel.UserModel
import com.example.meetandfix.fragments.CitasAdapter.CitaModel

val DATABASE_NAME="DBCITA"
val TABLE_NAME ="Cita"
val COL_IDCLIENTE="IdCliente"
val COL_IDREPARADOR="IdReparador"
val COL_NOMBRECLIENTE="NombreCliente"
val COL_FECHA ="Fecha"
val COL_ESTADO="Estado"

class DataBaseHandler( var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME+"("+
                COL_IDCLIENTE+" VARCHAR(100),"+
                COL_IDREPARADOR+" VARCHAR(100),"+
                COL_NOMBRECLIENTE+" VARCHAR(100),"+
                COL_FECHA+" VARCHAR(100),"+
                COL_ESTADO+" VARCHAR(100))"
        db?.execSQL(CREATE_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun InsertData(cita:CitaModel)
    {
        val db:SQLiteDatabase = this.writableDatabase

        var cv = ContentValues()
        cv.put(COL_ESTADO,cita.status)
        cv.put(COL_FECHA,cita.fecha)
        cv.put(COL_IDCLIENTE,cita.idCliente)
        cv.put(COL_IDREPARADOR,cita.idReparador)
        cv.put(COL_NOMBRECLIENTE,cita.nombreCliente)


        var result = db.insert(TABLE_NAME,null,cv)
        if(result==-1.toLong())
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Succes",Toast.LENGTH_SHORT).show()
        }

    }

    fun deletetable()
    {
        val db:SQLiteDatabase = this.writableDatabase

        db?.execSQL("DROP TABLE "+ TABLE_NAME);
    }
    fun createtable()
    {
        val db:SQLiteDatabase = this.writableDatabase
        val CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME+"("+
                COL_IDCLIENTE+" VARCHAR(100),"+
                COL_IDREPARADOR+" VARCHAR(100),"+
                COL_NOMBRECLIENTE+" VARCHAR(100),"+
                COL_FECHA+" VARCHAR(100),"+
                COL_ESTADO+" VARCHAR(100))"
        db?.execSQL(CREATE_TABLE);
    }

}