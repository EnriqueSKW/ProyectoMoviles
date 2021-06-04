package com.example.meetandfix.ReviewAdapter;

public class ReviewModel {
    private int Id;
    private String Nombre;
    private String Reseña;
    private String IdCliente;
    private String IdReparador;

    public ReviewModel(int id, String nombre, String reseña, String idCliente, String idReparador) {
        Id = id;
        Nombre = nombre;
        Reseña = reseña;
        IdCliente = idCliente;
        IdReparador = idReparador;
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

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public String getIdReparador() {
        return IdReparador;
    }

    public void setIdReparador(String idReparador) {
        IdReparador = idReparador;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Reseña='" + Reseña + '\'' +
                ", IdCliente='" + IdCliente + '\'' +
                ", IdReparador='" + IdReparador + '\'' +
                '}';
    }
}
