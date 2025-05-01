package CapaLogica;

public class Distrito {
    private int idDistrito;
    private String nombreDistrito;

    public Distrito(int idDistrito, String nombreDistrito) {
        this.idDistrito = idDistrito;
        this.nombreDistrito = nombreDistrito;
    }

    public int getId() {
        return idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
    
    
    
    @Override
    public String toString() {
        return nombreDistrito;
    }
}