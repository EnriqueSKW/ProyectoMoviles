package com.example.meetandfix;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class shared {
    private SharedPreferences prefs;

    public shared(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void SetNombreNegocio(String d) { prefs.edit().putString("NombreNegocio",d).commit(); }

    public void SetIdTiendaReparador(String d) { prefs.edit().putString("TiendaReparador",d).commit(); }

    public void setIdUsuario(String d)
    {
        prefs.edit().putString("IdUsuario",d).commit();
    }

    // string d = datos del string que recibe como parametros
    public void setNombreUsuario(String d) {
        prefs.edit().putString("NombreUsuario", d ).commit();
    }

    public  void  SetTipoUsuario(String d)
    {
        prefs.edit().putString("TipoUsuario", d ).commit();
    }

    public void setApellidosUsuario(String d) {
        prefs.edit().putString("ApellidosUsuario", d).commit();
    }


    public void setPassword(String d) {
        prefs.edit().putString("PasswordUsuario", d).commit();
    }

    public void setCorreoUsuario(String d) {
        prefs.edit().putString("CorreoUsuario", d).commit();
    }

    public void setImagenUsuario(String d) {
        prefs.edit().putString("ImagenUsuario", d).commit();
    }

    public void setDireccionUsuario(String d) {
        prefs.edit().putString("DireccionUsuario", d).commit();
    }


    public void setTelefonoUsuario(String d) {
        prefs.edit().putString("TelefonoUsuario", d).commit();
    }

    public String getNombreNegocio() {
        String d = prefs.getString("NombreNegocio","");
        return  d;
    }

    public String getTiendaReparador() {
        String d = prefs.getString("TiendaReparador","");
        return  d;
    }

    public String getNombreUsuario() {
        String d = prefs.getString("NombreUsuario","");
        return d;
    }

    public String getTipoUsuario()
    {
        String d = prefs.getString("TipoUsuario","");
        return d;
    }

    public String getApellidosUsuario() {
        String d = prefs.getString("ApellidosUsuario","");
        return d;
    }

    public String getIdUsuario() {
        String d = prefs.getString("IdUsuario","");
        return d;
    }

    public String getPasswordUsuario() {
        String d = prefs.getString("PasswordUsuario","");
        return d;
    }

    public String getCorreoUsuario() {
        String d = prefs.getString("CorreoUsuario","");
        return d;
    }

    public String getImagenUsuario() {
        String d = prefs.getString("ImagenUsuario","");
        return d;
    }

    public String getDireccionUsuario() {
        String d = prefs.getString("DireccionUsuario","");
        return d;
    }


    public String getTelefonoUsuario() {
        String d = prefs.getString("TelefonoUsuario","");
        return d;
    }
}
