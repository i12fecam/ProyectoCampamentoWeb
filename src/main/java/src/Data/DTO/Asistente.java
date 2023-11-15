package Data.DTO;

import Data.Persona;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Assistant  class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class Asistente extends Persona implements Serializable {

    /*Attributes*/
    private boolean atencionEspecial;

    /**
     * empty(default) constructor
     */
    public Asistente(){

    }

    /**
     *  Parametrized constructor
     * @param identificador El identificador del asistente
     * @param nombre El nombre del asistente
     * @param apellidos Apellidos del asistente
     * @param atencionEspecial Indica si el asistente requiere atencion especial
     * @param fechaNacimiento La fecha de nacimiento del asistente
     */
    public Asistente(int identificador, String nombre, String apellidos, LocalDate fechaNacimiento, boolean atencionEspecial) {
        super(identificador, nombre, apellidos, fechaNacimiento);
        this.atencionEspecial = atencionEspecial;
    }


    /**
     *  Verifica si el Asistente requiere atencion especial
     * @return True si el asistente requiere atencion especial, false en caso de no necesitarla
     */
    public boolean isAtencionEspecial() {
        return atencionEspecial;
    }

    /**
     * Establece si el Asistente requiere atencion especial
     * @param atencionEspecial true si el Asistente requiere atencion especial, false en caso de no necesitarla
     */
    public void setAtencionEspecial(boolean atencionEspecial) {
        this.atencionEspecial = atencionEspecial;
    }

    /**
     * Es una representacion del objeto Asistente
     * @return Una cadena que contiene los detalles del Asistente
     */
    public String toString() {
        return "Asistente{" +
                "identificador=" + getIdentificador() +
                ", nombre='" + getNombre() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", Fecha de Nacimiento:'" + getFechaNacimiento() + '\'' +
                ", especial=" + isAtencionEspecial() +
                '}';
    }

}


