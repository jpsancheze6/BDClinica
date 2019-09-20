package cita;

import BD.conexionBD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        //Inicio de transacción
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        conn.setAutoCommit(false);
        int numberTran = 0;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now(); //now = fecha actual
            //Lectura de el número de transacción
            BufferedReader reader = new BufferedReader(new FileReader("num.txt"));
            numberTran = reader.read();
            reader.close();
            //Escribir nuevo número de transacción
            BufferedWriter w = new BufferedWriter(new FileWriter("num.txt"));
            w.write(numberTran + 1);
            w.close();

            String start = "[" + now + "] START TRANSACTION No." + numberTran + "\n";
            Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        //Haciendo la consulta
        try {
            String sql = "INSERT INTO cita(Nombre,Reconsulta,Telefono,Fecha,idPaciente,Atendido,costo) VALUES(?,?,?,?,?,?,?)";
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                String start = "[" + now + "] TRANSACTION No." + numberTran + " COMPLETED\n";
                Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
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
            conn.rollback();
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                String start = "[" + now + "] TRANSACTION No." + numberTran + " ABORTED\n";
                Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(Agregarcita.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        stmt.close();
        conn.close();
        return bandera;
    }
}
