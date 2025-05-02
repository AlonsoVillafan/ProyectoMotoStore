package CapaLogica;

import java.sql.Date;

public class Cliente {
    private int idCliente;
    private String nombres;
    private String apellidos;
    private String dni;
    private Date fechaNac;
    private String telefono;
    private Date fechaRegistro;
    private Distrito distrito; // ALMACENAR EL ID Y NOMBRE DEL DISTRITO

    // Constructor completo
    public Cliente(int idCliente, String nombres, String apellidos, String dni, Date fechaNac,
                    String telefono, Date fechaRegistro, Distrito distrito) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.distrito = distrito;
    }

    // Getters y Setters
    //cuando voy a retornar un atributo
    //declaro public int (declaro el tipo de dato a retornar)
    public int getIdCliente() { return idCliente; }
    //cuando voy a setear (colocar) un dato en un atributo
    //es public void ya que eso no devulve nada
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    
    public Date getFechaNac() {return fechaNac;}
    public void setFechaNac(Date fechaNac) {this.fechaNac = fechaNac; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Distrito getDistrito() { return distrito; }
    public void setDistrito(Distrito distrito) { this.distrito = distrito; }

    
}
