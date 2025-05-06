package CapaDatos;

import CapaLogica.Venta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


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
    
    public String generarIdVenta() {
        String idVenta = "V001"; // Valor por defecto
        String sql = "{CALL SP_OBTENER_ULTIMO_IDVENTA}";

        try (
            Connection cn = new Conexion().getConnection();
            CallableStatement cs = cn.prepareCall(sql);
            ResultSet rs = cs.executeQuery()
        ) {
            if (rs.next()) {
                String ultimoId = rs.getString("IdVenta"); // por ejemplo: V007
                int numero = Integer.parseInt(ultimoId.substring(1)); // extrae 007 → 7
                numero++; // incrementa → 8
                idVenta = String.format("V%03d", numero); // V008
            }
        } catch (Exception e) {
            System.out.println("No se pudo obtener el nuevo ID: " + e.getMessage());
        }

        return idVenta;
    }

}