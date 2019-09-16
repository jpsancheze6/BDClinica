package historial;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tablePaci {

    private SimpleIntegerProperty idPaciente;
    private SimpleStringProperty Nombre;
    private SimpleStringProperty Apellido;

    public tablePaci(Integer idP, String nom, String ape) {
        this.idPaciente = new SimpleIntegerProperty(idP);
        this.Nombre = new SimpleStringProperty(nom);
        this.Apellido = new SimpleStringProperty(ape);
    }

    public int getId() {
        return idPaciente.get();
    }

    public void setStudentId(int Id) {
        this.idPaciente = new SimpleIntegerProperty(Id);
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
