package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
import Data.DTO.Inscripcion;
import Data.Horario;
import Data.TipoInscripcion;

import java.sql.*;

public class InscripcionDAO {

    private ProyectProperties prop;

    private Connection con;

    /**
     * Empty(default) class
     */
    public InscripcionDAO(){
        prop = ProyectProperties.getInstance();
        ConexionBD bd = new ConexionBD();

        con = bd.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
    }





    /**
     * Metodo para insertar una inscripcion a la base de datos
     * @param inscripcion Inscripcion que se va a guardar
     * @throws RuntimeException Si hay un error al conectarse con la base de datos
     */
    public void nuevaInscripcion(Inscripcion inscripcion) {

        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Inscripcion"));

            ps.setDate(1, Date.valueOf(inscripcion.getFechaInscripcion()));
            ps.setFloat(2, inscripcion.getPrecio());

            if (inscripcion.getHorario() == Horario.PARCIAL) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 2);
            }
            if (inscripcion.getTipoInscripcion() == TipoInscripcion.TARDIA) {
                ps.setInt(4,2);
            } else {
                ps.setInt(4,1);
            }
            ps.setInt(5,1);//completar
            ps.setInt(6,1);
            int status = ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Metodo para eliminar una inscipcion de la base de datos dada una incripcion
     * @param inscripcion Inscripcion que se quiere eliminar
     * @throws RuntimeException Si hay algun error al conectarse con la base de datos
     */
    public void cancelarInscripcion(Inscripcion inscripcion){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("cancelar_incripcion"));
            ps.setInt(1,inscripcion.getIdParticipante());
            ps.setInt(2,inscripcion.getIdCampamento());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Metodo para extraer una inscripcion de un asistente a un campamento de la base de datos
     * @param id_asistente Id del asistente de la inscripcion
     * @param id_campamento Id del campamento inscrito
     * @return Inscripcion descrita por id asistente e id campamento
     * @throws RuntimeException Si hay un error en la conexion con la base de datos
     */
    public Inscripcion getInscripcion(int id_asistente,int id_campamento){
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("get_inscripcion"));
            ps.setInt(1,id_asistente);
            ps.setInt(2,id_campamento);
            ResultSet res = ps.executeQuery();
            Inscripcion ins =new Inscripcion();
            res.next();
            ins.setFechaInscripcion(res.getDate("fecha_inscripcion").toLocalDate());
            ins.setPrecio(res.getFloat("precio"));
            String s = res.getString("horario");
            if( s.equals("parcial")){
                ins.setHorario(Horario.PARCIAL);
            }
            else if(s.equals("completa")){
                ins.setHorario(Horario.COMPLETA);
            }
            else throw new RuntimeException("Esto no deberia pasar");

            s = res.getString("tipo_inscripcion");
            if(s.equals("temprana")){
                ins.setTipoInscripcion(TipoInscripcion.TEMPRANA);
            }
            else if(s.equals("tardia")){
                ins.setTipoInscripcion(TipoInscripcion.TARDIA);
            }else throw new RuntimeException("Esto no deberia pasar 2");
            ins.setIdCampamento(id_campamento);
            ins.setIdParticipante(id_asistente);
            return ins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para extraer las personas que hay inscritas en un campamento dado su id
     * @param id_campamentos Id del campamento a buscar
     * @return Numero de las personas inscritas a ese campamento
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public int GetInscritos(int id_campamentos){
        int nInscritos = -1;
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_n_inscritos"));
            ps.setInt(1,id_campamentos);

            ResultSet rs = ps.executeQuery();
            rs.next();
            nInscritos = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nInscritos;

    }


}
