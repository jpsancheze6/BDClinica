package bdclinica;

import BD.conexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                //Acceder al login
            } else if (a == 0) {
                System.out.println("contrasseña incorrecta");
            } else if (a == -1) {
                System.out.println("usuario incorrecto");
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
