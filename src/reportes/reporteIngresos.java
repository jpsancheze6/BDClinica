package reportes;

import java.sql.Date;

public class reporteIngresos {
    String nombre, apellido;
    double costo;
    Date fecha;

    public reporteIngresos(String nombre, String apellido,Date fecha, double costo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.costo = costo;
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
