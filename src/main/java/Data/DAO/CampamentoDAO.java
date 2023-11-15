package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
import Data.DTO.*;
import Data.Horario;
import Data.NivelEducativo;

import java.sql.*;
import java.util.ArrayList;

/**
 * CampamentoDAO class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 */
public class CampamentoDAO {
    private ProyectProperties prop;
    private ConexionBD bd;
    private Connection con;


    /**
     * Empty(default) class
     */
    public CampamentoDAO() {
        prop = ProyectProperties.getInstance();
        bd = new ConexionBD();
        con = bd.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
    }

    /**
     * Este método inserta en la base de datos un nuevo campamento
     * @param campamento Campamento que se quiere añadir
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void crearCampamento(Campamento campamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Campamentos"));
            ps.setDate(1, Date.valueOf(campamento.getFechaInicio()));
            ps.setDate(2, Date.valueOf(campamento.getFechaFinal()));
            ps.setString(3, campamento.getNivelEducativo().toString().toUpperCase());
            ps.setInt(4, campamento.getMaxAsistentes());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Este método inserta en la base de datos una nueva actividad
     * @param actividad Actividad que se quiere añadir
     * @throws  RuntimeException Si hay un error de conexion con la base de datos
     */
    public void crearActividad(Actividad actividad) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Actividades"));
            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getNivelEducativo().toString().toUpperCase());
            ps.setString(3, actividad.getHorario().toString().toUpperCase());
            ps.setInt(4, actividad.getMaxParticipantes());
            ps.setInt(5, actividad.getMonitoresNecesarios());
            ps.setInt(6, actividad.getIdentificador());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Este método inserta en la base de datos un monitor en la tabla Monitores
     * @param monitor Monitor que se quiere añadir
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void crearMonitor(Monitor monitor) {

        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Monitores"));
            ps.setString(1, monitor.getNombre());
            ps.setString(2, monitor.getApellidos());
            ps.setDate(3, Date.valueOf(monitor.getFechaNacimiento()));
            ps.setInt(4, monitor.isEducadorEspecial() ? 1 : 0);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Asocia un monitor a una actividad en la base de datos.
     *
     * @param idMonitor El ID del monitor a asociar.
     * @param idActividad El ID de la actividad a la que se asociará el monitor.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void asociar_Monitor_Actividad(int idMonitor, int idActividad) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_monitor_actividad"));
            ps.setInt(1, idMonitor);
            ps.setInt(2, idActividad);
            int status = ps.executeUpdate();
            if (status > 0) {
                System.out.println("Monitor asociado a la actividad con exito");
            } else {
                System.out.println("Fallo al asociar el monitor a la actividad");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Asocia una actividad a un campamento en la base de datos.
     *
     * @param id_actividad   El ID de la actividad a asociar.
     * @param id_campamento  El ID del campamento al que se asociará la actividad.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void asociar_actividad(int id_actividad, int id_campamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_actividad_campamento"));
            ps.setInt(1, id_campamento);
            ps.setInt(2, id_actividad);
            
             int status = ps.executeUpdate();
             if( status >0 ){
                 System.out.println("Actividad asociada al campamento con exito");
             }else{
                 System.out.println("Fallo al asociar la actividad al campamento");
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Asigna un monitor como responsable de un campamento en la base de datos.
     *
     * @param idMonitor      El ID del monitor que se asignará como responsable.
     * @param idCampamento   El ID del campamento al que se asignará el monitor como responsable.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void asignar_monitor_responsable(int idMonitor, int idCampamento){
        try{
            PreparedStatement ps = con. prepareStatement(prop.getSentente("update_monitorResponsable"));
            ps.setInt(1,idMonitor);
            ps.setInt(2,idCampamento);
            int status = ps.executeUpdate();
            if (status > 0){
                System.out.println("Monitor responsable al campamento asociado con exito");
            }else{
                System.out.println("Fallo al asociar el monitor responsable del campamento");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para modificar el campamento en la base de datos y añadir monitor especial
     * @param idMonitor Id del monitor especial a añadir en el campamento en la base de datos
     * @param idCampamento Id del campamento a modificar en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos.
     */

    public void asignar_monitor_especial(int idMonitor, int idCampamento){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("update_monitorEspecial"));
            ps.setInt(1,idMonitor);
            ps.setInt(2,idCampamento);
            int status = ps.executeUpdate();
            //Aquí no se puede imprimir nada por pantalla
            if ( status > 0){
                System.out.println("Monitor especial del campamento asociado con exito");
            }else{
                System.out.println("Fallo al asociar el monitor especial del campamento");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer las actividades parciales que tiene un campamento dado su id
     * @param idCampamento Id del campamento del que se quiero extraer la informacion
     * @return número de actividades del campamento exclusivamente parciales
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public int getNumActividadesParciales(int idCampamento){
        int nActividades = -1;
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_n_actividades_parciales"));
            ps.setInt(1,idCampamento);
            ResultSet rs = ps.executeQuery();
            rs.next();
            nActividades = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  nActividades;
    }

    /**
     * Metodo para listar todos los campamentos que son incribibles.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve un vector con todos los camapamentos en los que se pueden inscribir en esa fecha
     */
    public ArrayList<Campamento> getCampamentosInscribibles() {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("get_campamentos_disponibles"));
            ResultSet rs = ps.executeQuery();
            ArrayList<Campamento> vector = new ArrayList<>();
            while (rs.next()) {
                Campamento camp = new Campamento();
                String n = rs.getString("nivel_educativo");
                switch (n) {
                    case "Infantil":
                        camp.setNivelEducativo(NivelEducativo.INFANTIL);
                        break;
                    case "Juvenil":
                        camp.setNivelEducativo(NivelEducativo.JUVENIL);
                        break;
                    case "Adolescente":
                        camp.setNivelEducativo(NivelEducativo.ADOLESCENTE);
                        break;
                }

                camp.setMaxAsistentes(rs.getInt("max_asistentes"));
                camp.setIdCampamento(rs.getInt("id"));
                camp.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
                camp.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                //aqui se podria poner la foreign key de los montores especiales y responsables
                vector.add(camp);
            }
            return vector;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer las caracteristicas de una actividad de la base de datos
     * dado un id de una actividad.
     * @param idActividad Id de la actividad que queremos extraer la informacion.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Retorna la informacion de dicha actividad.
     */
    public Actividad devolverActividad(int idActividad) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_actividad_id"));
            ps.setInt(1,idActividad);
            ResultSet rs = ps.executeQuery();
            Actividad act = new Actividad();
            rs.next();
            String s = rs.getString("horario");
            if (s.equals("parcial")) {
                act.setHorario(Horario.PARCIAL);
            } else if (s.equals("completa")) {
                act.setHorario(Horario.COMPLETA);
            }
            String n = rs.getString("nivel_educativo");
            if (n.equals("infantil")) {
                act.setNivelEducativo(NivelEducativo.INFANTIL);
            } else {
                if (n.equals("juvenil")) {
                    act.setNivelEducativo(NivelEducativo.JUVENIL);
                } else if (n.equals("adolescente")) {
                    act.setNivelEducativo(NivelEducativo.ADOLESCENTE);
                }
            }
            act.setNombre(rs.getString("nombre"));
            act.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));
            act.setMaxParticipantes(rs.getInt("max_participantes"));
            act.setIdentificador(rs.getInt("id_actividad"));
            return act;


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo para extraer las caracteristicas de un campamento de la base de datos
     * dado su identificador.
     * @param idCampamento Id del campamento del que se quiere extraer la informacion.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve la informacion de dicho campamento.
     */
    public Campamento devolverCampamento(int idCampamento){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_campamento_id"));
            ps.setInt(1,idCampamento);
            ResultSet rs = ps.executeQuery();
            Campamento camp=new Campamento();
            rs.next();
            String n = rs.getString("nivel_educativo");
            if( n.equals("infantil")){
                camp.setNivelEducativo(NivelEducativo.INFANTIL);
            }
            else{
                if(n.equals("juvenil")){
                    camp.setNivelEducativo(NivelEducativo.JUVENIL);
                }
                else if(n.equals("adolescente")){
                    camp.setNivelEducativo(NivelEducativo.ADOLESCENTE);
                }
            }
            camp.setMaxAsistentes(rs.getInt("max_asistentes"));
            camp.setIdCampamento(rs.getInt("id_campamento"));
            camp.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
            camp.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            return camp;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer informacion de un monitor de la base de datos dado su identificador.
     * @param idMonitor Id del monitor del que se quiere extraer la informacion.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve la informacion de dicho monitor.
     */
    public Monitor devolverMonitor(int idMonitor){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_monitor_id"));
            ps.setInt(1,idMonitor);
            ResultSet rs = ps.executeQuery();
            Monitor mon=new Monitor();
            rs.next();
            mon.setNombre(rs.getString("nombre"));
            mon.setIdentificador(rs.getInt("id_monitor"));
            mon.setEducadorEspecial(rs.getBoolean("especial"));
            mon.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            mon.setApellidos(rs.getString("apellidos"));//Cambiar

            return mon;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para listar todos los campamentos que existen en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve una lista con toda la informacion de todos los campamentos que existen en
     *         la base de datos.
     */

    public ArrayList<Campamento> listarCampamentos() {
        ArrayList<Campamento> listaCampamentos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Campamentos"));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Campamento campamento = new Campamento();
                campamento.setIdCampamento(rs.getInt("id_campamento"));
                campamento.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                campamento.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
                String n = rs.getString("nivel_educativo");
                if( n.equals("Infantil")){
                    campamento.setNivelEducativo(NivelEducativo.INFANTIL);
                }
                else{
                    if(n.equals("Juvenil")){
                        campamento.setNivelEducativo(NivelEducativo.JUVENIL);
                    }
                    else if(n.equals("Adolescente")){
                        campamento.setNivelEducativo(NivelEducativo.ADOLESCENTE);
                    }
                }
                campamento.setMaxAsistentes(rs.getInt("max_asistentes"));
                //falta monitor responsable y monitor especial
                listaCampamentos.add(campamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCampamentos;
    }

    /**
     * Metodo para listar todas las actividades que existen en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve una lista con la informacion de todas las actividades que existen en la
     *         base de datos.
     */
    public ArrayList<Actividad> listarActividad(){
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Actividades"));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Actividad actividad =  new Actividad();
                actividad.setNombre(rs.getString("nombre"));
                String n = rs.getString("nivel_educativo");
                if( n.equals("Infantil")){
                    actividad.setNivelEducativo(NivelEducativo.INFANTIL);
                }
                else{
                    if(n.equals("Juvenil")){
                        actividad.setNivelEducativo(NivelEducativo.JUVENIL);
                    }
                    else if(n.equals("Adolescente")){
                        actividad.setNivelEducativo(NivelEducativo.ADOLESCENTE);
                    }
                }
                String h = rs.getString("horario");
                if( h.equals("parcial")){
                    actividad.setHorario(Horario.PARCIAL);
                }
                else{
                    actividad.setHorario(Horario.COMPLETA);
                }
                actividad.setMaxParticipantes(rs.getInt("max_participantes"));
                actividad.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));
                actividad.setIdentificador(rs.getInt("id_actividad"));
                listaActividades.add(actividad);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaActividades;
    }

    /**
     * Metodo para listar todos los monitores que exiten en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve una lista con toda la informacion de todos los monitores existentes en la
     *         base de datos.
     */
    public ArrayList<Monitor> listarMonitores(){
        ArrayList<Monitor> listaMonitores = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Monitores"));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Monitor monitor =  new Monitor();
                monitor.setNombre(rs.getString("nombre"));
                monitor.setApellidos(rs.getString("apellidos"));
                monitor.setIdentificador(rs.getInt("id_monitor"));
                monitor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                monitor.setEducadorEspecial(rs.getBoolean("especial"));
                listaMonitores.add(monitor);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaMonitores;
    }
}


