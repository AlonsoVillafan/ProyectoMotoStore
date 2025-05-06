package CapaDatos;

import CapaLogica.Venta;
import java.sql.CallableStatement;
import java.sql.Connection;


public class VentaDAO {
    
    public boolean registrarVenta(Venta venta){
        
        String sql = "{CALL SP_REGISTRAR_VENTA(?,?,?,?,?)}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql)){
            
            cs.setString(1, venta.getIdVenta());
            cs.setInt(2, venta.getIdCliente().getIdCliente());
            cs.setString(3, venta.getFormaPago());
            cs.setString(4, venta.getSucursal().getIdSucursal());
            cs.setInt(5, venta.getEmpleado().getIdEmpleado());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar la venta: " + e.getMessage() );
            return false;
        } 
    }   
}
