package cita;

import BD.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class Agregarcita {

    conexionBD conexion = new conexionBD();

    public void recibirdatos(String nombre, boolean reconsulta, String telefono, String Fecha, int idPaciente, boolean atendido, int costo) throws ParseException, SQLException {
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
            stmt.setInt(7, costo);
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
