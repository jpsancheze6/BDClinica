/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cita;

import BD.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author tito88
 */
public class Modificar {

    public void Actualizar(int idcita, String nombre1, boolean reconsulta1, String telefono1, boolean atendido1, int costo1) {

        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar los datos actuales?");

        if (confirmar == JOptionPane.YES_OPTION) {
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
                    JOptionPane.showMessageDialog(null, "Datos de cita actualizados");
                }

                prest.close();
                conn.close();

            } catch (SQLException e) {

            }

        }

    }

}
