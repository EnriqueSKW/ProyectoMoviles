package com.example.meetandfix.SearchAdapter;

public class ShopModel {
    private int Id;
    private String Nombre;
    private String Direccion;
    private String Image;

    public ShopModel(int id, String nombre, String direccion, String image) {
        Id = id;
        Nombre = nombre;
        Direccion = direccion;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
                ", Image='" + Image + '\'' +
                '}';
    }
}
