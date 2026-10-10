package CapaLogica;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ClienteValidadorTest {

    //Creamos el objeto que será probado.
    private final ClienteValidador validador = new ClienteValidador();

    //Comprueba que el método retorne false
    //cuando todos los campos contienen información.
    @Test
    public void noDebeDetectarCamposVaciosCuandoTodosEstanLlenos() {

        boolean resultado = validador.hayCamposVacios(
                "Alonso",
                "Villafan",
                "12345678",
                "15/05/2000",
                "999999999"
        );
        //Esperamos false porque ningún campo está vacío.
        assertFalse(resultado);
    }



    //Comprueba que se detecte correctamente
    //cuando uno de los campos obligatorios está vacío.
    @Test
    public void debeDetectarCuandoExisteUnCampoVacio() {

        boolean resultado = validador.hayCamposVacios(
                "Alonso",
                "",
                "12345678",
                "15/05/2000",
                "999999999"
        );

        //Esperamos true porque apellidos está vacío.
        assertTrue(resultado);
    }


    //Comprueba que una fecha correcta pueda convertirse.
    @Test
    public void debeConvertirUnaFechaValida() {

        java.util.Date resultado = validador.convertirFecha("15/05/2000");

        //Una fecha válida debe devolver un objeto Date.
        assertNotNull(resultado);
    }



    //Comprueba que una fecha imposible sea rechazada.
    @Test
    public void debeRechazarUnaFechaInvalida() {

        java.util.Date resultado = validador.convertirFecha("32/01/2000");

        //Una fecha inválida debe devolver null.
        assertNull(resultado);
    }
}