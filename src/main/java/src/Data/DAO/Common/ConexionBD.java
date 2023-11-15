package Data.DAO.Common;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
/**
 * A class to manage the MySQL connection (general methods and configuration).
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class
ConexionBD {

    protected static Connection connection = null;


    /**
     * Metodo que establece una conexion con la base de datos MySQL utilizando la URL, nombre y contraseña
     * proporcionados
     * @param url URL de la base de datos
     * @param user Nombre de usuario para la conexion con la base de datos
     * @param password Contraseña asociada al nombre de usuario para la conexion
     * @return Una instancia que representa la conexion establecida
     * @throws RuntimeException Si ocurre algun error durante la carga del controlador JDBC o
     *                          si se produce una excepcion al intentar establecer la conexion
     */
    public Connection getConnection(String url, String user, String password){

        if( connection == null ){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection successfully opened!");
            }
            catch (SQLException e) {
                System.err.println("Connection to MySQL has failed!");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    // We can include here other methods to encapsulate CRUD commands...

    /**
     * Metodo que cierra la conexion con la base de datos si esta se encontraba abierta
     * @throws RuntimeException Si se produce cualquier tipo de excepcion al intentar cerrar la conexion
     */
    public void closeConnection() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection successfully closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to close the connection.");
            e.printStackTrace();
        }
    }
}
