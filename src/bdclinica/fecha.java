package bdclinica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class fecha {
    public String fecha() {
        Date fecha = new Date();
        String strDateFormat = "dd-MMM-yyyy";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        return objSDF.format(fecha);
    }
}