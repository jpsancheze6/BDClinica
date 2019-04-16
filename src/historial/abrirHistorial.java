package historial;

import BD.conexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author izasj
 */
public class abrirHistorial {
    conexionBD conBD = new conexionBD();
    
    
    public void ingresarDatos(int idH, int idP, int edad, Date fecha, String pade, String hC, String antec, String eF) {
        Connection con = null;
        con = conBD.conectarMySQL();
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.prepareStatement("select * from historial");
            rs = ps.executeQuery();
            while(rs.next()) {
                
            }
            
        }catch(Exception ex) {
            System.err.println("Error" + ex);
        }
    }
}