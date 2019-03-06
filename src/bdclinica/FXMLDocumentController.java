package bdclinica;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Pane paneAgregarPaciente, panePacientes, paneCitas,paneHistorial,
            paneReportes, paneConfiguracion, paneExtra, paneH, paneAgregarH;
    
    @FXML
    private void agregarPaciente(ActionEvent event){
        paneAgregarPaciente.setVisible(true);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
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
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
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
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
        //Código extra desde acá
    }
    
    // HISTORIAL /////
    @FXML
    private void historial(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(true);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
        //Código extra desde acá
    }

    //Boton para abrir el historial del paciente seleccionado(btnAbrir)
    @FXML
    private void handleButtonAction(ActionEvent event) {
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        paneH.setVisible(true);
        paneAgregarH.setVisible(false);
    }
    
    //Boton para buscar el historial a partir del nombre (btnBuscar)
    @FXML
    private void handleButtonAction2(ActionEvent event) {  
    }
    
    //Boton para agregar una consulta al historial del paciente seleccionado (btnAC)
    @FXML
    private void handleButtonAction3(ActionEvent event) {  
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        paneH.setVisible(false);
        paneAgregarH.setVisible(true);
    }
    
    //Boton para regresar al pane historial (btnReg)
    @FXML
    private void handleButtonAction4(ActionEvent event) {
        paneH.setVisible(false);
        paneHistorial.setVisible(true);
    }
    
    //Boton para agregar datos de una consulta al historial del paciente (btnAgregar)
    @FXML
    private void handleButtonAction5(ActionEvent event) {
        paneH.setVisible(false);
        paneHistorial.setVisible(true);
        paneAgregarH.setVisible(false);
    }
    // HISTORIAL/////
    
    @FXML
    private void reportes(ActionEvent event){
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(true);
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
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
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
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
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
        //Código extra desde acá
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
