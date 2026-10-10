package CapaDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CapaLogica.Marca;
import CapaLogica.Moto;
import CapaLogica.TipoMoto;

public class MotoDAO {

    /*
    MotoDAO depende de la abstracción ProveedorConexion
    y no directamente de la clase Conexion.
    
    Esto permite cambiar la forma de obtener la conexión
    sin modificar la lógica principal del DAO.
     */
    private final ProveedorConexion proveedorConexion;

    /*
    Constructor utilizado normalmente por la aplicación.
    Por defecto seguimos utilizando nuestra clase Conexion.
     */
    public MotoDAO() {
        this(new Conexion());
    }

    /*
    Este constructor permite recibir cualquier implementación
    de ProveedorConexion.
    
    Aquí aplicamos Inversión de Control mediante inyección por constructor.
     */
    public MotoDAO(ProveedorConexion proveedorConexion) {
        this.proveedorConexion = proveedorConexion;
    }

    public List<Moto> BuscarMoto(String buscar) {

        List<Moto> lista = new ArrayList<>();

        String sql = "{CALL SP_BUSCAR_MOTO(?)}";

        /*
        La conexión ya no se crea directamente con:
        new Conexion().getConnection()
         
        Ahora se solicita mediante la abstracción.
         */
        try (
            Connection cn = proveedorConexion.getConnection();
            CallableStatement cs = cn.prepareCall(sql)
        ) {

            // El primer parámetro del procedimiento recibe el texto buscado.
            cs.setString(1, buscar);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Moto moto = new Moto(
                            rs.getInt("IdMoto"),
                            new TipoMoto(0, rs.getString("NombreTipoMoto")),
                            new Marca(0, rs.getString("NombreMarca")),
                            rs.getString("Color"),
                            rs.getFloat("Precio"),
                            rs.getInt("Stock")
                    );
                    lista.add(moto);
                }
            }
        } catch (Exception e) {

            System.out.println("Error al buscar Moto: " + e.getMessage());
        }

        return lista;
    }
}