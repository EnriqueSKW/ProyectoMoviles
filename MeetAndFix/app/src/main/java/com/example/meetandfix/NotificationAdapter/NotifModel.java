package com.example.meetandfix.NotificationAdapter;

public class NotifModel {
    private int id;
    private String Nombre;
    private String Descripcion;

    public NotifModel(int id, String nombre, String descripcion) {
        this.id = id;
        Nombre = nombre;
        Descripcion = descripcion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "NotifModel{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }
}
