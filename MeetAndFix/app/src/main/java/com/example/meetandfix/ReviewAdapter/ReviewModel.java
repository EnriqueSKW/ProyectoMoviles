package com.example.meetandfix.ReviewAdapter;

public class ReviewModel {
    private int Id;
    private String Nombre;
    private String Reseña;
    private String Fecha;

    public ReviewModel(int id, String nombre, String reseña, String fecha) {
        Id = id;
        Nombre = nombre;
        Reseña = reseña;
        Fecha = fecha;
    }

    public ReviewModel(int id) {
        Id = id;
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

    public String getReseña() {
        return Reseña;
    }

    public void setReseña(String reseña) {
        Reseña = reseña;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Reseña='" + Reseña + '\'' +
                ", Fecha='" + Fecha + '\'' +
                '}';
    }
}
