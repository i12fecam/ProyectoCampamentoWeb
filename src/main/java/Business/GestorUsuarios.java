package Business;

import Data.DAO.AsistenteDAO;
import Data.DAO.UsuarioDAO;
import Data.DTO.Asistente;
import Data.TipoUsuario;

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
     * @param username
     * @param password
     * @return true, si se ha añadido correctamente, false, si no
     */
    public boolean AñadirUsuarioAsistente(Asistente asistente,String username, String password){
        try{
            usuarioDAO.addUser(username,password, TipoUsuario.asistente,null);
        }catch (RuntimeException e){
            return false;
        }

        int id = asistenteDAO.crear(asistente);
        if( id == -1){
            usuarioDAO.deleteUser(username);
            return false;
        }else{
            usuarioDAO.asociateUserAsistentant(username,id);
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


}
