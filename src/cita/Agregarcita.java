/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cita;

import BD.conexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author tito88
 */
public class Agregarcita {

    conexionBD conexion = new conexionBD();

    public void recibirdatos(String nombre, boolean reconsulta, String telefono,Date Fecha, int idPaciente,boolean atendido, int costo) throws ParseException, SQLException {
        Connection conn = null;
        conn = conexion.conectarMySQL();
        PreparedStatement stmt = null;
        //fecha solo para prueba
         Calendar calendar = Calendar.getInstance();
    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
 
    //Haciendo la consulta
        try {
           
            String sql = "INSERT INTO Cita(Nombre,Reconsulta,Telefono,Fecha,idPaciente,Atendido,costo) VALUES(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setBoolean(2, reconsulta);
            stmt.setString(3, telefono);
             stmt.setTimestamp(4, ourJavaTimestampObject);
            //stmt.setDate(4, Fecha);
            stmt.setInt(5, idPaciente);
            stmt.setBoolean(6, atendido);
            stmt.setInt(7,costo);
            int n = stmt.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Incertados");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
        }

        stmt.close();
        conn.close();

    }
    
}
