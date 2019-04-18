package BD;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class conexionBD {

    public String driver = "com.mysql.jdbc.Driver";
    public String base = "clinica";//<- Esto cambiarlo sin duda
    public String host = "localhost";//<- Cambiar esto
    public String puerto = "3306";//<- Posiblemente eso
    public String url = "jdbc:mysql://" + host + ":" + puerto + "/" + base
            + "?useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&"
            + "useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&"
            + "useSSL=false";
    public String username = "docuser";//<- Cambiar esto por el usuario
    public String password = "url.2019";//<- Y esto por la contraseña

    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Error en la BD");
            alert.setContentText("Comprobar errores");
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
        return conn;
    }
}
