package com.example.meetandfix.fragments.CitasAdapter;

public class CitaModel {

    private String IdReparador;
    private String IdCliente;
    private String Fecha;
    private String NombreCliente;
    private String Status;

    public CitaModel(String idReparador, String idCliente, String fecha, String nombreCliente, String status) {
        IdReparador = idReparador;
        IdCliente = idCliente;
        Fecha = fecha;
        NombreCliente = nombreCliente;
        Status = status;
    }

    public String getIdReparador() {
        return IdReparador;
    }

    public void setIdReparador(String idReparador) {
        IdReparador = idReparador;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "CitaModel{" +
                "IdReparador='" + IdReparador + '\'' +
                ", IdCliente='" + IdCliente + '\'' +
                ", Fecha='" + Fecha + '\'' +
                ", NombreCliente='" + NombreCliente + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
