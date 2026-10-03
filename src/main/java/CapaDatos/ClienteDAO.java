package CapaDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CapaLogica.Cliente;
import CapaLogica.Distrito;



public class ClienteDAO {

    // El DAO depende de una abstracción y no directamente
    // de la clase Conexion.
    private final ProveedorConexion proveedorConexion;

    
    //Constructor normal utilizado por la aplicación.
    //Actualmente utilizamos SQL Server mediante Conexion.
    public ClienteDAO() {
        this(new Conexion());
    }

    //Constructor que permite proporcionar otra implementación
    //de conexión sin modificar el código de ClienteDAO.
    public ClienteDAO(ProveedorConexion proveedorConexion) {
        this.proveedorConexion = proveedorConexion;
    }
    
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //METODO QUE DEVUELVE TRUE O FALSE SI SE REALIZÓ EL REGISTRO
    public boolean registrarCliente(Cliente cliente){
       
        String sql = "{CALL SP_REGISTRAR_CLIENTE(?,?,?,?,?,?)}";
        
        //La conexión se obtiene mediante la abstracción.
        try (   Connection cn = proveedorConexion.getConnection();
                CallableStatement cs = cn.prepareCall(sql)){           
            cs.setString(1, cliente.getNombres());
            cs.setString(2, cliente.getApellidos());
            cs.setString(3, cliente.getDni());
            //CONVIERTO JAVA.UTIL.DATE A JAVA.SQL.DATE
            java.sql.Date fechaSQL = new java.sql.Date(cliente.getFechaNac().getTime());
            cs.setDate(4, fechaSQL);
            
            cs.setString(5, cliente.getTelefono());
            cs.setInt(6, cliente.getDistrito().getId());           
            cs.executeUpdate();          
            return true;          
        } catch (Exception e) {
            System.out.println("Error al registrar Cliente: " + e.getMessage() );
            return false;
        }   
    }  
    
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //METODO PARA LISTAR TODOS LOS CLIENTES
    public List<Cliente> listarClientes(){
        List<Cliente> lista = new ArrayList<>();
        String sql = "{CALL SP_LISTAR_CLIENTES}";
        //La conexión se obtiene mediante la abstracción.       
        try (   Connection cn = proveedorConexion.getConnection();
                CallableStatement cs = cn.prepareCall(sql);
                ResultSet rs = cs.executeQuery()
                ){                    
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Dni"),
                        rs.getDate("FechaNacimiento"),
                        rs.getString("Telefono"),
                        rs.getDate("FechaRegistro"),
                        new Distrito (rs.getInt("IdDistrito"),rs.getString("NombreDistrito"))
                );
                lista.add(cliente);             
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar los clientes: " + e.getMessage());
        }
        return lista;  
    }
    
    
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //METODO PARA BUSCAR CLIENTES
    public List<Cliente> buscarCliente(String buscar){
        List<Cliente> lista = new ArrayList<>();
        String sql = "{CALL SP_BUSCAR_CLIENTE(?)}";
      
        //La conexión se obtiene mediante la abstracción.
        try (   Connection cn = proveedorConexion.getConnection();
                CallableStatement cs = cn.prepareCall(sql);
                ){
            
            //REEMPLAZO EL PARAMETRO DEL SP POR EL VALOR DE LA VARIABLE BUSCAR
            cs.setString(1, buscar);
            
            try (   ResultSet rs = cs.executeQuery()){
                
            //EL RESULTADO DE ESA CONSULTA LA ALMACENO EN LA LISTA
            
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            
                            rs.getInt("idCliente"),
                            rs.getString("Nombres"),
                            rs.getString("Apellidos"),
                            rs.getString("Dni"),
                            rs.getDate("FechaNacimiento"),
                            rs.getString("Telefono"),
                            rs.getDate("FechaRegistro"),
                            new Distrito (rs.getInt("IdDistrito"),rs.getString("NombreDistrito"))
                    );          
                    lista.add(cliente);
                }
            }                           
        } catch (Exception e) {
            System.out.println("Error al buscar clientes: " + e.getMessage());           
        }
        return lista;
    }
}
