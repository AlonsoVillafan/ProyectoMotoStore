package CapaDatos;

import CapaLogica.DetalleVenta;
import java.sql.CallableStatement;
import java.sql.Connection;


public class DetalleVentaDAO {
    
    public boolean registrarDetalleVenta(DetalleVenta detalleVenta){
        
        String sql = "{CALL SP_REGISTRAR_DETALLE_VENTA(?,?,?,?,?)}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql)){
            
            cs.setString(1, detalleVenta.getVenta().getIdVenta());
            cs.setInt(2, detalleVenta.getMoto().getIdMoto());
            cs.setInt(3, detalleVenta.getCantidad());
            cs.setDouble(4, detalleVenta.getPrecioVentaUnidad());
            cs.setDouble(5, detalleVenta.getDescuento());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar el detalle de venta: " + e.getMessage());
            return false;
        } 
    }
}
