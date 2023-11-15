package Data.DTO;

import Data.NivelEducativo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Campament class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class Campamento implements Serializable {
    /*Attributes*/
    private int idCampamento;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private NivelEducativo nivelEducativo;
    private int numAsistentes;
    private int maxAsistentes;
    private ArrayList<Monitor> monitores = new ArrayList<>();
    private ArrayList<Actividad> actividades = new ArrayList<>();




    /**
     * Parameterized constructor
     * @param idCampamento id del campamento
     * @param fechaInicio fecha de inicio del campamento
     * @param fechaFinal fecha de fin del campamento
     * @param nivelEducativo nivel educativo que tiene el campamento
     * @param maxAsistentes numero maximo de asistentes del campamento
     */
    public Campamento(int idCampamento, LocalDate fechaInicio, LocalDate fechaFinal, NivelEducativo nivelEducativo, int maxAsistentes) {
        if(fechaFinal.isBefore(fechaInicio)){
            throw new RuntimeException("Las fechas no tienen sentido");
        }
        this.idCampamento = idCampamento;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nivelEducativo = nivelEducativo;
        this.maxAsistentes = maxAsistentes;
        this.numAsistentes = 0;
    }

    public Campamento() {

    }
    /*Getters and setters*/

    /**
     *
     * @return El id del campamento
     */
    public int getIdCampamento() {
        return idCampamento;
    }

    /**
     * Establece el id del campamento
     * @param idCampamento
     */
    public void setIdCampamento(int idCampamento) {
        this.idCampamento = idCampamento;
    }

    /**
     *
     * @return Fecha de inicio del campamento
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del campamento
     * @param fechaInicio
     */

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *
     * @return Fecha de cierre del campamento
     */
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Establece la fecha de cierre del campamento
     * @param fechaFinal
     */
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return Nivel educativo del campamento
     */
    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    /**
     * Establece el nivel educativo que tiene el campamento
     * @param nivelEducativo
     */
    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    /**
     *
     * @return Numero maximo de asistentes
     */
    public int getMaxAsistentes() {
        return maxAsistentes;
    }

    /**
     * Establece el número maximo de asistentes
     * @param maxAsistentes
     */
    public void setMaxAsistentes(int maxAsistentes) {
        this.maxAsistentes = maxAsistentes;
    }





    @Override
    public String toString() {
        return "Campamento{" +
                "idCampamento=" + idCampamento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinal=" + fechaFinal +
                ", nivelEducativo=" + nivelEducativo +
                ", numAsistentes=" + numAsistentes +
                ", maxAsistentes=" + maxAsistentes +
                ", monitores=" + monitores.size() +
                ", actividades=" + actividades.size() +
                '}';
    }




    /**
     * Método para asociar un monitor al campamento. Un monitor se añade a la lista si no se encuentra en la lista de
     * monitores del campamento y si es un monitor encargado.
     * @param monitor Monitor que se desea ascoiar
     */
    public void asociarMonitor(Monitor monitor) {
        try {

            boolean monitorEncargadoActividad = false;
            for (Actividad it : actividades) {
                if (it.getMonitores().contains(monitor)) {
                    monitorEncargadoActividad = true;
                    break;
                }
            }
            if (monitorEncargadoActividad && !monitores.contains(monitor)) {
                monitores.add(monitor);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * Método para asociar un monitor especial al campamento. Este se añade a la lista de monitores si no se encuentra
     * previamente añadido como monitor para ese mismo campamento y si es monitor especial
     * @param monitorEspecial Monitor especial que se desea asociar
     */
    public void asociarMonitorEspecial(Monitor monitorEspecial) {
        for (Actividad it : actividades) {
            if (it.getMonitores().contains(monitorEspecial)) {
                throw new RuntimeException("El monitor especial no puede estar asociado a ninguna actividad");
            }
        }
        if (!monitores.contains(monitorEspecial) && monitorEspecial.isEducadorEspecial()) {
            monitores.add(monitorEspecial);
        } else {
            System.out.println("El monitor ya esta asociado o no es educador especial");
            throw new RuntimeException("Error al asociar Monitor Especial");
        }
    }

    public boolean tieneMonitorEspecial(){
        if(monitores!=null) {
            for (Monitor it : monitores) {
                if (it != null && it.isEducadorEspecial()) {
                    return true;
                }
            }
        }
        return false;
    }

}







