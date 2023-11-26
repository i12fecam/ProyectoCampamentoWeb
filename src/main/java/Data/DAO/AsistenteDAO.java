package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
import Data.DTO.Asistente;
import java.util.ArrayList;

import java.sql.Connection;

import java.sql.*;


public class AsistenteDAO {


    private ProyectProperties prop;

    private ConexionBD bd;

    private Connection con;

    /**
     * Empty(default) class
     */
    public AsistenteDAO() {
        prop = prop.getInstance();
        bd = new ConexionBD();

        con = bd.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
    }

    /**
     * Metodo para añadir un asistente en la base de datos
     * @param asistente Asistente que se quiere añadir
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public void crear(Asistente asistente) {
        //TODO crear assert de todos los parametros necesarios
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_Asistentes"));

            ps.setString(1, asistente.getNombre());
            ps.setDate(2, Date.valueOf(asistente.getFechaNacimiento()));
            ps.setInt(3, asistente.isAtencionEspecial() ? 1 : 0);
            ps.setString(4, asistente.getApellidos());

            int status = ps.executeUpdate();
            if (status > 0) {
                System.out.println("Asistente agregado con exito");
            } else {
                System.out.println("Fallo al agregar el asistente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO hacer que devuelve el valor del id del asistente

    }

    /**
     * Metodo para modificcar un asistente en la base de datos
     * @param asistente Asistente que se desea modificar
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public void modificar(Asistente asistente) {
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("update_Asistente"));

            ps.setString(1, asistente.getNombre());
            ps.setDate(2, Date.valueOf(asistente.getFechaNacimiento()));
            ps.setInt(3, asistente.isAtencionEspecial() ? 1 : 0);
            ps.setString(4, asistente.getApellidos());
            ps.setInt(5, asistente.getIdentificador());

            int status = ps.executeUpdate();
            if (status > 0) {
                System.out.println("Asistente modificado con éxito");
            } else {
                System.out.println("Fallo al modificar el asistente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Metodo para listar todos los asistentes que hay en la base de datos
     * @return Una lista con toda la informacion de todos los asistentes existentes en la base de datos
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */

    public ArrayList<Asistente> listar() {
        ArrayList<Asistente> listaAsistentes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_all_Asistentes"));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Asistente asistente = new Asistente();
                asistente.setIdentificador(rs.getInt("id_asistente"));
                asistente.setNombre(rs.getString("nombre"));
                asistente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                asistente.setAtencionEspecial(rs.getInt("especial") == 1);
                asistente.setApellidos(rs.getString("apellidos"));

                listaAsistentes.add(asistente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAsistentes;
    }

    /**
     * Metodo para extraer toda la informacion de un asistente de la base de datos dado su identificador
     * @param id_asistente Id del asistente del que se quiere obtener su informacion
     * @return Devuelve la informacion de dicho asistente
     * @throws RuntimeException Si hay un error de conexion con la base de datos
     */
    public Asistente getAsistente(int id_asistente){
        Asistente asistente = new Asistente();
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_asistente_id"));
            ps.setInt(1,id_asistente);
            ResultSet rs = ps.executeQuery();

            rs.next();

            asistente.setIdentificador(rs.getInt("id_asistente"));
            asistente.setNombre(rs.getString("nombre"));
            asistente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            asistente.setAtencionEspecial(rs.getInt("especial") == 1);
            asistente.setApellidos(rs.getString("apellidos"));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  asistente;
    }
}