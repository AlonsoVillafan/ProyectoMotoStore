package CapaLogica;

public class DetalleVenta {
    
    private Venta venta;
    private Moto moto;
    private int cantidad;
    private double precioVentaUnidad;
    private double descuento;

    public DetalleVenta(Venta venta, Moto moto, int cantidad, double precioVentaUnidad, double descuento) {
        this.venta = venta;
        this.moto = moto;
        this.cantidad = cantidad;
        this.precioVentaUnidad = precioVentaUnidad;
        this.descuento = descuento;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
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
