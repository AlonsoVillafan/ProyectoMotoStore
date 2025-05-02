package CapaDatos;

import CapaLogica.Distrito;           // Clase modelo que representa un distrito
import java.sql.Connection;           // Clase para manejar la conexión a base de datos
import java.sql.PreparedStatement;    // Clase para preparar consultas SQL
import java.sql.ResultSet;            // Clase para manejar los resultados de una consulta
import java.sql.SQLException;         // Clase para capturar errores SQL
import java.util.ArrayList;           // Clase para usar listas dinámicas
import java.util.List;                // Interfaz general para listas

public class DistritoDAO {
    
    //METODO QUE DEVUELVE UNA LISTA DE OBJETOS Distrito
    //OBTENIDOS DESDE LA BASE DE DATOS
    public List<Distrito> listarDistritos() {
        //SE CREA UNA LISTA VACIA QUE CONTENDRA LOS DISTRITOS
        //RECUPERADOS DE LA BASE DE DATOS
        List<Distrito> lista = new ArrayList<>();           
        //DEFINO LA CONSULTA SQL O STORE PROCEDURE
        String sql = "{CALL SP_LISTAR_DISTRITOS}";

        try (   Connection conn = new Conexion().getConnection(); //OBTENGO Y USO LA CONEXION
                PreparedStatement stmt = conn.prepareStatement(sql); //PREPARO LA CONSULTA SQL
                ResultSet rs = stmt.executeQuery()){            //EJECUTO LA CONSULTA Y OBTENGO EL RESULTADO

            while (rs.next()) {
                //SE CREA UN OBJETO TIPO DISTRITO USANDO
                //LOS VALORES OBTENIDOS DE LA FILA ACTUAL
                Distrito distrito = new Distrito(
                        rs.getInt("idDistrito"),            //OBTENGO EL ID DEL DISTRITO
                        rs.getString("nombreDistrito")      //OBTENGO EL NOMBRE DEL DISTRITO
                );
                //SE AGREGA CADA OBJETO A LA LISTA
                lista.add(distrito);
            }
        } catch (SQLException e) {
            //EN CASO DE ERROR SE MUESTRA UN MENSAJE EN CONSOLA
            System.out.println("Error al listar distritos: " + e.getMessage());
        }
        //SE DEVUELVE LA LISTA CON TODOS LOS OBJETOS DISTRITO
        return lista;
    }
}

