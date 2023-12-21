package Business;

import Data.DTO.Asistente;
import Data.DAO.AsistenteDAO;
import Data.DTO.Campamento;

import java.io.*;
import java.util.ArrayList;
/**
 * GestorAsistentes class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */

public class GestorAsistentes implements Serializable{
    /*Attributes*/



    private AsistenteDAO asistenteDAO;


    /**
     * Empty(default) class
     */
    public GestorAsistentes() {
        this.asistenteDAO = new AsistenteDAO();
    }


    /**
     * Método que permite dar de alta a un asistente
     * @param asistente Asistente al que se quiere dar de alta
     * @return true excepto si el asistente ya esta dado de alta o es nulo que retorna false
     */
    public boolean darAlta(Asistente asistente) {
        if (asistente == null) {
            System.out.println("El asistente es nulo.");
            return false;
        }
        asistenteDAO.crear(asistente);
        return true;
    }

    /**
     * Metodo que permite modificar un asistente
     * @param asistente Asistente que se quiere modificar
     */
    public void ModificarAsistente(Asistente asistente) {

        if (asistente == null) {
            System.out.println("El asistente es nulo.");

        }else {
            asistenteDAO.modificar(asistente);
        }
    }

    /**
     *
     * @return Devuelve una lista de todos los asistentes registrados en el sistema
     */
    public ArrayList<Asistente> listarAsistentes(){return asistenteDAO.listarAsistentes();}

    /**
     * Método que nos permite listar a los asistentes
     */
    public void listar() {
        ArrayList<Asistente> listaAsistentes = asistenteDAO.listarAsistentes();

        for (Asistente asistente : listaAsistentes) {
            System.out.println("ID: " + asistente.getIdentificador());
            System.out.println("Nombre: " + asistente.getNombre());
            System.out.println("Apellidos: " + asistente.getApellidos());
            System.out.println("Fecha de nacimiento: " + asistente.getFechaNacimiento());
            System.out.println("Atención especial: " + (asistente.isAtencionEspecial() ? "Sí" : "No"));
            System.out.println("--------------------------------------");
        }
    }


}
