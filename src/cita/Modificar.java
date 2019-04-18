package cita;

import BD.conexionBD;
import java.awt.Robot;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class Modificar {

    public void Actualizar(int idcita, String nombre1, boolean reconsulta1, String telefono1, boolean atendido1, int costo1) {

        if (showConfirm("", "¿Desea modificar los datos actuales?", "Si", "No", "Cancelar").equals("Si")) {
            Connection conn = null;
            try {
                conexionBD conexion = new conexionBD();
                conn = conexion.conectarMySQL();
                //creando la consulta
                String Ssql = "UPDATE cita SET Nombre=?, Reconsulta=?, Telefono=?, Atendido=?, Costo=?"
                        + " WHERE idCita=?";
                PreparedStatement prest = null;
                prest = conn.prepareStatement(Ssql);
                //poniendo valores
                prest.setString(1, nombre1);
                prest.setBoolean(2, reconsulta1);
                prest.setString(3, telefono1);
                prest.setBoolean(4, atendido1);
                prest.setInt(5, costo1);
                prest.setInt(6, idcita);
                int n = prest.executeUpdate();
                if (n > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Datos actualizados correctamente");
                    alert.showAndWait();
                }
                prest.close();
                conn.close();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Excepción");
                alert.setHeaderText("Ha ocurrido algún error");
                alert.setContentText("Revise los detalles");

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
    public static final String YES = "Sí";
    public static final String NO = "No";
    public static final String OK = "OK";
    public static final String CANCEL = "Cancelar";

    public static String showConfirm(String title, String message, String... options) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Seleccione una opción");
        alert.setHeaderText(title);
        alert.setContentText(message);
        //To make enter key press the actual focused button, not the first one. Just like pressing "space".
        alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                try {
                    Robot r = new Robot();
                    r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
                    r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (options == null || options.length == 0) {
            options = new String[]{OK, CANCEL};
        }
        List<ButtonType> buttons = new ArrayList<>();
        for (String option : options) {
            buttons.add(new ButtonType(option));
        }
        alert.getButtonTypes().setAll(buttons);
        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent()) {
            return CANCEL;
        } else {
            return result.get().getText();
        }
    }
}
