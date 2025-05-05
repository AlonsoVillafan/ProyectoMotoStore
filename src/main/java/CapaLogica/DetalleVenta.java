package CapaLogica;

public class DetalleVenta {
    
    private Venta idVenta;
    private Moto idMoto;
    private int cantidad;
    private double precioVentaUnidad;
    private double descuento;

    public DetalleVenta(Venta idVenta, Moto idMoto, int cantidad, double precioVentaUnidad, double descuento) {
        this.idVenta = idVenta;
        this.idMoto = idMoto;
        this.cantidad = cantidad;
        this.precioVentaUnidad = precioVentaUnidad;
        this.descuento = descuento;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    public Moto getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Moto idMoto) {
        this.idMoto = idMoto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVentaUnidad() {
        return precioVentaUnidad;
    }

    public void setPrecioVentaUnidad(double precioVentaUnidad) {
        this.precioVentaUnidad = precioVentaUnidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
    
}
