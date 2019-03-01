package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    
    public String driver = "com.mysql.jdbc.Driver";
    public String base = "clinica";//<- Esto cambiarlo sin duda
    public String host = "127.0.0.1";//<- Cambiar esto
    public String puerto = "3306";//<- Posiblemente eso
    public String url = "jdbc:mysql://" + host + ":" + puerto + "/" + base
            + "?useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&"
            + "useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&"
            + "useSSL=false";
    public String username = "root";//<- Cambiar esto por el usuario
    public String password = "";//<- Y esto por la contraseña
    
    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}