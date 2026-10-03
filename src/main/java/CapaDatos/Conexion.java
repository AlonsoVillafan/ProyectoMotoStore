package CapaDatos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion implements  ProveedorConexion{

    //Aquí se almacenarán los valores leídos
    //desde el archivo database.properties.
    private final Properties propiedades = new Properties();

    public Conexion() {
        cargarConfiguracion();
    }

    //Carga los datos de conexión desde database.properties.
    private void cargarConfiguracion() {

        try (InputStream archivo = getClass()
                .getClassLoader()
                .getResourceAsStream("database.properties")) {

            //Validamos que el archivo realmente exista.
            if (archivo == null) {
                throw new RuntimeException("No se encontró database.properties");
            }
            propiedades.load(archivo);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la configuración de la base de datos", e);
        }
    }


    @Override 
    //Crea y devuelve una conexión hacia SQL Server.
    public Connection getConnection() throws SQLException {

        // Recuperamos los valores desde el archivo properties.
        String driver = propiedades.getProperty("db.driver");
        String url = propiedades.getProperty("db.url");
        String usuario = propiedades.getProperty("db.user");
        String password = propiedades.getProperty("db.password");

        try {
            //Cargamos el driver JDBC configurado.
            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "No se encontró el driver JDBC", e);
        }

        return DriverManager.getConnection(url, usuario, password);
    }
}