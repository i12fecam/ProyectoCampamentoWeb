package Data.DAO.Common;

import java.io.*;
import java.util.Properties;

public class ProyectProperties {

    private Properties config;
    private Properties sql;

    private static ProyectProperties instance;

    public static ProyectProperties getInstance(){
        if(instance == null ){
            instance = new ProyectProperties();
        }
        return instance;
    }
    /**
     * Metodo que carga las propiedades las propiedades del proyecto desde archivos de configuracion.
     * Lee las propiedades de configuracion generales desde "config.properties" y las especificas de
     * SQL desde "sql.properties"
     * @throws RuntimeException Si ocurre algun error al cargar los ficheros de configuracion
     */
    private ProyectProperties(){

        //String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //String configPath=Thread.currentThread().getContextClassLoader().getResource("config.properties");

        config = new Properties();
        sql = new Properties();

        try {
            FileInputStream configFI = new FileInputStream("./config.properties");
            config.load(configFI);
            configFI.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream sqlFI = new FileInputStream("./sql.properties");
            sql.load(sqlFI);
            sqlFI.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Metodo que obtiene la URL del servidor de la base de datos
     * @return La URL del servidor
     */
    public String getUrl(){
        return config.getProperty("servername");
    }

    /**
     * Metodo que obtiene el nombre de usuario de la base de datos
     * @return El nombre de usuario
     */
    public String getUsername(){
        return config.getProperty("name");
    }

    /**
     * Metodo que obtiene la contraseña asociada al usuario de la base de datos
     * @return La contraseña
     */
    public String getPassword(){
        return config.getProperty("password");
    }

    /**
     * Metodo que obtiene una sentencia SQL
     * @param name Nombre asociado a la sentencia SQL
     * @return La sentencia SQL correspondiente al nombre proporcionado
     * @throws RuntimeException Si no se encuentra la sentencia SQL para el nombre dado
     */
    public String getSentente(String name){
        String result =sql.getProperty(name);
        if(result.equals("")){
            throw new RuntimeException("No se encontro la sentencia SQl del archivo sql.properties");
        }
        return  result;
    }

}
