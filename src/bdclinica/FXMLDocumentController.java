package bdclinica;

import java.net.URL;
import cita.paciente_tabla;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import cita.Agregarcita;
import java.text.ParseException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import BD.conexionBD;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import paciente.datosPacientes;
import paciente.registrarPaciente;
import cita.paciente_tablacita;
import cita.Modificar;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.scene.control.PasswordField;
import historial.tableHistorial;
import historial.tablePaci;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

public class FXMLDocumentController implements Initializable {

    int clave;

    @FXML
    private Pane paneAgregarPaciente, panePacientes, paneCitas, paneHistorial,
            paneReportes, paneConfiguracion, paneExtra, paneEditarPaciente, paneH,
            paneAgregarH, paneRIngresos;
    @FXML
    private javafx.scene.control.TextField nombre, telefono, costo, idPaciente, Hora;
    @FXML
    private DatePicker fecha, dtFecha, dtFechaEdicion;
    @FXML
    public TableView tblPacientes;
    @FXML
    private ObservableList<tablePaci> datosP = FXCollections.observableArrayList();
    @FXML
    private ObservableList<tableHistorial> datosHi = FXCollections.observableArrayList();
    @FXML
    public TableView<tablePaci> tablePA;
    @FXML
    public TableView<tableHistorial> tableHist;
    @FXML
    public TableColumn<tablePaci, String> NombrePaciente;
    @FXML
    public TableColumn<tablePaci, String> ApellidoPaciente;
    @FXML
    public TableColumn<tablePaci, Integer> IDPaciente;
    @FXML
    public TableColumn<tableHistorial, Date> fechaHist;
    @FXML
    public TableColumn<tableHistorial, Integer> edadHist;
    @FXML
    public TableColumn<tableHistorial, String> padeHist;
    @FXML
    public TableColumn<tableHistorial, String> mediHist;
    @FXML
    public TableColumn<tableHistorial, String> descripHist;
    @FXML
    private CheckBox reconsulta;
    @FXML
    private Button b;
    @FXML
    private CheckBox atendido;
    @FXML
    private TableView<paciente_tabla> tabla;
    @FXML
    public TableColumn<paciente_tabla, Integer> Id;
    @FXML
    public ComboBox cbxMunicipios, cbxMunicipiosEdicion;
    @FXML
    public TableColumn<paciente_tabla, String> Nombre;
    @FXML
    public TableColumn<paciente_tabla, String> Apellido;
    @FXML
    public CheckBox cuadroMasculino, cuadroFemenino, cuadroMasculinoEdicion, cuadroFemeninoEdicion;
    @FXML
    public TextField txtNombre, txtApellido, txtNombreEdicion, txtApellidoEdicion,
            txtN, txtHid, txtPac, txtMed, txtTelefono, txtTelefonoEdicion;
    @FXML
    public TextArea txtPade, txtDesc, txtAnte, txtHC, txtEF;

    private ObservableList<paciente_tabla> lista = FXCollections.observableArrayList();

    @FXML
    private TableView<paciente_tablacita> tabla2;
    public TableColumn<paciente_tablacita, Integer> Idcita;
    public TableColumn<paciente_tablacita, String> Nombrepaciente;
    public TableColumn<paciente_tablacita, String> Reconsultacita;
    public TableColumn<paciente_tablacita, String> Telefonocita;
    public TableColumn<paciente_tablacita, String> Fechacita;
    public TableColumn<paciente_tablacita, String> Atendidocita;
    public TableColumn<paciente_tablacita, String> costocita;
    private ObservableList<paciente_tablacita> lista2 = FXCollections.observableArrayList();

    @FXML
    private Button modificarcita, actualizar, agregar, cancelar;
    @FXML
    int idcita1 = 0;

    @FXML
    private TableView<paciente_tablacita> tablaconsulta;
    public TableColumn<paciente_tablacita, Integer> Idcitac;
    public TableColumn<paciente_tablacita, String> Nombrepacientec;
    public TableColumn<paciente_tablacita, String> Reconsultacitac;
    public TableColumn<paciente_tablacita, String> Telefonocitac;
    public TableColumn<paciente_tablacita, String> Fechacitac;
    public TableColumn<paciente_tablacita, String> Atendidocitac;
    public TableColumn<paciente_tablacita, String> costocitac;
    private ObservableList<paciente_tablacita> listaconsulta = FXCollections.observableArrayList();

