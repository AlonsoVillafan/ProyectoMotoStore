package CapaDatos;

import CapaLogica.Cliente;
import CapaLogica.Empleado;
import CapaLogica.Sucursal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;


public class VentaDAO {
    
    public boolean registrarVenta(int IdVenta, Cliente IdCliente, Date FechaVenta, String FormaPago, Sucursal sucursal, Empleado empleado){
        
        String sql = "{CALL SP_REGISTRAR_VENTA(?,?,?,?,?,?)}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql)){
            
        } catch (Exception e) {
            System.out.println("Error al registrar la venta: " + e.getMessage() );
            return false;
        }
        
        
        
        
        
    }
    
    
}
