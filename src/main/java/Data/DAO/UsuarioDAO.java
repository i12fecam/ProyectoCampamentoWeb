package Data.DAO;

import Data.DAO.Common.ConexionBD;
import Data.DAO.Common.ProyectProperties;
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
            return false;
        }
        return true;
    }

    public boolean asociateUserAsistentant(String username,int id_asistente){
        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("asociate_user_asistant"));
            ps.setInt(1,id_asistente);
            ps.setString(2,username);
            if(ps.executeUpdate() == 0){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public TipoUsuario checkUser(String username, String password){

        try {
            PreparedStatement ps = con.prepareStatement(prop.getSentente("check_user_password"));
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs =ps.executeQuery();
            rs.next();
            if(rs.next()){
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
}
