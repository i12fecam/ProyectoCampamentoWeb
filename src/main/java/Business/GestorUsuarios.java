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
     * Metodo para añadir un usuario, asistente y asociarlos.
     * @param asistente El assitente debe ser rellenado con todos los campos menos el de ID
     * @param email Email del usuario
     * @param password Contraseña del usuario
     * @return true, si se ha añadido correctamente; false, si no
     */
    public boolean AñadirUsuarioAsistente(Asistente asistente,String email, String password){
        try {
             asistenteDAO.crear(asistente);

             usuarioDAO.addUser(email, password, TipoUsuario.asistente);
             int id = usuarioDAO.asociarUsuarios(asistente, email);

             if(id == -1){
                 usuarioDAO.deleteUser(email);
             }else{

                usuarioDAO.actualizar(id,email);
             }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo para comprobar si un usuario es valido o no
     * @param email Email del usuario a comprobar
     * @return true, si el usuario es valido; false, si el usuario no es valido
     */
    public boolean ValidarUsuario(String email){
           return usuarioDAO.comprobarUsuarios(email);
    }

    /**
     *  Metodo para comprobar si el usuario es un asistente o un administrador
     * @param username Email del usuario
     * @param password Contraseña del usuario
     * @return devuelve null en caso de que no coincida el usuario con la contraseña
     */
    public TipoUsuario comprobarUsuario(String username, String password){
        return usuarioDAO.checkUser(username,password);

    }

    /**
     * Metodo para obtener el Id del asistente asociado al usuario dado su email
     * @param username Email del usuario
     * @return El Id del asistente asociado o -1 en caso de error
     */
    public int devolverIdAsistente(String username){
        return usuarioDAO.getIdAsistente(username);
    }


    /**
     * Metodo para cambiar la contraseña de un usuario
     * @param emailUser Email del usuario que quiere cabiar la contraseña
     * @param newPassword Nueva contraseña
     * @throws SQLException Si ocurre un error con la conexion de la base de datos
     */
    public void changePassword(String emailUser, String newPassword) throws SQLException {
        usuarioDAO.changePassword(emailUser,newPassword);
    }

}
