/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tito88
 */
public class tablasexo {
    //Nombre, Apellido ,Fecha_de_Nacimiento, Sexo, idMunicipio

    private SimpleStringProperty Nombre;
    private SimpleStringProperty Apellido;
    private SimpleStringProperty Fecha;
    private SimpleStringProperty Telefono;
    private SimpleStringProperty Municipio;

    public tablasexo(String Nombre1, String Apellido1, String Fecha1,String Telefono1) {

        this.Nombre = new SimpleStringProperty(Nombre1);
        this.Apellido = new SimpleStringProperty(Apellido1);
        this.Fecha = new SimpleStringProperty(Fecha1);
        this.Telefono = new SimpleStringProperty(Telefono1);
  //        this.Municipio = new SimpleStringProperty(m);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public void setNombre(String nombre) {
        this.Nombre = new SimpleStringProperty(nombre);
    }

    public String getApellido() {
        return Apellido.get();
    }

    public void setApellido(String apellido) {
        this.Apellido = new SimpleStringProperty(apellido);
    }

    public String getFecha() {
        return Fecha.get();
    }

    public void setFecha(String fecha) {
        this.Fecha = new SimpleStringProperty(fecha);
    }

    public String getTelefono() {
        return Telefono.get();
    }

    public void setTelefono(String telefono) {
        this.Telefono = new SimpleStringProperty(telefono);
    }
}
