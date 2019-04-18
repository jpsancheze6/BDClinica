package reportes;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author izasj
 */
public class reporteMunicipios {
    private SimpleStringProperty nombreMuni;
    private SimpleStringProperty cantidadMuni;
    
    public reporteMunicipios(String nom, String cant) {
        this.nombreMuni = new SimpleStringProperty(nom);
        this.cantidadMuni = new SimpleStringProperty(cant);
    }

    public String getNombreMuni() {
        return nombreMuni.get();
    }

    public void setNombreMuni(String nombreMuni) {
        this.nombreMuni =new SimpleStringProperty(nombreMuni);;
    }

    public String getCantidadMuni() {
        return cantidadMuni.get();
    }

    public void setCantidadMuni(String cantidadMuni) {
        this.cantidadMuni = new SimpleStringProperty(cantidadMuni);;
    }
    
    
    
}
