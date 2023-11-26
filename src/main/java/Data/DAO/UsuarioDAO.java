package Data.DAO;

import Data.TipoUsuario;

public class UsuarioDAO {

    public UsuarioDAO(){

    }

    public boolean addUser(String username, String password, TipoUsuario tipoUsuario, Integer id_Asistente){
        return false;
    }

    public boolean asociateUserAsistentant(String username,int id_asistente){
        return false;
    }

    public TipoUsuario checkUser(String username, String password){

        return null;
    }
}
