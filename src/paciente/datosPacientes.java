package paciente;

import java.sql.Date;

public class datosPacientes {

    private int id, idHistorial, telefono;
    private String apellido, nombre, sexo, municipio;
    private Date fecha_de_Nacimiento;

    public datosPacientes(int id, String nombre, String apellido, Date fecha, String sexo, String municipio, int idHistorial, int telefono) {
        this.id = id;
        this.municipio = municipio;
        this.idHistorial = idHistorial;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fecha_de_Nacimiento = fecha;
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMunicipio() {
        return municipio;
    }

    public void setIdMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_de_Nacimiento() {
        return fecha_de_Nacimiento;
    }

    public void setFecha_de_Nacimiento(Date fecha_de_Nacimiento) {
        this.fecha_de_Nacimiento = fecha_de_Nacimiento;
    }

}
