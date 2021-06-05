package com.example.meetandfix.SearchAdapter;

import android.graphics.Bitmap;

public class ShopModel {
    private int Id;
    private String Nombre;
    private String Direccion;
    private String Image;
    private String Telefono;
    private String Correo;

    public ShopModel(int id, String nombre, String direccion,String telefono,String correo, String image) {
        Id = id;
        Nombre = nombre;
        Direccion = direccion;
        Telefono = telefono;
        Correo = correo;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        Correo = Correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ShopModel{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
