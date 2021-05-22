package com.example.meetandfix.UserModel;

public class UserModel {


    private String Correo;
    private String Contraseña;

    public UserModel( String correo, String contraseña) {

        Correo = correo;
        Contraseña = contraseña;
    }


    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                ", Correo='" + Correo + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                '}';
    }
}
