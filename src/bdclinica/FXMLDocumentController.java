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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import paciente.datosPacientes;
import paciente.registrarPaciente;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane paneAgregarPaciente, panePacientes, paneCitas, paneHistorial,
            paneReportes, paneConfiguracion, paneExtra;
    @FXML
    private TextField txtNombre, txtApellido;
    @FXML
    private DatePicker dtFecha;
    @FXML
    private CheckBox cuadroMasculino, cuadroFemenino;
    @FXML
    private ComboBox cbxMunicipios;
    @FXML
    private TableView tblPacientes;

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
        //Agregar a tblPacientes los pacientes que hay
        tblPacientes.getColumns().clear();
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<datosPacientes, Integer>("id"));
        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<datosPacientes, String>("nombre"));
        TableColumn apellido = new TableColumn("Apellido");
        apellido.setCellValueFactory(new PropertyValueFactory<datosPacientes, String>("apellido"));
        TableColumn fecha = new TableColumn("Nacimiento");
        fecha.setCellValueFactory(new PropertyValueFactory<datosPacientes, Date>("fecha_de_Nacimiento"));
        TableColumn sexo = new TableColumn("Sexo");
        sexo.setCellValueFactory(new PropertyValueFactory<datosPacientes, String>("sexo"));
        TableColumn idMunicipio = new TableColumn("Municipio");
        idMunicipio.setCellValueFactory(new PropertyValueFactory<datosPacientes, Integer>("idMunicipio"));
        TableColumn idHistorial = new TableColumn("Historial");
        idHistorial.setCellValueFactory(new PropertyValueFactory<datosPacientes, Integer>("idHistorial"));

        tblPacientes.getColumns().addAll(id, nombre, apellido, fecha, sexo, idMunicipio, idHistorial);
        //Agregar filas de la consulta de la base de datos
        ObservableList<datosPacientes> data = null;
        try {
            conexionBD sql = new conexionBD();
            Connection con = sql.conectarMySQL();
            String sentencia = "select p.idPaciente, p.Nombre, p.apellido, p.Fecha_de_Nacimiento, p.Sexo, m.Nombre, p.idHistorial from paciente p inner join municipio m on m.idMunicipio = p.idMunicipio";
            Statement stm = con.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(sentencia);
            int n = -1;
            int m = 0;
            if (rs != null) {
                while (rs.next()) {
                    //Agregar columna a los municipios
                    //Acá se agregan las filas a data para después añadirlos a la tabla
                    if (m == 0) {
                        data = FXCollections.observableArrayList(new datosPacientes(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getDate(4),rs.getString(5), rs.getString(6), rs.getInt(7)));
                        m++;
                    } else {
                        data.add(new datosPacientes(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getDate(4),rs.getString(5), rs.getString(6), rs.getInt(7)));
                        m++;
                    }
                }
                tblPacientes.setItems(data);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Sin datos");
                alert.setContentText("Aún no se han registrado datos");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Error en la BD");
            alert.setContentText("Comprobar errores");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();
            Label label = new Label("Detalles:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        }
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
        String apellido = txtApellido.getText();
        String genero;
        //Comprobaciones de que no estén vacios los datos
        int n = 0;
        if (apellido.equals("") || apellido.equals(null)) {
            n++;
        }
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
            rp.recibirDatos(nombre, apellido, fecha, genero, seleccion);
            cancelarIngresarPaciente();
        }

    }

    @FXML
    private void cancelarIngresarPaciente() {
        txtNombre.setText("");
        txtApellido.setText("");
        dtFecha.setValue(LocalDate.now());
        cbxMunicipios.getSelectionModel().select(0);
        cuadroMasculino.setSelected(false);
        cuadroFemenino.setSelected(false);
    }

    @FXML
    private void editarPaciente() {
        System.out.println(tblPacientes.getItems().get(tblPacientes.getSelectionModel().getSelectedIndex()));
    }

    //************************************************************************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
