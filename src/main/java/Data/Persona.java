package Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Person class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */

public class Persona implements Serializable {
    /*Attributes*/
    protected int identificador;
    protected String nombre;
    protected String apellidos;

    protected LocalDate fechaNacimiento;

    /* Constructors */

    /**
     * Empty (default) constructor
     * */
    public Persona() {
    }

    /**
     * Parameterized constructor
     * @param identificador
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     */
    public Persona(int identificador, String nombre, String apellidos, LocalDate fechaNacimiento) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }
    /* Getters and setters*/
    /**
     * @return fecha de nacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    /**
     * @return nombre + apellidos
     */
    public String getNombreCompleto() {
        return nombre + apellidos;
    }

    /**
     * Devuelve una representación en forma de cadena de la instancia de Persona.
     *
     * @return Una cadena que contiene los atributos de la instancia en formato legible.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "identificador=" + identificador +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\''+
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    /**
     * Compara esta instancia de Persona con otra para verificar si tienen el mismo identificador.
     *
     * @param persona La instancia de Persona con la que se va a comparar.
     * @return true si ambas instancias tienen el mismo identificador, false en caso contrario.
     */
    public boolean equals (Persona persona) {
        return persona.identificador == this.identificador;

    }
}
