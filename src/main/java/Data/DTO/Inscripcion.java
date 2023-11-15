package Data.DTO;

import Data.Horario;
import Data.TipoInscripcion;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Inscripcion class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class Inscripcion  implements Serializable {
    /*Attributes*/
    protected int idParticipante;
    protected int idCampamento;
    protected LocalDate fechaInscripcion;
    protected float precio;
    protected TipoInscripcion tipoInscripcion;

    protected Horario horario;
    /**
     *Empty(default) constructor
    */
    public Inscripcion() {
    }

    /**
     * Parametrized constructor
     * @param idParticipante
     * @param idCampamento
     * @param fechaInscripcion
     * @param precio
     * @param tipoInscripcion
     * @param horario
     */
    public Inscripcion(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, TipoInscripcion tipoInscripcion, Horario horario) {
        this.idParticipante = idParticipante;
        this.idCampamento = idCampamento;
        this.fechaInscripcion = fechaInscripcion;
        this.precio = precio;
        this.tipoInscripcion = tipoInscripcion;
        this.horario = horario;
    }
    /*Getters and Setters*/
    /**
     * @return id del participante
     */
    public int getIdParticipante() {
        return idParticipante;
    }

    /**
     * @param idParticipante
     */
    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    /**
     * @return id del campamento
     */
    public int getIdCampamento() {
        return idCampamento;
    }

    /**
     * @param idCampamento
     */
    public void setIdCampamento(int idCampamento) {
        this.idCampamento = idCampamento;
    }

    /**
     * @return fecha de inscripcion
     */
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * @param fechaInscripcion
     */
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * @return precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return tipo de inscripcion
     */
    public TipoInscripcion getTipoInscripcion() {
        return tipoInscripcion;
    }

    /**
     * @param tipoInscripcion
     */
    public void setTipoInscripcion(TipoInscripcion tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }

    /**
     * @return horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    /**
     * Devuelve una representación en cadena de la inscripción.
     *
     * @return Una cadena que representa la inscripción en formato legible.
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "idParticipante=" + idParticipante +
                ", idCampamento=" + idCampamento +
                ", fechaInscripcion=" + fechaInscripcion +
                ", precio=" + precio +
                ", tipoInscripcion=" + tipoInscripcion +
                ", horario=" + horario +
                '}';
    }


}
