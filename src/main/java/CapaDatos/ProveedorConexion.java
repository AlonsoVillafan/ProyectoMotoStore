package CapaDatos;

import java.sql.Connection;
import java.sql.SQLException;

//Contrato que define cómo obtener una conexión.
//El resto del programa puede depender de esta interfaz
//sin preocuparse por la implementación concreta.

public interface ProveedorConexion {

    //Devuelve una conexión a la base de datos.
    Connection getConnection() throws SQLException;
}