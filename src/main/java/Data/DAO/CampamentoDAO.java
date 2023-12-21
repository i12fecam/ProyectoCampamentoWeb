package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
import Data.DTO.*;
import Data.Horario;
import Data.NivelEducativo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void crearActividad(Actividad actividad) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Actividades"));
            ps.setString(1, actividad.getNombre());
            ps.setString(2, actividad.getNivelEducativo().toString().toLowerCase());
            ps.setString(3, actividad.getHorario().toString().toLowerCase());
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
     * Metodo para modificcar un monitor en la base de datos
     * @param monitor Monitor que se desea modificar
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public void modificarMonitor(Monitor monitor) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("update_Monitor"));

            ps.setString(1, monitor.getNombre());
            ps.setDate(2, Date.valueOf(monitor.getFechaNacimiento()));
            ps.setInt(3, monitor.isEducadorEspecial() ? 1 : 0);
            ps.setString(4, monitor.getApellidos());
            ps.setInt(5, monitor.getIdentificador());

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Asocia una actividad a un campamento en la base de datos.
     *
     * @param id_actividad  El ID de la actividad a asociar.
     * @param id_campamento El ID del campamento al que se asociará la actividad.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void asociar_actividad(int id_actividad, int id_campamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_actividad_campamento"));
            ps.setInt(1, id_campamento);
            ps.setInt(2, id_actividad);

            int status = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Asigna un monitor como responsable de un campamento en la base de datos.
     *
     * @param idMonitor    El ID del monitor que se asignará como responsable.
     * @param idCampamento El ID del campamento al que se asignará el monitor como responsable.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public void asignar_monitor_responsable(int idMonitor, int idCampamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("update_monitorResponsable"));
            ps.setInt(1, idMonitor);
            ps.setInt(2, idCampamento);
            int status = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para modificar el campamento en la base de datos y añadir monitor especial
     * @param idMonitor Id del monitor especial a añadir en el campamento en la base de datos
     * @param idCampamento Id del campamento a modificar en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos.
     */

    public void asignar_monitor_especial(int idMonitor, int idCampamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("update_monitorEspecial"));
            ps.setInt(1,idMonitor);
            ps.setInt(2,idCampamento);
            int status = ps.executeUpdate();
            //Aquí no se puede imprimir nada por pantalla

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para comprobar si un monitor es de atención especial
     * @param idMonitor Id del monitor a comprobar
     * @throws RuntimeException Si hay un error de conexion con la base de datos.
     */

    public boolean comprobarmonitorespecial(int idMonitor) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("check_monitor_especial"));
            ps.setInt(1, idMonitor);
            ps.setInt(2, 1);

            try (ResultSet rs = ps.executeQuery()) {
                // Si hay resultados, el monitor es especial
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer las actividades parciales que tiene un campamento dado su id
     * @param idCampamento Id del campamento del que se quiero extraer la informacion
     * @return número de actividades del campamento exclusivamente parciales
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public int getNumActividadesParciales(int idCampamento) {
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
        return nActividades;
    }

    /**
     * Metodo para listar todos los campamentos que son incribibles.
     * @return Devuelve un vector con todos los camapamentos en los que se pueden inscribir en esa fecha
     * @throws RuntimeException Si hay un error de conexion con la base de datos
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
                        camp.setNivelEducativo(NivelEducativo.Infantil);
                        break;
                    case "Juvenil":
                        camp.setNivelEducativo(NivelEducativo.Juvenil);
                        break;
                    case "Adolescente":
                        camp.setNivelEducativo(NivelEducativo.Adolescente);
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
     *
     * @param idActividad Id de la actividad que queremos extraer la informacion.
     * @return Retorna la informacion de dicha actividad.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public Actividad devolverActividad(int idActividad) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_actividad_id"));
            ps.setInt(1, idActividad);
            ResultSet rs = ps.executeQuery();
            Actividad act = new Actividad();
            rs.next();
            String s = rs.getString("horario");
            if (s.equals("parcial")) {
                act.setHorario(Horario.Parcial);
            } else if (s.equals("completa")) {
                act.setHorario(Horario.Completa);
            }
            String n = rs.getString("nivel_educativo");
            if (n.equals("infantil")) {
                act.setNivelEducativo(NivelEducativo.Infantil);
            } else {
                if (n.equals("juvenil")) {
                    act.setNivelEducativo(NivelEducativo.Juvenil);
                } else if (n.equals("adolescente")) {
                    act.setNivelEducativo(NivelEducativo.Adolescente);
                }
            }
            act.setNombre(rs.getString("nombre"));
            act.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));
            act.setMaxParticipantes(rs.getInt("max_participantes"));
            act.setIdentificador(rs.getInt("id_actividad"));
            return act;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo para extraer los monitores que estan asociados a una actividad, dada su id
     * @param idActividad Id de la actividad a la que se quieren mirar los monitores asociados
     * @return Lista con los monitores que hay asociados a una actividad
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public List<Monitor> DevolverMonitores_Actividad(int idActividad) {
        try {
            List<Monitor> monitores = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_Monitores_Actividad_id"));
            ps.setInt(1, idActividad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Monitor monitor = new Monitor();
                monitor.setIdentificador(rs.getInt("id_monitor"));
                monitor.setNombre(rs.getString("nombre"));
                monitor.setApellidos(rs.getString("apellidos"));
                monitor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                monitor.setEducadorEspecial(rs.getBoolean("especial"));
                monitores.add(monitor);
            }
            return monitores;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    /**
     * Metodo para extraer las caracteristicas de un campamento de la base de datos
     * dado su identificador.
     * @param idCampamento Id del campamento del que se quiere extraer la informacion.
     * @return Devuelve la informacion de dicho campamento.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public Campamento devolverCampamento(int idCampamento) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_campamento_id"));
            ps.setInt(1, idCampamento);
            ResultSet rs = ps.executeQuery();
            Campamento camp = new Campamento();
            rs.next();
            String n = rs.getString("nivel_educativo");
            if (n.equals("infantil")) {
                camp.setNivelEducativo(NivelEducativo.Infantil);
            } else {
                if (n.equals("juvenil")) {
                    camp.setNivelEducativo(NivelEducativo.Juvenil);
                } else if (n.equals("adolescente")) {
                    camp.setNivelEducativo(NivelEducativo.Adolescente);
                }
            }
            camp.setMaxAsistentes(rs.getInt("max_asistentes"));
            camp.setIdCampamento(rs.getInt("id_campamento"));
            camp.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
            camp.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            return camp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer informacion de un monitor de la base de datos dado su identificador.
     *
     * @param idMonitor Id del monitor del que se quiere extraer la informacion.
     * @return Devuelve la informacion de dicho monitor.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public Monitor devolverMonitor(int idMonitor) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_monitor_id"));
            ps.setInt(1, idMonitor);
            ResultSet rs = ps.executeQuery();
            Monitor mon = new Monitor();
            rs.next();
            mon.setNombre(rs.getString("nombre"));
            mon.setIdentificador(rs.getInt("id_monitor"));
            mon.setEducadorEspecial(rs.getBoolean("especial"));
            mon.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            mon.setApellidos(rs.getString("apellidos"));//Cambiar

            return mon;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para listar todos los campamentos que existen en la base de datos
     *
     * @return Devuelve una lista con toda la informacion de todos los campamentos que existen en
     * la base de datos.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
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
                if (n.equals("Infantil")) {
                    campamento.setNivelEducativo(NivelEducativo.Infantil);
                } else {
                    if (n.equals("Juvenil")) {
                        campamento.setNivelEducativo(NivelEducativo.Juvenil);
                    } else if (n.equals("Adolescente")) {
                        campamento.setNivelEducativo(NivelEducativo.Adolescente);
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
     *
     * @return Devuelve una lista con la informacion de todas las actividades que existen en la
     * base de datos.
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public ArrayList<Actividad> listarActividad() {
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Actividades"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setNombre(rs.getString("nombre"));
                String n = rs.getString("nivel_educativo");
                if (n.equals("Infantil")) {
                    actividad.setNivelEducativo(NivelEducativo.Infantil);
                } else {
                    if (n.equals("Juvenil")) {
                        actividad.setNivelEducativo(NivelEducativo.Juvenil);
                    } else if (n.equals("Adolescente")) {
                        actividad.setNivelEducativo(NivelEducativo.Adolescente);
                    }
                }
                String h = rs.getString("horario");
                if (h.equals("parcial")) {
                    actividad.setHorario(Horario.Parcial);
                } else {
                    actividad.setHorario(Horario.Completa);
                }
                actividad.setMaxParticipantes(rs.getInt("max_participantes"));
                actividad.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));
                actividad.setIdentificador(rs.getInt("id_actividad"));
                listaActividades.add(actividad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaActividades;
    }

    /**
     * Metodo para listar todos los monitores que exiten en la base de datos
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     * @return Devuelve una lista con toda la informacion de todos los monitores existentes en la
     * base de datos.
     */
    public ArrayList<Monitor> listarMonitores() {
        ArrayList<Monitor> listaMonitores = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Monitores"));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Monitor monitor = new Monitor();
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

    public List<Asistente> DevolverAsistentes_Actividad(int idCampamento) {
        try{
            List<Asistente> asistentes = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_Campamentos_Asistentes_id"));
            ps.setInt(1,idCampamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Asistente asistente = new Asistente();
                asistente.setIdentificador(rs.getInt("id_asistente"));
                asistente.setNombre(rs.getString("nombre"));
                asistente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                asistente.setAtencionEspecial(rs.getBoolean("especial"));
                asistente.setApellidos(rs.getString("apellidos"));
                asistentes.add(asistente);
            }
            return asistentes;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo para extraer las actividades que existen en un campamento, dado su id
     * @param idCampamento Id del campamento del que se quieren saber sus actividades
     * @return Lista con las actividades que hay en dicho campamento
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public List<Actividad> DevolverActividades_Campamento(int idCampamento){
        try{
            List<Actividad> actividades = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_Campamentos_Actividades_id"));
            ps.setInt(1,idCampamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Actividad actividad = new Actividad();

                actividad.setIdentificador(rs.getInt("id_actividad"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setMaxParticipantes(rs.getInt("max_participantes"));
                actividad.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));
                String n = rs.getString("nivel_educativo");
                if (n.equals("Infantil")) {
                    actividad.setNivelEducativo(NivelEducativo.Infantil);
                } else {
                    if (n.equals("Juvenil")) {
                        actividad.setNivelEducativo(NivelEducativo.Juvenil);
                    } else if (n.equals("Adolescente")) {
                        actividad.setNivelEducativo(NivelEducativo.Adolescente);
                    }
                }
                String h = rs.getString("horario");
                if (h.equals("parcial")) {
                    actividad.setHorario(Horario.Parcial);
                } else {
                    actividad.setHorario(Horario.Completa);
                }

                PreparedStatement ls = con.prepareStatement(prop.getSentente("select_Monitores_Actividad_id"));
                ls.setInt(1, actividad.getIdentificador());
                ResultSet ss = ls.executeQuery();
                ArrayList<Monitor> Monitores = new ArrayList<>();
                while (ss.next()) {
                    Monitor monitor = new Monitor();
                    monitor.setApellidos(ss.getString(3));
                    monitor.setFechaNacimiento(ss.getDate(4).toLocalDate());
                    monitor.setNombre(ss.getString(2));
                    monitor.setIdentificador(ss.getInt(1));
                    monitor.setEducadorEspecial(ss.getBoolean(5));
                    Monitores.add(monitor);
                }
             //   actividad.setMonitores(Monitores);
                actividades.add(actividad);
            }
            return actividades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Metodo para listar las actividades que tiene un campamento
     * @param idCampamento Id del campamento que se quieren saber sus actividades
     * @return Lista con todas las actividades que tiene dicho campamento
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public List<Actividad> DevolverActividades_Campamento2(int idCampamento) {
        List<Actividad> actividades = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(prop.getSentente("select_Campamentos_Actividades_id2"))) {
            ps.setInt(1, idCampamento);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Actividad actividad = new Actividad();
                    actividad.setIdentificador(rs.getInt("id_actividad"));
                    actividad.setNombre(rs.getString("nombre"));
                    actividad.setMaxParticipantes(rs.getInt("max_participantes"));
                    actividad.setMonitoresNecesarios(rs.getInt("monitores_necesarios"));

                    actividades.add(actividad);
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción según sea necesario
            throw new RuntimeException(e);
        }

        return actividades;
    }

    /**
     * Metodo para litar los monitores que tiene una actividad
     * @param idActividad Id de la actividad
     * @return Lista con los monitores que tiene dicha actividad
     * @throws RuntimeException Si hay algun tipo de error en la conexion con la base de datos
     */
    public List<Monitor> DevolverMonitores_Actividad2(int idActividad) {
        try {
            List<Monitor> monitores = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_Monitores_Actividad_id2"));
            ps.setInt(1, idActividad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Monitor monitor = new Monitor();
                monitor.setIdentificador(rs.getInt("id_monitor"));
                monitor.setNombre(rs.getString("nombre"));
                monitor.setApellidos(rs.getString("apellidos"));
                monitor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                monitor.setEducadorEspecial(rs.getBoolean("especial"));
                monitores.add(monitor);
            }
            return monitores;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    /**
     * Metodo para listar los campamentos a los que se encuentra inscrito un asistente
     * @param idAsistente Id del asistente
     * @return Lista con todos los campamentos a los que se encuentra inscrito
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public List<Campamento> DevolverCampamentoInscritos(int idAsistente){
        ArrayList<Campamento> listaCampamentos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_campamento_inscritos"));
            ps.setInt(1,idAsistente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Campamento campamento = new Campamento();
                campamento.setIdCampamento(rs.getInt("id_campamento"));
                campamento.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                campamento.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
                String n = rs.getString("nivel_educativo");
                if (n.equals("Infantil")) {
                    campamento.setNivelEducativo(NivelEducativo.Infantil);
                } else {
                    if (n.equals("Juvenil")) {
                        campamento.setNivelEducativo(NivelEducativo.Juvenil);
                    } else if (n.equals("Adolescente")) {
                        campamento.setNivelEducativo(NivelEducativo.Adolescente);
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

}