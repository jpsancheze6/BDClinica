package cita;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class paciente_tablacita {

    private SimpleIntegerProperty Id;
    private SimpleStringProperty Nombre;
    private SimpleBooleanProperty Reconsulta;
    private SimpleStringProperty Telefono;
    //  private SimpleDateTimePropety Fecha;
    private SimpleBooleanProperty Atendido;
    private SimpleIntegerProperty Costo;
    private SimpleStringProperty Fecha;

    public paciente_tablacita(Integer Id1, String Nombre1, boolean reconsulta1, String Telefono1,String fecha, boolean atendido, int costo) {
        this.Id = new SimpleIntegerProperty(Id1);
        this.Nombre = new SimpleStringProperty(Nombre1);
        this.Reconsulta = new SimpleBooleanProperty(reconsulta1);
        this.Telefono = new SimpleStringProperty(Telefono1);
        this.Fecha=new SimpleStringProperty(fecha);
        this.Atendido = new SimpleBooleanProperty(atendido);
        this.Costo = new SimpleIntegerProperty(costo);
    }

    public int getId() {
        return Id.get();
    }
     public void settId(int Id) {
        this.Id = new SimpleIntegerProperty(Id);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public void setNombre(String nombre) {
        this.Nombre = new SimpleStringProperty(nombre);
    }

    public boolean getReconsulta() {
        return Reconsulta.get();
    }

    public void setReconsulta(boolean reconsulta) {
        this.Reconsulta = new SimpleBooleanProperty(reconsulta);
    }

    public String getTelefono() {
        return Telefono.get();
    }

    public void setTelefono(String telefono) {
        this.Telefono = new SimpleStringProperty(telefono);
    }

    public boolean getAtendido() {
        return Atendido.get();
    }

    public void setAtendido(boolean atendido) {
        this.Atendido = new SimpleBooleanProperty(atendido);
    }

    public int getCosto() {
        return Costo.get();
    }

    public void setCosto(int costo) {
        this.Costo = new SimpleIntegerProperty(costo);
    }
    public String getFecha() {
        return Fecha.get();
    }

    public void setFecha(String fecha) {
        this.Fecha = new SimpleStringProperty(fecha);
    }
}
