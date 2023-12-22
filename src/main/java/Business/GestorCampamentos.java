package Business;

import Data.DAO.CampamentoDAO;
import Data.DTO.Actividad;
import Data.DTO.Asistente;
import Data.DTO.Campamento;
import Data.DTO.Monitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    public boolean asociarMonitorActividad(int idMonitor,int idActividad)
    {
        Actividad actividad = campamentoDAO.devolverActividad(idActividad);
        List<Monitor> monitores_activdad = campamentoDAO.DevolverMonitores_Actividad(idActividad);
        if(monitores_activdad.size() < actividad.getMonitoresNecesarios()){
            campamentoDAO.asociar_Monitor_Actividad(idMonitor,idActividad);
            return true;
        }
        return false;
    }

    /**
     * Metodo que asocia una actividad a un campamento
     * @param idActividad Id de la actividad que se quiere asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar la actividad
     */
    public boolean asociarActividadCampamento(int idCampamento,int idActividad){

        Actividad act=campamentoDAO.devolverActividad(idActividad);
        Campamento campament=campamentoDAO.devolverCampamento(idCampamento);
        if(act.getNivelEducativo()==campament.getNivelEducativo()) {
            try {
                campamentoDAO.asociar_actividad(idActividad, idCampamento);
            }catch (Exception e){
                return false;
            }

            return true;
        }
        return false;
    }

    /**
     * Metodo que asocia un monitor a un campamento
     * @param idMonitor Id del monitor responsable a asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar el monitor
     */
    public boolean asociarMonitorResponsableCampamento(int idMonitor, int idCampamento){

            boolean asignacionExitosa = false;
            List<Actividad> actividades = campamentoDAO.DevolverActividades_Campamento2(idCampamento);
            for (Actividad actividad : actividades) {
                int id_actividad = actividad.getIdentificador();
                List<Monitor> monitores_actividad = campamentoDAO.DevolverMonitores_Actividad2(id_actividad);
                for (Monitor monitor : monitores_actividad) {
                    if (idMonitor == monitor.getIdentificador()) {
                        campamentoDAO.asignar_monitor_responsable(idMonitor, idCampamento);
                        asignacionExitosa = true;
                    }
                }
            }
        if (!asignacionExitosa) {
            throw new mensajeExcepcion("El monitor no pertenece a una de las actividades del campamento");
        }
        return asignacionExitosa;
    }

    /**
     * Metodo para comprobar si un monitor es de atención especial
     * @param idMonitor Id del monitor a comprobar
     */

    public boolean comprobar_monitor_especial(int idMonitor){
        return campamentoDAO.comprobarmonitorespecial(idMonitor);
    }


    /**
     * Metodo que asocia un monitor especial a un campamento
     * @param idMonitor Id del monitor especial a asociar al campamento
     * @param idCampamento Id del campamento al que se quiere asociar el monitor
     */
    public void asociarMonitorEspecialCampamento(int idMonitor, int idCampamento) {

        List<Actividad> actividades = campamentoDAO.DevolverActividades_Campamento2(idCampamento);

        for (Actividad actividad : actividades) {
            // Verificar si el monitor está asignado a la actividad actual
            int id_actividad = actividad.getIdentificador();
            List<Monitor> monitores = campamentoDAO.DevolverMonitores_Actividad2(id_actividad);
            for (Monitor monitor : monitores) {
                if (idMonitor == monitor.getIdentificador()) {
                    throw new mensajeExcepcion("No se ha asociado el monitor especial al campamento porque ya esta asignado a una actividad");
                }
            }
                List<Asistente> asistentesCampamento= campamentoDAO.DevolverAsistentes_Actividad(idCampamento);
                for (Asistente asistente : asistentesCampamento) {
                    if (asistente.isAtencionEspecial() && comprobar_monitor_especial(idMonitor)) {
                        // Si hay algún asistente con atención especial, asignar el monitor especial y salir del método.
                        campamentoDAO.asignar_monitor_especial(idMonitor, idCampamento);
                        System.out.println("Se ha asociado al monitor especial con exito");
                        return;
                    }
                    else{
                        if(!comprobar_monitor_especial(idMonitor)){
                            throw new mensajeExcepcion("El monitor no es de atención especial");
                        }
                    }
                }
            // Verificar si hay algún asistente con atención especial en la actividad
            throw new mensajeExcepcion("No se ha asociado el monitor especial al campamento porque no hay asistentes especiales");
        }
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
     *
     * @return Devuelve una lista de todos los campamentos registrados en el sistema
     */
    public ArrayList<Campamento> listarCampamentos(){
        return campamentoDAO.listarCampamentos();
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

    public ArrayList<Monitor> listarMonitores(){
        return campamentoDAO.listarMonitores();
    }

    public void ModificarMonitor(Monitor monitor) {

        if (monitor == null) {
            System.out.println("El monitor es nulo.");

        }else {
            campamentoDAO.modificarMonitor(monitor);
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

    /**
     *
     * @return Devuelve una lista de todas las actividades registradas en el sistema
     */
    public ArrayList<Actividad> listarActividades(){
        return campamentoDAO.listarActividad();
    }


    /**
     *  Metodo para saber los campamentos en los que esta inscrito un asistente
     * @param idAsistente Id del asistente
     * @return Lista con todos los campamentos a los que esta inscrito
     */
    public List<Campamento> listarCampamentosInscritos(int idAsistente){
        return campamentoDAO.DevolverCampamentoInscritos(idAsistente);
    }

    /**
     *  Metodo para verificar si ya esta la actividad asociada al campamento
     * @param idCampamento Id del campamento
     * @param idActividad Id de la actividad
     * @return Lista con todos los campamentos a los que esta inscrito
     */
    public int comprobar_duplicidad_camp_act(int idCampamento,int idActividad){
        return campamentoDAO.comprobarDuplicidadCampamentoActividad(idCampamento,idActividad);
    }

    /**
     *  Metodo para verificar si ya esta asociado el monitor a una actividad
     * @param idMonitor Id del monitor
     * @param idActividad Id de la actividad
     */
    public int comprobar_duplicidad_mon_act(int idMonitor,int idActividad){
        return campamentoDAO.comprobarDuplicidadMonitorActividad(idMonitor,idActividad);
    }

}
