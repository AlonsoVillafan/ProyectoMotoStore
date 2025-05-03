package CapaDatos;

import CapaLogica.Moto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MotoDAO {
    
    //METODO PARA LISTAR LAS MOTOS DISPONIBLES
    public List<Moto> BuscarMoto(String buscar){
        List<Moto> lista = new ArrayList<>();
        String sql = "{CALL SP_BUSCAR_MOTOS(?)}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql)
                ){
            cs.setString(1, buscar);
            
            try (   ResultSet rs = cs.executeQuery()
                    ){
                while (rs.next()) {
                    Moto moto = new Moto(0, 0, 0, sql, 0, 0);
                    
                    
                }
                
                
            } 
                      
        } catch (Exception e) {
            System.out.println("Error al buscar clientes: " + e.getMessage()); 
        }
        
        return lista;

    }
    
}
