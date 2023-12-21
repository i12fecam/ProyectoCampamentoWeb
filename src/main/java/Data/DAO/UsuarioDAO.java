package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
import Data.DTO.Asistente;
import Data.TipoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    final private ProyectProperties prop;
    final private ConexionBD bd;
    final private Connection con;

    /**
     * Empty(default) class
     */
    public UsuarioDAO(){
        prop = ProyectProperties.getInstance();
        bd = new ConexionBD();
        con = bd.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
    }

    /**
     * Metodo para añadir un usuario a la base de datos
     * @param username Email del usuario
     * @param password Contraseña del usuario
     * @param tipoUsuario Tipo de usuario: asistente o administrador
     * @param id_Asistente Id del asistente asociado
     * @return True, si se ha añadido el usuario correctamente; false, en caso de error
     */
    public boolean addUser(String username, String password, TipoUsuario tipoUsuario, Integer id_Asistente){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("insert_usuarios"));
            ps.setString(1,tipoUsuario.toString() );
            ps.setString(2,username);
            ps.setString(3,password);
            ps.setInt(4,id_Asistente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Metodo para comprobar si un email se encuentra en la base de datos
     * @param email Email a comprobar
     * @return True, si el email no se encuentra; false, si el email se encuentra
     */

    public boolean comprobarUsuarios(String email){
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("comprobar_usuarios"));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String emailBaseDatos = rs.getString("username");
                if(emailBaseDatos.equals(email)){
                    return false;
                }
            }
            return true;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para asociar usuarios con asistentes
     * @param asistente Asistente
     * @param email Email del usuario
     * @return Id del asistente asociado al usuario, -1 en caso de error
     * @throws RuntimeException Si hay algun error al conectarse con la base de datos
     */
    public int asociarUsuarios(Asistente asistente, String email){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("select_asistente_nombre"));
            ps.setString(1, asistente.getNombre());
            ps.setString(2,asistente.getApellidos());
            ps.setString(3,email);
            int id = -1;
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 id = rs.getInt("id_asistente");
            }
            return id;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para actualizar usuarios en la base de datos
     * @param id Id que se quiere actualizar
     * @param email Email del usuario al que se quiere actualizar
     * @throws RuntimeException Si hay algun error de conexion con la base de datos
     */
    public void actualizar(int id, String email){
        try{
            PreparedStatement ps = con.prepareStatement(prop.getSentente("actualizar_usuarios"));
            ps.setInt(1,id);
            ps.setString(2,email);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para saber el tipo de usurio que es
     * @param username Email del usuario
     * @param password Contraseña del usuario
     * @return Tipo de usuario (asistente, administrador); null en caso de error
     */
    public TipoUsuario checkUser(String username, String password){

        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("check_user_password"));
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs =ps.executeQuery();

            if(rs.first()){
                String usuario = rs.getString(1);
                if(usuario.equals(TipoUsuario.asistente.toString())){
                    return TipoUsuario.asistente;
                }
                else if(usuario.equals(TipoUsuario.administrador.toString())){
                    return TipoUsuario.administrador;
                }
                else return null;
            }
            else return null;
        } catch (SQLException e) {
            return null;
        }

    }

    /**
     * Metodo para eliminar a un usuario de la base de datos
     * @param username Email del usuario
     * @return True en caso de que se haya elimiado correctamente; false en caso contrario
     */
    public boolean deleteUser(String username){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(prop.getSentente("delete_user"));
            ps.setString(1,username);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;

    }

    /**
     * Metodo para obtener el id del asistente asociado al usuario de la base de datos
     * @param username Email del usuario
     * @return Id del asistente asociado; -1 en caso de que no se encuentre ningun asistente asociado
     */
    public int getIdAsistente(String username) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(prop.getSentente("get_id_asistente"));
            ps.setString(1,username);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                return rs.getInt("fk_asistente");
            }
            return -1;
        } catch (SQLException e) {
            return -1;
        }

    }

    /**
     * Metodo para cambiar la contraseña de un usuario en la base de datos
     * @param emailUser Email del usuario
     * @param newPassword Nueva contraseña del usuario
     * @throws SQLException Si hay algun error de conexion con la base de datos
     */

    public void changePassword(String emailUser, String newPassword) throws SQLException {
        PreparedStatement ps = con.prepareStatement(prop.getSentente("change_password_user"));
        ps.setString(1,newPassword);
        ps.setString(2,emailUser);
        ps.executeUpdate();
    }
}
