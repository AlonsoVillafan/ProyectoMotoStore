package CapaLogica;


public class Moto {
    private int idMoto;
    private TipoMoto idTipoMoto;
    private Marca idMarca;
    private String Color;
    private double precio;
    private int Stock;

    public Moto(int idMoto, TipoMoto idTipoMoto, Marca idMarca, String Color, double precio, int Stock) {
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

    public TipoMoto getIdTipoMoto() {
        return idTipoMoto;
    }

    public void setIdTipoMoto(TipoMoto idTipoMoto) {
        this.idTipoMoto = idTipoMoto;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
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