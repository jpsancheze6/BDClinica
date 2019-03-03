package paciente;

import java.sql.Date;



public class datosPacientes {
    private int id,idMunicipio,idHistorial;
    private String nombre, sexo;
    private Date fecha_de_Nacimiento;

    public datosPacientes(int id, String nombre, Date fecha, String sexo, int idMunicipio, int idHistorial) {
        this.id = id;
        this.idMunicipio = idMunicipio;
        this.idHistorial = idHistorial;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_de_Nacimiento = fecha_de_Nacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
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
