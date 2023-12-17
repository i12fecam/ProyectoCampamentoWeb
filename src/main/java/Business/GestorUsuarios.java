package Business;

import Data.DAO.AsistenteDAO;
import Data.DAO.UsuarioDAO;
import Data.DTO.Asistente;
import Data.TipoUsuario;

import java.sql.SQLException;

public class GestorUsuarios {
    private UsuarioDAO usuarioDAO;
    private AsistenteDAO asistenteDAO;

    public GestorUsuarios(){
        usuarioDAO = new UsuarioDAO();
        asistenteDAO = new AsistenteDAO();
    }

    /**
     *
     * @param asistente El assitente debe ser rellenado con todos los campos menos el de ID
     * @param email
     * @param password
     * @return true, si se ha añadido correctamente, false, si no
     */
    public boolean AñadirUsuarioAsistente(Asistente asistente,String email, String password){
        try{
            usuarioDAO.addUser(email,password, TipoUsuario.asistente,null);
        }catch (RuntimeException e){
            return false;
        }

        int id = asistenteDAO.crear(asistente);
        if( id == -1){
            usuarioDAO.deleteUser(email);
            return false;
        }else{
            usuarioDAO.asociateUserAsistentant(email,id);
            return  true;
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return devuelve null en caso de que no coincida el usuario con la contraseña
     */
    public TipoUsuario comprobarUsuario(String username, String password){
        return usuarioDAO.checkUser(username,password);

    }

    public int devolverIdAsistente(String username){
        return usuarioDAO.getIdAsistente(username);
    }




    public void changePassword(String emailUser, String newPassword) throws SQLException {
        usuarioDAO.changePassword(emailUser,newPassword);
    }

    public static void main(String[] args) throws SQLException {
        UsuarioDAO usuarioDAO1 = new UsuarioDAO();
        usuarioDAO1.changePassword("abi@uco.es","nuevaContraseña");
    }
}
