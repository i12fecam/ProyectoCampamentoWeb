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
    public UsuarioDAO(){
        prop = ProyectProperties.getInstance();
        bd = new ConexionBD();
        con = bd.getConnection(prop.getUrl(), prop.getUsername(), prop.getPassword());
    }

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
        }
        return true;
    }

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


    public void changePassword(String emailUser, String newPassword) throws SQLException {
        PreparedStatement ps = con.prepareStatement(prop.getSentente("change_password_user"));
        ps.setString(1,newPassword);
        ps.setString(2,emailUser);
        ps.executeUpdate();
    }
}