    public void actualizardatos() {
        lista2.clear();
        Modificar mod = new Modificar();
        boolean atendido1 = false, reconsulta1 = false;
        String nombre1 = nombre.getText();
        String telefono1 = telefono.getText();
        String costo1 = costo.getText();
        int costo2 = Integer.parseInt(costo1);
        String id = idPaciente.getText();
        int idpaciente = Integer.parseInt(id);

        //Tomando los  valores de los checkbox
        boolean selected = reconsulta.isSelected();
        boolean selected1 = atendido.isSelected();
        if (selected == true) {
            reconsulta1 = true;
        }
        if (selected1 == true) {
            atendido1 = true;
        }
        mod.Actualizar(idcita1, nombre1, reconsulta1, telefono1, atendido1, costo2);
        nombre.clear();
        telefono.clear();
        costo.clear();
        idPaciente.clear();
        reconsulta.setSelected(false);
        atendido.setSelected(false);
        Hora.clear();
        actualizar.setDisable(true);
        modificarcita.setDisable(true);
        tabla2.setVisible(false);
        lista2.clear();
    }

    @FXML
    private void agregarPaciente(ActionEvent event) {
        paneAgregarPaciente.setVisible(true);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        paneEditarPaciente.setVisible(false);
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
        //Código extra desde acá
        //Conectar con la base de datos para cargar municipios
        conexionBD sql = new conexionBD();
        Connection con = sql.conectarMySQL();
        String sentencia = "select * from municipio";
        try {
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
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Error");
            alert.setContentText("Ha ocurrido un error, revise los detalles: ");
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

    // HISTORIAL ------------
    @FXML
    private void historial(ActionEvent event) {
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(true);
        panePacientes.setVisible(false);
        paneReportes.setVisible(false);
        txtN.setText(null);
        tablePA.getItems().clear();
        paneH.setVisible(false);
        paneAgregarH.setVisible(false);
        //Código extra desde acá
    }

    //tabla mostrar los pacientes en base a su apellido
    @FXML
    public void tablePA() throws SQLException {
        datosP = FXCollections.observableArrayList();
        Connection con = null;
        conexionBD conBD = new conexionBD();
        con = conBD.conectarMySQL();
        String no = null;
        no = txtN.getText();
        try {
            String selectSQL = "select idPaciente, Nombre, apellido from paciente where apellido LIKE '%" + no + "%'";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            while (rs.next()) {
                int idP = rs.getInt("idPaciente");
                String nomb = rs.getString("Nombre");
                String apell = rs.getString("Apellido");
                tablePaci tp = new tablePaci(idP, nomb, apell);
                datosP.add(tp);
            }
            //agrega a la tabla
            IDPaciente.setCellValueFactory(new PropertyValueFactory<>("Id"));
            NombrePaciente.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            ApellidoPaciente.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
            tablePA.setItems(datosP);
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

    //Boton para agregar una consulta al historial del paciente seleccionado (btnAC)
    @FXML
    public void abrirpaneH(int idHist, String no, String ap) {
        datosP = FXCollections.observableArrayList();
        Connection con = null;
        conexionBD conBD = new conexionBD();
        con = conBD.conectarMySQL();
        int idh = idHist;
        try {
            String selectSQL = "select r.Fecha, r.Edad, r.Padecimiento, r.medicamento, r.Descripccion from historial h inner join receta r on h.idHistorial = r.idHistorial  where h.idHistorial = " + idh + " order by r.fecha;";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            while (rs.next()) {
                Date fe = rs.getDate(1);
                int eda = rs.getInt(2);
                String pa = rs.getString(3);
                String me = rs.getString(4);
                String de = rs.getString(5);
                tableHistorial tp = new tableHistorial(fe, eda, pa, me, de);
                datosHi.add(tp);
            }
            //agrega a la tabla
            fechaHist.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            edadHist.setCellValueFactory(new PropertyValueFactory<>("edad"));
            padeHist.setCellValueFactory(new PropertyValueFactory<>("padecimiento"));
            mediHist.setCellValueFactory(new PropertyValueFactory<>("medicamento"));
            descripHist.setCellValueFactory(new PropertyValueFactory<>("descrip"));
            tableHist.setItems(datosHi);
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

    //abrir el historial del paciente seleccionado
    @FXML
    public void seleccionarFila(ActionEvent event) {
        txtN.setText(null);
        tablePaci pa = tablePA.getSelectionModel().getSelectedItem();
        int h = pa.getId();
        String no = pa.getNombre();
        String ap = pa.getApellido();
        abrirpaneH(h, no, ap);
        paneHistorial.setVisible(false);
        paneH.setVisible(true);
        txtHid.setText(String.valueOf(h));
        txtPac.setText(no + " " + ap);
    }

    //Boton para agregar una consulta al historial del paciente seleccionado (btnAC)
    @FXML
    public void agregarConsulta(ActionEvent event) {
        String idh = txtHid.getText();
        paneH.setVisible(false);
        txtPade.setText(null);
        txtMed.setText(null);
        txtDesc.setText(null);
        txtAnte.setText(null);
        txtHC.setText(null);
        txtEF.setText(null);
        paneAgregarH.setVisible(true);
        Connection con = null;
        conexionBD conBD = new conexionBD();
        con = conBD.conectarMySQL();
        try {
            String selectSQL = "select Verificacion from historial where idHistorial = " + idh + ";";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            int veri = 0;
            while (rs.next()) {
                veri = rs.getInt(1);
            }
            if (veri == 0) {
                txtPade.setDisable(false);
                txtMed.setDisable(false);
                txtDesc.setDisable(false);
                txtAnte.setDisable(false);
                txtHC.setDisable(false);
                txtEF.setDisable(false);

            } else {
                txtAnte.setDisable(true);
                txtHC.setDisable(true);
                txtEF.setDisable(true);
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
    public void addHistorial(String idh) {
        Connection con = null;
        conexionBD conBD = new conexionBD();
        con = conBD.conectarMySQL();
        int idh2 = Integer.parseInt(idh);

        try {
            String selectSQL = "select Verificacion from historial where idHistorial = " + idh2 + ";";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            int veri = 0;
            while (rs.next()) {
                veri = rs.getInt(1);
            }
            if (veri == 0) {
                String pad = txtPade.getText();
                String me = txtMed.getText();
                String des = txtDesc.getText();
                String ante = txtAnte.getText();
                String hCli = txtHC.getText();
                String eFisi = txtEF.getText();
                String selectSQL2 = "update paciente set idHistorial = " + idh2 + " where idPaciente = " + idh2 + ";";
                Statement stm = con.createStatement();
                stm.executeUpdate(selectSQL2);
                String selectSQL3 = "call primerIngreso(" + idh2 + ", \"" + pad + "\", \"" + me + "\", \"" + des + "\", \"" + hCli + "\", \"" + ante + "\", \"" + eFisi + "\");";
                stm.executeUpdate(selectSQL3);
            } else {
                String pad = txtPade.getText();
                String me = txtMed.getText();
                String des = txtDesc.getText();
                Statement stm = con.createStatement();
                String selectSQL3 = "call Ingresos(" + idh2 + ", \"" + pad + "\", \"" + me + "\", \"" + des + "\");";
                stm.executeUpdate(selectSQL3);
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

    //Boton para regresar al pane historial (btnReg)
    @FXML
    private void handleButtonAction4(ActionEvent event) {
        paneH.setVisible(false);
        paneHistorial.setVisible(true);
        tableHist.getItems().clear();
    }

    //Boton para agregar datos de una consulta al historial del paciente (btnAgregar)
    @FXML
    private void handleButtonAction5(ActionEvent event) {
        String idh = txtHid.getText();
        addHistorial(idh);
        paneH.setVisible(false);
        paneHistorial.setVisible(true);
        paneAgregarH.setVisible(false);
        tableHist.getItems().clear();
    }
    // HISTORIAL---------

    @FXML
    private void pacientes() {
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
        TableColumn telefono = new TableColumn("Telefono");
        telefono.setCellValueFactory(new PropertyValueFactory<datosPacientes, Integer>("telefono"));
        
        tblPacientes.getColumns().addAll(id, nombre, apellido, fecha, sexo, idMunicipio, telefono);
        //Agregar filas de la consulta de la base de datos
        ObservableList<datosPacientes> data = null;
        try {
            conexionBD sql = new conexionBD();
            Connection con = sql.conectarMySQL();
            String sentencia = "select p.idPaciente, p.Nombre, p.apellido, p.Fecha_de_Nacimiento, p.Sexo, m.Nombre, p.idHistorial, p.telefono from paciente p inner join municipio m on m.idMunicipio = p.idMunicipio";
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
                                rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
                        m++;
                    } else {
                        data.add(new datosPacientes(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
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
    private void agregarcita() throws SQLException, ClassNotFoundException, ParseException {
        Agregarcita citas = new Agregarcita();
        if ((nombre.getText().equals("")) || (telefono.getText().equals("")) || (costo.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error");
            alert.setContentText("Faltan campos por llenar");

            alert.showAndWait();
        } else {

            boolean atendido1 = false, reconsulta1 = false;
            String nombre1 = nombre.getText();
            String telefono1 = telefono.getText();
            String costo1 = costo.getText();
            int costo2 = Integer.parseInt(costo1);
            String id = idPaciente.getText();
            int idpaciente = Integer.parseInt(id);
            //tomando la fecha y hora
            LocalDate localDate = fecha.getValue();

            DateTimeFormatter fecha1 = DateTimeFormatter.ISO_LOCAL_DATE;
            String fecha2 = (localDate).format(fecha1);
            String hora = Hora.getText();
            fecha2 = fecha2 + " " + hora;

            //Tomando los  valores de los checkbox
            boolean selected = reconsulta.isSelected();
            boolean selected1 = atendido.isSelected();
            if (selected == true) {
                reconsulta1 = true;
            }
            if (selected1 == true) {
                atendido1 = true;
            }
            nombre.clear();
            telefono.clear();
            costo.clear();
            idPaciente.clear();
            reconsulta.setSelected(false);
            atendido.setSelected(false);
            Hora.clear();
            //agregar.setDisable(true);
            lista.clear();
            lista2.clear();
            //enviando al metodo
            citas.recibirdatos(nombre1, reconsulta1, telefono1, fecha2, idpaciente, atendido1, costo2);
        }
    }

    @FXML
    private void cancelarprocesos() {
        tabla.setVisible(false);
        tabla2.setVisible(false);
        actualizar.setDisable(true);
        modificarcita.setDisable(true);
        agregar.setDisable(false);
    }

    @FXML
    public void Buscar() throws SQLException {
        tabla();
        agregar.setDisable(false);
    }

    @FXML
    public void tabla() throws SQLException {
        lista = FXCollections.observableArrayList();
        String nombre1 = nombre.getText();
        //conectando con base de datos
        Connection conn = null;
        conexionBD conexion = new conexionBD();
        conn = conexion.conectarMySQL();
        PreparedStatement stmt = null;
        //Obteniendo pacientes con mismo nombre
        try {
            //Haciendo la consulta
            String selectSQL = "SELECT idPaciente, Nombre, Apellido FROM paciente WHERE Nombre LIKE '%" + nombre1 + "%'";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            //ciclo para agregar todos los pacientes con el nombre a la lista
            while (rs.next()) {
                int id1 = rs.getInt(1);
                String Nombre1 = rs.getString(2);
                String Apellido = rs.getString(3);
                paciente_tabla listap = new paciente_tabla(id1, Nombre1, Apellido);
                lista.add(listap);
            }
            //agrega a la tabla
            Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Apellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
            tabla.setItems(lista);
            tabla.setVisible(true);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Error");
            alert.setContentText("Revise los errores para continuar");
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
    public void seleccionado() {
        //Obtiene el id del paciente seleccionado
        try {
            paciente_tabla persona = tabla.getSelectionModel().getSelectedItem();
            int id1 = persona.getId();
            String id2 = "";
            id2 = id2 + id1;
            //Escribe el id en el textfield
            idPaciente.setText(id2);
            nombre.setText(persona.getNombre());
            //Habilita 
            telefono.setDisable(false);
            costo.setDisable(false);
            modificarcita.setDisable(false);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Posiblemente hace falta seleccionar un registro");
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
    public void tabla2() throws SQLException {
        lista2.clear();
        lista.clear();
        tabla2.setVisible(true);
        tabla.setVisible(false);
        String id = idPaciente.getText();
        if (id.equals("")) {

        } else {
            int idpaciente = Integer.parseInt(id);

            //conectando con base de datos
            Connection conn = null;
            conexionBD conexion = new conexionBD();
            conn = conexion.conectarMySQL();
            //Obteniendo pacientes con mismo nombre
            try {

                //Haciendo la consulta
                String selectSQL = "SELECT idCita,Nombre,Reconsulta, Telefono, Fecha,Atendido,costo FROM cita WHERE idPaciente =" + id;
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                ResultSet rs = preparedStatement.executeQuery(selectSQL);
                //ciclo para agregar todos los pacientes con el nombre a la lista

                while (rs.next()) {

                    int id1 = rs.getInt(1);
                    String Nombre11 = rs.getString(2);
                    boolean Reconsulta1 = rs.getBoolean(3);
                    String Telefono1 = rs.getString(4);
                    //  String Fecha1= rs.getString(5);
                    Timestamp Fecha1 = rs.getTimestamp(5);
                    boolean Atendido1 = rs.getBoolean(6);
                    int costo1 = rs.getInt(7);
                    String fecha2 = new SimpleDateFormat("yyyy-MM-dd  hh:ss:mm").format(Fecha1);
                    paciente_tablacita listap = new paciente_tablacita(id1, Nombre11, Reconsulta1, Telefono1, fecha2, Atendido1, costo1);
                    lista2.add(listap);
                }
                rs.close();
                conn.close();
                //agrega a la tabla
                Idcita.setCellValueFactory(new PropertyValueFactory<>("Id"));
                Nombrepaciente.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
                Reconsultacita.setCellValueFactory(new PropertyValueFactory<>("Reconsulta"));
                Telefonocita.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
                Fechacita.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
                Atendidocita.setCellValueFactory(new PropertyValueFactory<>("Atendido"));
                costocita.setCellValueFactory(new PropertyValueFactory<>("Costo"));
                tabla2.setItems(lista2);
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Excepción");
                alert.setHeaderText("Ocurrió un error");
                alert.setContentText("Revisar errores para continuar");
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

    }

    @FXML
    public void tabla3() throws SQLException {
        listaconsulta.clear();

        try {
            //conectando con base de datos
            Connection conn = null;
            conexionBD conexion = new conexionBD();
            conn = conexion.conectarMySQL();
            //Haciendo la consulta
            String selectSQL = "SELECT idCita,Nombre,Reconsulta, Telefono, Fecha,Atendido,costo FROM cita";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            //ciclo para agregar todos los pacientes con el nombre a la lista

            while (rs.next()) {

                int id1 = rs.getInt(1);
                String Nombre11 = rs.getString(2);
                boolean Reconsulta1 = rs.getBoolean(3);
                String Telefono1 = rs.getString(4);
                //  String Fecha1= rs.getString(5);
                Timestamp Fecha1 = rs.getTimestamp(5);
                boolean Atendido1 = rs.getBoolean(6);
                int costo1 = rs.getInt(7);
                String fecha2 = new SimpleDateFormat("yyyy-MM-dd  hh:ss:mm").format(Fecha1);
                paciente_tablacita listap = new paciente_tablacita(id1, Nombre11, Reconsulta1, Telefono1, fecha2, Atendido1, costo1);
                listaconsulta.add(listap);
            }
            rs.close();
            conn.close();
            //agrega a la tabla
            Idcitac.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Nombrepacientec.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Reconsultacitac.setCellValueFactory(new PropertyValueFactory<>("Reconsulta"));
            Telefonocitac.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            Fechacitac.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            Atendidocitac.setCellValueFactory(new PropertyValueFactory<>("Atendido"));
            costocitac.setCellValueFactory(new PropertyValueFactory<>("Costo"));
            tablaconsulta.setItems(listaconsulta);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrió un error");
            alert.setContentText("Revisar errores para continuar");
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
    public void seleccionadotabla2() {
        //Obtiene el id del paciente seleccionado
        try {
            paciente_tablacita persona = tabla2.getSelectionModel().getSelectedItem();
            idcita1 = persona.getId();
            agregar.setDisable(true);
            String fecha1 = persona.getFecha();
            //ciclo para separar fecha y hora
            int largo = fecha1.length();
            //usando una bandera
            String fechalocal = "";
            String horalocal = "";
            boolean bandera = false;

            for (int x = 0; x < largo; x++) {
                char c = fecha1.charAt(x);

                if (c == ' ') {
                    c = fecha1.charAt(x + 2);
                    x = x + 2;

                    for (int x1 = x; x1 < largo; x1++) {
                        c = fecha1.charAt(x1);
                        horalocal = horalocal + c;
                    }
                    x = largo;
                } else {
                    if (c == '-') {
                        c = '/';
                        fechalocal = fechalocal + c;
                    } else {
                        fechalocal = fechalocal + c;
                    }
                }
            }
            //dando formato a fecha
            LocalDate localDate1 = LocalDate.parse(fechalocal, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            fecha.setValue(localDate1);
            String id2 = "";
            nombre.setText(persona.getNombre());
            //Habilita 
            telefono.setDisable(false);
            Hora.setText(horalocal);
            costo.setDisable(false);
            actualizar.setDisable(false);
            boolean boleano = persona.getReconsulta();
            reconsulta.setSelected(boleano);
            telefono.setText(persona.getTelefono());
            boleano = persona.getAtendido();
            atendido.setSelected(boleano);
            costo.setText(id2 + persona.getCosto());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Posiblemente hace falta seleccionar un registro");
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
    Label g;
    Button citapornombre;

    @FXML
    public void tablacitas(String campo, String valor) throws SQLException {
        listaconsulta.clear();

        try {
            //conectando con base de datos
            Connection conn = null;
            conexionBD conexion = new conexionBD();
            conn = conexion.conectarMySQL();
            //Haciendo la consulta
            String selectSQL = "SELECT idCita,Nombre,Reconsulta, Telefono, Fecha, Atendido,costo FROM cita Where " + campo + " LIKE '%" + valor + "%'";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            //ciclo para agregar todos los pacientes con el nombre a la lista

            while (rs.next()) {

                int id1 = rs.getInt(1);
                String Nombre11 = rs.getString(2);
                boolean Reconsulta1 = rs.getBoolean(3);
                String Telefono1 = rs.getString(4);
                //  String Fecha1= rs.getString(5);
                Timestamp Fecha1 = rs.getTimestamp(5);
                boolean Atendido1 = rs.getBoolean(6);
                int costo1 = rs.getInt(7);
                String fecha2 = new SimpleDateFormat("yyyy-MM-dd  hh:ss:mm").format(Fecha1);
                paciente_tablacita listap = new paciente_tablacita(id1, Nombre11, Reconsulta1, Telefono1, fecha2, Atendido1, costo1);
                listaconsulta.add(listap);
            }
            rs.close();
            conn.close();
            //agrega a la tabla
            Idcitac.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Nombrepacientec.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Reconsultacitac.setCellValueFactory(new PropertyValueFactory<>("Reconsulta"));
            Telefonocitac.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            Fechacitac.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            Atendidocitac.setCellValueFactory(new PropertyValueFactory<>("Atendido"));
            costocitac.setCellValueFactory(new PropertyValueFactory<>("Costo"));
            tablaconsulta.setItems(listaconsulta);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrió un error");
            alert.setContentText("Revisar errores para continuar");
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

    public void tablacitasfecha(String f1, String f2) throws SQLException {
        listaconsulta.clear();

        try {
            //conectando con base de datos
            Connection conn = null;
            conexionBD conexion = new conexionBD();
            conn = conexion.conectarMySQL();
            //Haciendo la consulta
            String selectSQL = "SELECT idCita,Nombre,Reconsulta, Telefono, Fecha, Atendido,costo FROM cita Where Fecha between " + f1 + " and " + f2;
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            //ciclo para agregar todos los pacientes con el nombre a la lista

            while (rs.next()) {

                int id1 = rs.getInt(1);
                String Nombre11 = rs.getString(2);
                boolean Reconsulta1 = rs.getBoolean(3);
                String Telefono1 = rs.getString(4);
                //  String Fecha1= rs.getString(5);
                Timestamp Fecha1 = rs.getTimestamp(5);
                boolean Atendido1 = rs.getBoolean(6);
                int costo1 = rs.getInt(7);
                String fecha2 = new SimpleDateFormat("yyyy-MM-dd  hh:ss:mm").format(Fecha1);
                paciente_tablacita listap = new paciente_tablacita(id1, Nombre11, Reconsulta1, Telefono1, fecha2, Atendido1, costo1);
                listaconsulta.add(listap);
            }
            rs.close();
            conn.close();
            //agrega a la tabla
            Idcitac.setCellValueFactory(new PropertyValueFactory<>("Id"));
            Nombrepacientec.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Reconsultacitac.setCellValueFactory(new PropertyValueFactory<>("Reconsulta"));
            Telefonocitac.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            Fechacitac.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            Atendidocitac.setCellValueFactory(new PropertyValueFactory<>("Atendido"));
            costocitac.setCellValueFactory(new PropertyValueFactory<>("Costo"));
            tablaconsulta.setItems(listaconsulta);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrió un error");
            alert.setContentText("Revisar errores para continuar");
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
    public void consultacitapornombre() throws SQLException {
        String nombreconsulta = mensaje1();
        tablacitas("Nombre", nombreconsulta);
    }

    public String mensaje1() {
        TextInputDialog dialog = new TextInputDialog("Ingreso de nombre a buscar");
        dialog.setTitle("CITAS");
        dialog.setHeaderText("Ingrese nombre de paciente:");
        dialog.setContentText("Nombre:");
        Optional<String> respuesta = dialog.showAndWait();
        respuesta.ifPresent(nombre -> {
            g.setText(nombre);
        });
        String r = g.getText();
        return (r);
    }

    @FXML
    DatePicker fechac;

    @FXML
    public void fechacon() {
        fechac.setDisable(false);

    }

    @FXML
    public void consultaporfecha() throws SQLException {

        LocalDate fechacita = fechac.getValue();
        DateTimeFormatter fecha0 = DateTimeFormatter.ISO_LOCAL_DATE;
        String fecha1 = (fechacita).format(fecha0);
        String hora = "00:00:00";
        String hora2 = "23:59:00";
        String fecha2 = "'" + fecha1 + " " + hora + "'";
        String fecha3 = "'" + fecha1 + " " + hora2 + "'";
        tablacitasfecha(fecha2, fecha3);
        fecha.setDisable(true);

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
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPass1, txtPass2;
    @FXML
    private CheckBox cbxAdministrador, cbxRegular;

    @FXML
    private void configuracion(ActionEvent event) throws FileNotFoundException, IOException {
        RandomAccessFile archivo = new RandomAccessFile("confi.txt", "rw");
        byte c = archivo.readByte();
        archivo.close();
        if (c == 1) {
            paneAgregarPaciente.setVisible(false);
            paneCitas.setVisible(false);
            paneConfiguracion.setVisible(true);
            paneExtra.setVisible(false);
            paneHistorial.setVisible(false);
            panePacientes.setVisible(false);
            paneReportes.setVisible(false);
            //Código extra desde acá
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("No se puede ingresar");
            alert.setContentText("El usuario ingresado no es administrador");
            alert.showAndWait();
        }

    }

    @FXML
    private void crearUsuario() throws SQLException {
        String usuario = txtUsuario.getText();
        String pass1 = txtPass1.getText();
        String pass2 = txtPass2.getText();
        String rol = "";
        boolean admin = cbxAdministrador.isSelected();
        boolean regular = cbxRegular.isSelected();
        int n = 0;
        if (pass1.equals(pass2)) {
            if (admin == true || regular == true) {
                if (admin == true) {
                    rol = "Administrador";
                } else if (regular == true) {
                    rol = "Regular";
                }
                if (usuario.equals(null) || usuario.equals("")) {
                    //El usuario no se ingresó
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Error");
                    alert.setHeaderText("Datos incompletos");
                    alert.setContentText("No se ingresó el usuario");
                    alert.showAndWait();
                } else {
                    //Todos los datos se ingresaron correctamente
                    //Ingresar en la base de datos
                    conexionBD sql1 = new conexionBD();
                    Connection con1 = sql1.conectarMySQL();
                    String sentencia1 = "SELECT IngresarUsuario('" + usuario + "','" + pass1 + "','" + rol + "');";
                    Statement stm1 = con1.createStatement();
                    ResultSet rs1 = null;
                    rs1 = stm1.executeQuery(sentencia1);
                    while (rs1.next()) {
                        int a = rs1.getInt(1);
                        if (a == 1) {
                            //usuario creado correctamente
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setTitle("Información");
                            alert.setHeaderText("Correcto");
                            alert.setContentText("Usuario creado correctamente");
                            alert.showAndWait();
                            limpiarConfiguracion();
                        } else if (a == 0) {
                            //usuario no creado
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setTitle("Error");
                            alert.setHeaderText("Incorrecto");
                            alert.setContentText("Usuario no creado, \n"
                                    + "puede ser que ya exista un usuario con ese nombre");
                            alert.showAndWait();
                        }
                    }
                }
            } else {
                //No se selecciono ningún tipo de usuario
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText("Falta tipo de usuario");
                alert.setContentText("No se ha seleccionado ningún tipo de usuario");
                alert.showAndWait();
            }
        } else {
            //Contraseñas incorrectas
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("Error de contraseñas");
            alert.setContentText("Las contraseñas no coinciden");
            alert.showAndWait();
        }
    }

    private void limpiarConfiguracion() {
        txtUsuario.setText("");
        txtPass1.setText("");
        txtPass2.setText("");
        cbxAdministrador.setSelected(false);
        cbxRegular.setSelected(false);
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

    @FXML
    private void seleccionarAdministrador() {
        cbxRegular.setSelected(false);
        cbxAdministrador.setSelected(true);
    }

    @FXML
    private void seleccionarRegular() {
        cbxRegular.setSelected(true);
        cbxAdministrador.setSelected(false);
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
        cuadroFemeninoEdicion.setSelected(true);
        cuadroMasculinoEdicion.setSelected(false);
    }

    @FXML
    private void seleccionarMasculinoEdicion() {
        cuadroFemeninoEdicion.setSelected(false);
        cuadroMasculinoEdicion.setSelected(true);
    }

    @FXML
    private void seleccionarFemeninoEdicion() {
        cuadroFemeninoEdicion.setSelected(true);
        cuadroMasculinoEdicion.setSelected(false);
    }

    @FXML
    private void cancelarEdicion() {
        paneEditarPaciente.setVisible(false);
        pacientes();
    }

    @FXML
    private void guardarUsuario() {
        String nombre = txtNombre.getText();
        LocalDate fecha = dtFecha.getValue();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String genero;
        //Comprobaciones de que no estén vacios los datos
        int n = 0;
        if (telefono.equals("") || telefono.equals(null)) {
            n++;
        }
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
            //Convertir telefono a int
            telefono.replaceAll(" ", "");
            try {
                int tel = Integer.parseInt(telefono);
                //Mandar a registrarPaciente.java
                registrarPaciente rp = new registrarPaciente();
                rp.recibirDatos(nombre, apellido, fecha, genero, seleccion, tel);
                cancelarIngresarPaciente();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Excepción");
                alert.setHeaderText("Se produjo un error al analizar los datos");
                alert.setContentText("Revise errores, (comprobar número de teléfono)");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
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
    }

    @FXML
    private void guardarUsuarioEditado() {
        String nombre = txtNombreEdicion.getText();
        LocalDate fecha = dtFechaEdicion.getValue();
        String apellido = txtApellidoEdicion.getText();
        String telefono = txtTelefonoEdicion.getText();
        String genero;
        //Comprobaciones de que no estén vacios los datos
        int n = 0;
        if (telefono.equals("") || telefono.equals(null)) {
            n++;
        }
        if (apellido.equals("") || apellido.equals(null)) {
            n++;
        }
        if (nombre.equals("") || nombre.equals(null)) {
            n++;
        }
        if (fecha.equals(LocalDate.now())) {
            n++;
        }
        if (cuadroFemeninoEdicion.isSelected() == false) {
            if (cuadroMasculinoEdicion.isSelected() == false) {
                n++;
            }
        }
        if (cuadroMasculinoEdicion.isSelected() == false) {
            if (cuadroFemeninoEdicion.isSelected() == false) {
                n++;
            }
        }
        if (cuadroMasculinoEdicion.isSelected() == true) {
            genero = "M";
        } else {
            genero = "F";
        }
        int seleccion = cbxMunicipiosEdicion.getSelectionModel().getSelectedIndex();
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
            //Convertir telefono a int
            telefono.replaceAll(" ", "");
            try {
                int tel = Integer.parseInt(telefono);
                //Mandar a registrarPaciente.java
                registrarPaciente rp = new registrarPaciente();
                rp.recibirDatosEdicion(clave, nombre, apellido, fecha, genero, seleccion, tel);
                paneEditarPaciente.setVisible(false);
                panePacientes.setVisible(true);
                cancelarIngresarPaciente();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Excepción");
                alert.setHeaderText("Se produjo un error al analizar los datos");
                alert.setContentText("Revise errores, (comprobar número de teléfono)");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
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
    }

    @FXML
    private void cancelarIngresarPaciente() {
        txtNombre.setText("");
        txtApellido.setText("");
        dtFecha.setValue(LocalDate.now());
        cbxMunicipios.getSelectionModel().select(0);
        cuadroMasculino.setSelected(false);
        cuadroFemenino.setSelected(false);

        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneExtra.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(true);
        paneReportes.setVisible(false);
    }

    @FXML
    private void editarPaciente() {
        try {
            datosPacientes a = (datosPacientes) tblPacientes.getSelectionModel().getSelectedItem();
            int id = a.getId();
            String nombre = a.getNombre();
            String apellido = a.getApellido();
            Date fecha = a.getFecha_de_Nacimiento();
            String sexo = a.getSexo();
            String municipio = a.getIdMunicipio();
            int historial = a.getIdHistorial();
            int telefono = a.getTelefono();
            
            //cargar listado de municipios
            conexionBD sql = new conexionBD();
            Connection con = sql.conectarMySQL();
            String sentencia = "select * from municipio";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sentencia);
            ObservableList<String> municipios = FXCollections.observableArrayList();
            municipios.add("-- Seleccione Municipio --");
            int n = -1;
            if (rs != null) {
                while (rs.next()) {
                    municipios.add(rs.getString(2));
                }
                cbxMunicipiosEdicion.setItems(municipios);
            } else {
                System.out.println("No hay datos");
            }
            //Colocar datos
            panePacientes.setVisible(false);
            paneEditarPaciente.setVisible(true);
            txtNombreEdicion.setText(nombre);
            txtApellidoEdicion.setText(apellido);
            txtTelefonoEdicion.setText("" + telefono);
            if (sexo.equals("M") || sexo.equals("m")) {
                cuadroMasculinoEdicion.setSelected(true);
            } else {
                cuadroFemeninoEdicion.setSelected(true);
            }
            cbxMunicipiosEdicion.getSelectionModel().select(municipio);
            //Fecha de nacimiento
            dtFechaEdicion.setValue(fecha.toLocalDate());
            this.clave = id;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Se produjo un error al registrar el usuario");
            alert.setContentText("Revise errores, (posiblemente no se selecciono un registro)");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
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

    //************************************************************************//
    //Panel de reportes de ingresos
    @FXML
    private void reporteIngresos() {
        paneAgregarH.setVisible(false);
        paneAgregarPaciente.setVisible(false);
        paneCitas.setVisible(false);
        paneConfiguracion.setVisible(false);
        paneEditarPaciente.setVisible(false);
        paneExtra.setVisible(false);
        paneH.setVisible(false);
        paneHistorial.setVisible(false);
        panePacientes.setVisible(false);
        paneRIngresos.setVisible(true);
        paneReportes.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
