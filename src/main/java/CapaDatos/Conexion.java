package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    
    //pivate : Solo esta clase puede acceder a estas variables
    //static : La variable pertenece a la clase, no a una instancia. Solo se carga una vez en memoria
    private static final 
            String url = "jdbc:sqlserver://localhost:1433;instanceName=SQLEXPRESS;databaseName=STOREMOTO_BD;encrypt=true;trustServerCertificate=true";     
    private static final
            String user = "sa"; 
    private static final
            String password = "1234";
    
    
    //ESTE METODO PUEDE LANZAR UNA EXCEPCION Y USANDO THROWS
    //DEJO QUE LA CAPA DATOS MANEJE LOS ERRORES.
    public Connection getConnection() throws SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver JDBC: ", e);
        }
        //SI NO HUBO ERRORES, RETORNO LA CONEXION
        return DriverManager.getConnection(url,user,password);    
    }
}