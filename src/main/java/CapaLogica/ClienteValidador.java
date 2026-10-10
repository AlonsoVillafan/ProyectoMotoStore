package CapaLogica;

import java.text.SimpleDateFormat;
import java.util.Date;

//Contiene las validaciones relacionadas con Cliente.

//La lógica se separa del formulario para que pueda
//reutilizarse y probarse de forma independiente.

public class ClienteValidador {

    //Verifica si alguno de los campos obligatorios está vacío.
    
    //@return true si existe al menos un campo vacío.
    public boolean hayCamposVacios(String nombres, String apellidos, String dni, String fechaNacimiento, String telefono) {

        return nombres.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || fechaNacimiento.isEmpty() || telefono.isEmpty();
    }

    //Convierte una fecha escrita como dd/MM/yyyy a un objeto Date.
    //@return la fecha convertida o null si el formato es inválido.
    public Date convertirFecha(String fechaTexto) {

        try {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            // Evita aceptar fechas imposibles como 32/01/2026.
            formato.setLenient(false);
            return formato.parse(fechaTexto);

        } catch (Exception e) {
            // Retornamos null para indicar que la fecha no es válida.
            return null;
        }
    }
}