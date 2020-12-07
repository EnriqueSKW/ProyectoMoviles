package com.example.meetandfix;
import android.app.Application;


public class VariablesGlobales extends Application  {
    private String NombreUsuario = "";
    private String Telefono = "";
    public String NombreUsuario2 = "";
    public String Telefono2 = "";
    private static VariablesGlobales mInstance= null;
    protected VariablesGlobales(){}
    public static synchronized VariablesGlobales getInstance() {
        if(null == mInstance){
            mInstance = new VariablesGlobales();
        }
        return mInstance;
    }

    public String getNombre(){
        return this.NombreUsuario;
    }

    public void setNombre(String d){
        this.NombreUsuario=d;
    }
}


