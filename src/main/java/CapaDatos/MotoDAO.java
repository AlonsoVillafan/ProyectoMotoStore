package CapaDatos;

import CapaLogica.Marca;
import CapaLogica.Moto;
import CapaLogica.TipoMoto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MotoDAO {
    
    public List<Moto> BuscarMoto(String buscar){
        List<Moto> lista = new ArrayList<>();
        String sql = "{CALL SP_BUSCAR_MOTOS(?)}";
        
        try (   Connection cn = new Conexion().getConnection();
                CallableStatement cs = cn.prepareCall(sql)
                ){
            cs.setString(1, buscar); //EL PRIMER PARAMETRO DE MI SP RECIBE EL VALOR DE BUSCAR
            
            try (   ResultSet rs = cs.executeQuery()
                    ){
                while (rs.next()) {                                     
                    Moto moto = new Moto(
                            rs.getInt("IdMoto"),
                            new TipoMoto(0,rs.getString("NombreTipoMoto")),
                            new Marca(0,rs.getString("NombreMarca")),
                            rs.getString("Color"),
                            rs.getDouble("Precio"),
                            rs.getInt("Stock")
                        );
                    lista.add(moto);
                }                              
            }                     
        } catch (Exception e) {
            System.out.println("Error al buscar clientes: " + e.getMessage()); 
        }        
        return lista;
    }   
}
