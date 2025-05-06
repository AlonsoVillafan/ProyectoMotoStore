package CapaLogica;

public class Sucursal {
    
    private String idSucursal;
    private String nombreSucursal;
    private Distrito distrito;

    public Sucursal(String idSucursal, String nombreSucursal, Distrito distrito) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.distrito = distrito;
    }
    
    

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
    
    //PARA MOSTRAR SOLO EL NOMBRE EN EL COMBO BOX DE VENTA
    @Override
    public String toString() {
        return nombreSucursal;
    }
    
    
        
}
