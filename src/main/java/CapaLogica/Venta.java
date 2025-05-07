package CapaLogica;

import java.sql.Date;

public class Venta {
    
    private String idVenta;
    private Cliente idCliente;
    private Date fechaVenta;
    private String formaPago;
    private Sucursal sucursal;
    private Empleado empleado;

    //CONSTRUCTOR PARA LECTURA
    public Venta(String idVenta, Cliente idCliente, Date fechaVenta, String formaPago, Sucursal sucursal, Empleado empleado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.formaPago = formaPago;
        this.sucursal = sucursal;
        this.empleado = empleado;
    }
    
    //CONTRUCTOR PARA REGISTRAR DATOS NECESARIOS

    public Venta(String idVenta, Cliente idCliente, String formaPago, Sucursal sucursal, Empleado empleado) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.formaPago = formaPago;
        this.sucursal = sucursal;
        this.empleado = empleado;
    }
    
    public Venta(){
        
    }
    
    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
