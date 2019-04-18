package reportes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author izasj
 */
public class reporteEdad {
    private SimpleStringProperty nombreEdad;
    private SimpleStringProperty apellidoEdad;
    private SimpleIntegerProperty cantidadEdad;
    
    public reporteEdad(String nom, String apellido, int cant) {
        this.nombreEdad = new SimpleStringProperty(nom);
        this.apellidoEdad = new SimpleStringProperty(apellido);
        this.cantidadEdad = new SimpleIntegerProperty(cant);
    }

    public String getNombreEdad() {
        return nombreEdad.get();
    }

    public void setNombreEdad(String nombreEdad) {
        this.nombreEdad = new SimpleStringProperty(nombreEdad);
    }

    public String getApellidoEdad() {
        return apellidoEdad.get();
    }

    public void setApellidoEdad(String apellidoEdad) {
        this.apellidoEdad = new SimpleStringProperty(apellidoEdad);
    }

    public int getCantidadEdad() {
        return cantidadEdad.get();
    }

    public void setCantidadEdad(int cantidadEdad) {
        this.cantidadEdad = new SimpleIntegerProperty(cantidadEdad);
    }
    
    
    
}
