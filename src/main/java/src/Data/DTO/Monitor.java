package Data.DTO;

import Data.Persona;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Monitor class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class Monitor extends Persona implements Serializable {
    /*Attributes*/
    private boolean educadorEspecial;
    /**
     * Empty (default) constructor
     * */
    public Monitor(){}


    /**Constructor con todos sus parametros obligatorios
     * @param identificador El identificador del monitor.
     * @param nombre El nombre del monitor.
     * @param apellidos
     * @param educadorEspecial Indica si el monitor es educador especial.
     * @param fechaNacimiento La fecha de nacimiento del monitor.
    */
    public Monitor(int identificador, String nombre, String apellidos, LocalDate fechaNacimiento, boolean educadorEspecial) {
        super(identificador, nombre, apellidos, fechaNacimiento);
        this.educadorEspecial = educadorEspecial;
    }

    /**
     * Establece si el Monitor es un educador especial
     * @param educadorEspecial
     */
    /* setters and getters*/
    public void setEducadorEspecial(boolean educadorEspecial) {
        this.educadorEspecial = educadorEspecial;
    }

    /**
     *
     * @return true si el Monitor es un educador especial, false en caso contrario
     */
    public boolean isEducadorEspecial() {
        return educadorEspecial;
    }

    /**
     *  Imprime la informacion de un Monitor
     * @return Cadena con toda la informacion necesario de un Monitor
     */
    public String toString() {
        return "Monitor{" +
                "identificador=" + getIdentificador() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido1='" + getApellidos() + '\'' +
                ", especial=" + isEducadorEspecial() +
                '}';
    }
}
