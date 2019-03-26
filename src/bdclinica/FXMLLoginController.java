package bdclinica;

import BD.conexionBD;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLLoginController implements Initializable {

    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField txtPassword;

    @FXML
    public void aceptar(ActionEvent e) throws SQLException {
        String usuario = txtUsuario.getText();
        String pass = txtPassword.getText();
        conexionBD sql = new conexionBD();
        Connection con = sql.conectarMySQL();
        String sentencia = "SELECT Iniciar('" + usuario + "' ,'" + pass + "');";
        Statement stm = con.createStatement();
        ResultSet rs;
        rs = stm.executeQuery(sentencia);
        while (rs.next()) {
            int a = rs.getInt(1);
            if (a == 1) {
                try {
                    conexionBD sql1 = new conexionBD();
                    Connection con1 = sql1.conectarMySQL();
                    String sentencia1 = "SELECT rol FROM usuario WHERE nombre = '" + usuario + "';";
                    Statement stm1 = con1.createStatement();
                    ResultSet rs1 = null;
                    rs1 = stm1.executeQuery(sentencia1);
                    while (rs1.next()) {
                        if (rs1.getString(1).equals("Administrador")) {
                            //Comprobar si es administrador 1->si, 0->no
                            //Escribir un arhcivo diciendo si es o no administrador
                            RandomAccessFile archivo = new RandomAccessFile("confi.txt", "rw");
                            archivo.write(1);
                            archivo.close();
                        }else{
                            RandomAccessFile archivo = new RandomAccessFile("confi.txt", "rw");
                            archivo.write(0);
                            archivo.close();
                        }
                    }

                    //Llamar a una nueva ventana
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    //Cerrar ventana actual
                    Stage actual = (Stage) txtPassword.getScene().getWindow();
                    actual.close();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Excepción");
                    alert.setHeaderText("Ha ocurrido un error");
                    alert.setContentText("No se ha podido abrir correctamente la aplicación");
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
            } else if (a == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Error al ingresar");
                alert.setContentText("La contraseña es incorrecta");
                alert.showAndWait();
            } else if (a == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText("Error al ingresaar");
                alert.setContentText("El usuario no existe");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void cancelar(ActionEvent e) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
