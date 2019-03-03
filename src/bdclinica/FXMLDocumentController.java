package bdclinica;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import BD.conexionBD;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import paciente.registrarPaciente;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane paneAgregarPaciente, panePacientes, paneCitas, paneHistorial,
            paneReportes, paneConfiguracion, paneExtra;
    @FXML
    private TextField txtNombre;
    @FXML
    private DatePicker dtFecha;
    @FXML
    private CheckBox cuadroMasculino, cuadroFemenino;
    @FXML
    private ComboBox cbxMunicipios;

    @FXML
    private void agregarPaciente(ActionEvent event) throws SQLException {
        paneAgregarPaciente.setVisible(true);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá
        //Conectar con la base de datos para cargar municipios
        conexionBD sql = new conexionBD();
        Connection con = sql.conectarMySQL();
        String sentencia = "select * from municipio";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sentencia);
        //
        ObservableList<String> municipios = FXCollections.observableArrayList();
        municipios.add("-- Seleccione Municipio --");
        int n = -1;
        if (rs != null) {
            while (rs.next()) {
                //System.out.println(rs.getString(1) + ", " + rs.getString(2));
                //Agregar columna a los municipios
                municipios.add(rs.getString(2));
            }
            cbxMunicipios.setItems(municipios);
        } else {
            System.out.println("No hay datos");
        }
        cancelarIngresarPaciente();
    }

    @FXML
    private void pacientes(ActionEvent event) {
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
    private void citas(ActionEvent event) {
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
    private void historial(ActionEvent event) {
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
    private void reportes(ActionEvent event) {
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
    private void configuracion(ActionEvent event) {
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
    private void extra(ActionEvent event) {
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(true);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        //Código extra desde acá

    }

    //************************************************************************//
    //Pacientes
    @FXML
    private void seleccionarMasculino() {
        cuadroFemenino.setSelected(false);
        cuadroMasculino.setSelected(true);
    }

    @FXML
    private void seleccionarFemenino() {
        cuadroFemenino.setSelected(true);
        cuadroMasculino.setSelected(false);
    }

    @FXML
    private void guardarUsuario() {
        String nombre = txtNombre.getText();
        LocalDate fecha = dtFecha.getValue();
        String genero = "";
        //Comprobaciones de que no estén vacios los datos
        int n = 0;
        if (nombre.equals("") || nombre.equals(null)) {
            n++;
        }
        if (fecha.equals(LocalDate.now())) {
            n++;
        }
        if (cuadroFemenino.isSelected() == false) {
            if (cuadroMasculino.isSelected() == false) {
                n++;
            }
        }
        if (cuadroMasculino.isSelected() == false) {
            if (cuadroFemenino.isSelected() == false) {
                n++;
            }
        }
        if (cuadroMasculino.isSelected() == true) {
            genero = "M";
        } else {
            genero = "F";
        }
        int seleccion = cbxMunicipios.getSelectionModel().getSelectedIndex();
        if (seleccion == 0) {
            n++;
        }
        if (n > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("Datos incompletos");
            alert.setContentText("Por favor ingrese todos los datos");
            alert.showAndWait();
        } else {
            //Mandar a registrarPaciente.java
            registrarPaciente rp = new registrarPaciente();
            rp.recibirDatos(nombre, fecha, genero, seleccion);
            cancelarIngresarPaciente();
        }

    }

    @FXML
    private void cancelarIngresarPaciente() {
        txtNombre.setText("");
        dtFecha.setValue(LocalDate.now());
        cbxMunicipios.getSelectionModel().select(0);
        cuadroMasculino.setSelected(false);
        cuadroFemenino.setSelected(false);
    }

    //************************************************************************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
