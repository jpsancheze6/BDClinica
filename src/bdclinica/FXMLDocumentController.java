package bdclinica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Pane paneAgregarPaciente, panePacientes, paneCitas,paneHistorial,
            paneReportes, paneConfiguracion, paneExtra;
    
    @FXML
    private void agregarPaciente(ActionEvent event){
        paneAgregarPaciente.setVisible(true);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    @FXML
    private void pacientes(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(true);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    @FXML
    private void citas(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(true);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    @FXML
    private void historial(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(true);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    @FXML
    private void reportes(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(true);
        //Código extra desde acá
        
    }
    @FXML
    private void configuracion(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(true);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    @FXML
    private void extra(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(true);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
