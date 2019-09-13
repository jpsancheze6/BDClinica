package cita;

import BD.conexionBD;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class Agregarcita {

    conexionBD conexion = new conexionBD();

    public boolean recibirdatos(String nombre, boolean reconsulta, String telefono, String Fecha, int idPaciente, boolean atendido, double costo) throws ParseException, SQLException {
        boolean bandera = false;
        Connection conn = null;
        conn = conexion.conectarMySQL();
        PreparedStatement stmt = null;
        //Fecha
        Timestamp s = Timestamp.valueOf(Fecha);
        //Haciendo la consulta
        try {
            String sql = "INSERT INTO Cita(Nombre,Reconsulta,Telefono,Fecha,idPaciente,Atendido,costo) VALUES(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setBoolean(2, reconsulta);
            stmt.setString(3, telefono);
            stmt.setTimestamp(4, s);
            stmt.setInt(5, idPaciente);
            stmt.setBoolean(6, atendido);
            stmt.setDouble(7, costo);
            int n = stmt.executeUpdate();
            if (n > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Información");
                alert.setHeaderText("Correcto");
                alert.setContentText("Cita ingresada correctamente");
                alert.showAndWait();
                conn.commit();
            }
            bandera = true;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Excepción");
            alert.setHeaderText("Error al ingresar");
            alert.setContentText("Revisar detalles:");
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
            bandera = false;
        }
        stmt.close();
        conn.close();
        return bandera;
    }
}
