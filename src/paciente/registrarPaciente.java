package paciente;

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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

public class registrarPaciente {

    public void recibirDatos(String nombre, String apellido, LocalDate fecha, String genero, int id, int telefono) {
        conexionBD sql = new conexionBD();
        Connection con = sql.conectarMySQL();
        int numberTran = 0;
        try {
            //Inicio de transacción
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
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
            String sentencia
                    = "insert into paciente(Nombre, Apellido ,Fecha_de_Nacimiento, Sexo, idMunicipio, telefono) values"
                    + "(\"" + nombre + "\",\"" + apellido + "\",\"" + fecha + "\",\"" + genero + "\"," + id + "," + telefono + ");";
            Statement stm = con.createStatement();
            int rs = stm.executeUpdate(sentencia);
            if (rs == 1) {
                //Mensaje de que se llenó correctamente y resetear valores
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Información");
                alert.setHeaderText("Creado");
                alert.setContentText("Usuario Creado Correctamente");
                alert.showAndWait();
                con.commit();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                String start = "[" + now + "] TRANSACTION No." + numberTran + " COMPLETED\n";
                Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                    String start = "[" + now + "] TRANSACTION No." + numberTran + " ABORTED\n";
                    Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException ex1) {
                    Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (SQLException ex1) {
                Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
        } catch (IOException ex) {
            Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recibirDatosEdicion(int clave, String nombre, String apellido, LocalDate fecha, String genero, int id, int telefono) {
        conexionBD sql = new conexionBD();
        Connection con = sql.conectarMySQL();
        int numberTran = 0;
        try {
            //Inicio de transacción
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
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
            String sentencia
                    = "update paciente  set Nombre = \"" + nombre + "\", apellido = \"" + apellido + "\", "
                    + "Fecha_de_Nacimiento = '" + fecha + "', Sexo = \"" + genero + "\", idMunicipio = " + id + " "
                    + ", telefono = " + telefono + " "
                    + "where idPaciente = " + clave + ";";
            Statement stm = con.createStatement();
            int rs = stm.executeUpdate(sentencia);
            if (rs == 1) {
                //Mensaje de que se llenó correctamente y resetear valores
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Operación correcta");
                alert.setHeaderText("Editado");
                alert.setContentText("Usuario editado correctamente");
                alert.showAndWait();
                con.commit();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                String start = "[" + now + "] TRANSACTION No." + numberTran + " COMPLETED\n";
                Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now(); //now = fecha actual
                    String start = "[" + now + "] TRANSACTION No." + numberTran + " ABORTED\n";
                    Files.write(Paths.get("log.txt"), start.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException ex1) {
                    Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (SQLException ex1) {
                Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex1);
            }

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
        } catch (IOException ex) {
            Logger.getLogger(registrarPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
