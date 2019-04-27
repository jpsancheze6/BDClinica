package reportes;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author izasj
 */
public class reporteMunicipios {
    private SimpleStringProperty nombreMuni;
    private SimpleStringProperty apellidoMuni;
    private SimpleStringProperty cantidadMuni;
    
    public reporteMunicipios(String nom, String cant, String ape) {
        this.nombreMuni = new SimpleStringProperty(nom);
        this.apellidoMuni = new SimpleStringProperty(ape);
        this.cantidadMuni = new SimpleStringProperty(cant);
    }

    public String getApellidoMuni() {
        return apellidoMuni.get();
    }

    public void setApellidoMuni(String apellidoMuni) {
        this.apellidoMuni = new SimpleStringProperty(apellidoMuni);;
    }
    
    public String getNombreMuni() {
        return nombreMuni.get();
    }

    public void setNombreMuni(String nombreMuni) {
        this.nombreMuni = new SimpleStringProperty(nombreMuni);;
    }

    public String getCantidadMuni() {
        return cantidadMuni.get();
    }

    public void setCantidadMuni(String cantidadMuni) {
        this.cantidadMuni = new SimpleStringProperty(cantidadMuni);;
    }
    
    
    
}
