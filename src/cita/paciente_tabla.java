package cita;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class paciente_tabla {

    private SimpleIntegerProperty Id;
    private SimpleStringProperty Nombre;
    private SimpleStringProperty Apellido;

    public paciente_tabla(Integer Id1, String Nombre1, String Apellido1) {
        this.Id = new SimpleIntegerProperty(Id1);
        this.Nombre = new SimpleStringProperty(Nombre1);
        this.Apellido = new SimpleStringProperty(Apellido1);
    }

    public int getId() {
        return Id.get();
    }

    public void setStudentId(int Id) {
        this.Id = new SimpleIntegerProperty(Id);
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
}
