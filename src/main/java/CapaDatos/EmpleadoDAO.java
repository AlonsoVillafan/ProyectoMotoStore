package CapaDatos;

import CapaLogica.Empleado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    
    public List<Empleado> listarEmpleados (){
        
        List<Empleado> lista = new ArrayList<>();
        String sql = "{CALL SP_LISTAR_EMPLEADO}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {  
                Empleado empleado = new Empleado(
                        rs.getInt("IdEmpleado"),
                        rs.getString("Nombres")
                );
                lista.add(empleado);
            }         
        } catch (Exception e) {
            System.out.println("Error al listar los empleados: " + e.getMessage());
        }
        return lista;     
    } 
    
}
