package CapaDatos;

import CapaLogica.Distrito;
import CapaLogica.Sucursal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SucursalDAO {
    
    public List<Sucursal> listarSucursal (){
        
        List<Sucursal> lista = new ArrayList<>();
        String sql = "{CALL SP_LISTAR_SUCURSAL}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {  
                Sucursal sucursal = new Sucursal(
                        rs.getString("IdSucursal"),
                        rs.getString("NombreSucursal"),
                        new Distrito (rs.getInt("IdDistrito"))
                );
                lista.add(sucursal);
            }         
        } catch (Exception e) {
            System.out.println("Error al listar sucursal: " + e.getMessage());
        }
        return lista;     
    } 
}
