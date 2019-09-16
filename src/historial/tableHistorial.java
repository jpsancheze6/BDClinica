package historial;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tableHistorial {

    private Date fecha;
    private SimpleIntegerProperty edad;
    private SimpleStringProperty padecimiento;
    private SimpleStringProperty medicamento;
    private SimpleStringProperty descrip;

    public tableHistorial(Date f, Integer ed, String pade, String medi, String descrip) {
        this.fecha = f;
        this.edad = new SimpleIntegerProperty(ed);
        this.padecimiento = new SimpleStringProperty(pade);
        this.medicamento = new SimpleStringProperty(medi);
        this.descrip = new SimpleStringProperty(descrip);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int edad) {
        this.edad = new SimpleIntegerProperty(edad);;
    }

    public String getPadecimiento() {
        return padecimiento.get();
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = new SimpleStringProperty(padecimiento);;
    }

    public String getMedicamento() {
        return medicamento.get();
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = new SimpleStringProperty(medicamento);;
    }

    public String getDescrip() {
        return descrip.get();
    }

    public void setDescrip(String descrip) {
        this.descrip = new SimpleStringProperty(descrip);;
    }

}
