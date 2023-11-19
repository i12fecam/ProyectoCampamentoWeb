package Business;

import Data.DAO.CampamentoDAO;
import Data.DTO.Actividad;
import Data.DTO.Campamento;
import Data.DTO.Monitor;

import java.io.*;
import java.util.ArrayList;
/**
 * GestorCampamentos class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class GestorCampamentos implements Serializable {


    private CampamentoDAO campamentoDAO;

    /**
     * Empty(default) constructor
     */
    public GestorCampamentos() {
        this.campamentoDAO = new CampamentoDAO();
    }

    /**
     * Método que crea un campamento
     * @param campamento Id del campamento a crear
     * @return false si el campamento es null, true si no es null
     */
    public boolean crearCampamento(Campamento campamento){
        if (campamento == null) {
            System.out.println("El campamento es nulo.");
            return false;
        }
        campamentoDAO.crearCampamento(campamento);
        return true;
    }


    /**
     * Método que crea un monitor
     * @param monitor Id del monitor a crear
     * @return true si el monitor es nulo, false ni no lo es
     */
    public boolean crearMonitor(Monitor monitor){
        if (monitor == null) {
            System.out.println("El monitor es nulo.");
            return false;
        }
        campamentoDAO.crearMonitor(monitor);
        return true;

    }


    /**
     * Método que crea una actividad
     * @param actividad Id de la actividad a crear
     * @return true si la actividad no es nula,false si es nula
     */
    public boolean crearActividad(Actividad actividad){
        if (actividad == null) {
            System.out.println("La actividad es nula.");
            return false;
        }
        campamentoDAO.crearActividad(actividad);
        return true;
    }

    /**
     * Metodo que asocia un monitor a una actividad
     * @param idMonitor Id del monitor que se quiere asociar a la actividad
     * @param idActividad Id de la actividad a la que se quiere asociar la actividad
     */
    public void asociarMonitorActividad(int idMonitor,int idActividad)
    {
        campamentoDAO.asociar_Monitor_Actividad(idMonitor,idActividad);
        //TODO comprobar que no se sobrepase el número de monitores máximo de la actividad
    }

    /**
     * Metodo que asocia una actividad a un campamento
     * @param idActividad Id de la actividad que se quiere asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar la actividad
     */
    public void asociarActividadCampamento(int idCampamento,int idActividad){



        Actividad act=campamentoDAO.devolverActividad(idActividad);
        Campamento campament=campamentoDAO.devolverCampamento(idCampamento);
        if(act.getNivelEducativo()==campament.getNivelEducativo()) {
            campamentoDAO.asociar_actividad(idActividad, idCampamento);
        }
    }

    /**
     * Metodo que asocia un monitor a un campamento
     * @param idMonitor Id del monitor responsable a asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar el monitor
     */
    public void asociarMonitorResponsableCampamento(int idMonitor, int idCampamento){

        Monitor mon=campamentoDAO.devolverMonitor(idMonitor);
        Campamento campament=campamentoDAO.devolverCampamento(idCampamento);
        //TODO comprobar que el monitor esta en alguna activdad del campamento
        campamentoDAO.asignar_monitor_responsable(idMonitor, idCampamento);
    }

    /**
     * Metodo que asocia un monitor especial a un campamento
     * @param idMonitor Id del monitor especial a asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar el monitor
     */
    public void asociarMonitorEspecialCampamento(int idMonitor, int idCampamento){

        Monitor mon=campamentoDAO.devolverMonitor(idMonitor);
        Campamento campament=campamentoDAO.devolverCampamento(idCampamento);
        //TODO comprobar si hay algun inscrito especial y que el monitor no este en ninguna actividad
        campamentoDAO.asignar_monitor_especial(idMonitor,idCampamento);
    }


    /**
     * Imprime en la consola una representación en cadena de los objetos Campamento almacenados en la lista "campamentos".
     * Este método itera a través de la lista de objetos Campamento almacenados en el atributo "campamentos" e imprime
     * en la consola una representación en cadena de cada uno de ellos utilizando el método "toString" de la clase Campamento.
     */

    public void toStringCampamentos() {
        ArrayList<Campamento> listaCampamentos = campamentoDAO.listarCampamentos();
        for (Campamento campamento : listaCampamentos) {
            System.out.println("ID: " + campamento.getIdCampamento());
            System.out.println("Fecha Inicio: " + campamento.getFechaInicio());
            System.out.println("Fecha Fin: " + campamento.getFechaFinal());
            System.out.println("Nivel Educativo: " + (campamento.getNivelEducativo().toString()));
            System.out.println("Asistentes máximos: " + campamento.getMaxAsistentes());
            System.out.println("--------------------------------------");
        }
    }
    /**
     * Imprime en la consola una representación en cadena de los objetos Monitor almacenados en la lista "monitores".
     * Este método itera a través de la lista de objetos Monitor almacenados en el atributo "monitores" e imprime
     * en la consola una representación en cadena de cada uno de ellos utilizando el método "toString" de la clase Monitor.
     */
    public void toStringMonitores(){
        ArrayList<Monitor> listaMonitores=campamentoDAO.listarMonitores();
        for(Monitor monitor : listaMonitores){
          System.out.println("ID: " + monitor.getIdentificador());
          System.out.println("Nombre: " + monitor.getNombre());
          System.out.println("Apellidos: "+ monitor.getApellidos());
          System.out.println("Especial: " + monitor.isEducadorEspecial());
          System.out.println("Fecha nacimiento: " + monitor.getFechaNacimiento());
          System.out.println("--------------------------------------");

        }
    }
    /**
     * Imprime en la consola una representación en cadena de los objetos Actividad almacenados en la lista "actividades".
     * Este método itera a través de la lista de objetos Actividad almacenados en el atributo "actividades" e imprime
     * en la consola una representación en cadena de cada uno de ellos utilizando el método "toString" de la clase Actividad.
     */
    public void toStringActividades() {
        ArrayList<Actividad> listaActividad = campamentoDAO.listarActividad();
        for( Actividad actividad : listaActividad){
            System.out.println("ID: " + actividad.getIdentificador());
            System.out.println("Nombre: " + actividad.getNombre());
            System.out.println("Nivel Educativo: " + actividad.getNivelEducativo().toString());
            System.out.println("Horario: " + actividad.getHorario().toString());
            System.out.println("Maximo de asistentes " + actividad.getMaxParticipantes());
            System.out.println("Maximo de monitores: " + actividad.getMonitoresNecesarios());
            System.out.println("--------------------------------------");
        }
    }

    public Campamento getCampamento(int id){
        return null;
    }




}
