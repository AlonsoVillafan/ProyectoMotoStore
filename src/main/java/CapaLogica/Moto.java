package CapaLogica;


public class Moto {
    private int idMoto;
    private int idTipoMoto;
    private int idMarca;
    private String Color;
    private double precio;
    private int Stock;

    public Moto(int idMoto, int idTipoMoto, int idMarca, String Color, double precio, int Stock) {
        this.idMoto = idMoto;
        this.idTipoMoto = idTipoMoto;
        this.idMarca = idMarca;
        this.Color = Color;
        this.precio = precio;
        this.Stock = Stock;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public int getIdTipoMoto() {
        return idTipoMoto;
    }

    public void setIdTipoMoto(int idTipoMoto) {
        this.idTipoMoto = idTipoMoto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    
    
    
    
    
}
