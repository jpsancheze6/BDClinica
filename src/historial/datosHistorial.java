package historial;

import java.sql.Date;

/**
 *
 * @author izasj
 */
public class datosHistorial {

    private int idHistorial, idPaciente, edad;
    private Date fecha;
    private String padecimieno, historiaClinica, antecedentes, examenFisico;

    public datosHistorial(int idH, int idP, int edad, Date fecha, String pade, String hC, String antec, String eF) {
        this.idHistorial = idH;
        this.idPaciente = idP;
        this.edad = edad;
        this.fecha = fecha;
        this.padecimieno = pade;
        this.historiaClinica = hC;
        this.antecedentes = antec;
        this.examenFisico = eF;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPadecimieno() {
        return padecimieno;
    }

    public void setPadecimieno(String padecimieno) {
        this.padecimieno = padecimieno;
    }

    public String getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(String historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

}
