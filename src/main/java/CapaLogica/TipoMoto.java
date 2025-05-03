package CapaLogica;


public class TipoMoto {
    private int idTipoMoto;
    private String nombreTipoMoto;

    public TipoMoto(int idTipoMoto, String nombreTipoMoto) {
        this.idTipoMoto = idTipoMoto;
        this.nombreTipoMoto = nombreTipoMoto;
    }

    public int getIdTipoMoto() {
        return idTipoMoto;
    }

    public void setIdTipoMoto(int idTipoMoto) {
        this.idTipoMoto = idTipoMoto;
    }

    public String getNombreTipoMoto() {
        return nombreTipoMoto;
    }

    public void setNombreTipoMoto(String nombreTipoMoto) {
        this.nombreTipoMoto = nombreTipoMoto;
    }
    
    //LO QUE SE MOSTRARA AL IMPRIMIR EL OBJETO
    @Override
    public String toString() {
        return nombreTipoMoto;
    }      
}
