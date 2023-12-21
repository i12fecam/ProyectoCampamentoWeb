package Business;

import Data.DAO.AsistenteDAO;
import Data.DAO.CampamentoDAO;
import Data.DAO.InscripcionDAO;
import Data.DTO.Asistente;
import Data.DTO.Campamento;
import Data.DTO.Inscripcion;
import Data.Horario;
import Data.TipoInscripcion;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * GestorInscripciones class
 * @author Noelia Ruiz
 * @author Antonio Javier Quintero
 * @author Abigail Fernández
 * @author Fátima Caballero
 * */
public class GestorInscripciones implements Serializable{
    /*Attributes*/


    /**
     * * Empty(default) class
     */
    public GestorInscripciones(){
    }

    /**
     * Metodo para crear una inscripcion
     * @param id_asistente Id del asistente al que se quiere inscribir
     * @param id_campamento Id del campamento al que se le inscribir
     * @param fechaInscripcion Fecha en la que se le esta inscribiendo
     * @param horario Horario en el que se le va a inscribir en el campamento
     * @throws RuntimeException Si el campamento esta lleno y no hay espacio para mas inscripciones
     * @throws RuntimeException Si la fecha de inscripcion es demasiado tardia para el campamento
     */
    public void crearInscripcion(int id_asistente,int id_campamento, LocalDate fechaInscripcion, Horario horario, float precio){

        InscripcionDAO ins = new InscripcionDAO();
        CampamentoDAO camp = new CampamentoDAO();
        AsistenteDAO asis = new AsistenteDAO();
        Asistente asistente = asis.getAsistente(id_asistente);
        Campamento campamento = camp.devolverCampamento(id_campamento);

        //comprobar si el campamento sigue teniendo espacio
       if(ins.GetInscritos(id_campamento) > campamento.getMaxAsistentes())
       {
           throw new RuntimeException("El campamento ya se encuentra lleno");
       }


        //mirar si es tardia
        TipoInscripcion tipoInscripcion;
        if(fechaInscripcion.isBefore(campamento.getFechaInicio().minusDays(15))){
            tipoInscripcion = TipoInscripcion.Temprana;
        }
        else if(fechaInscripcion.isAfter(campamento.getFechaInicio().minus(15, ChronoUnit.DAYS)) && fechaInscripcion.isBefore(campamento.getFechaInicio().minus(2,ChronoUnit.DAYS))){

            tipoInscripcion = TipoInscripcion.Tardia;
        }
        else{
            throw new RuntimeException("La fecha de inscripcion es demasiado tardia");
        }

        //comprobar si necesita monitor especial
        //TODO sustituir funcion
        if (asistente.isAtencionEspecial() /*&& !campamento.tieneMonitorEspecial()*/) {
            //añadir monitor especial al campamento
            System.out.println("!!!!ATENCION: Después de esta operación asegurese de añadir un monitor especial al campamento");
        }

        //se crea la inscripción
        ins.nuevaInscripcion(new Inscripcion(asistente.getIdentificador(),campamento.getIdCampamento(),fechaInscripcion,precio,tipoInscripcion,horario));
        System.out.println("El precio de la inscripcion es de: " + precio);
    }

    public float calcularPrecio(int idCamp, Horario horario){
        float precio;
        CampamentoDAO camp = new CampamentoDAO();
        Campamento campamento = camp.devolverCampamento(idCamp);
        if(horario == Horario.Parcial){
            precio = 100;
            precio = precio + camp.getNumActividadesParciales(campamento.getIdCampamento())*20;
        }else{
            precio = 300;
        }
        return precio;
    }

    /**
     * Metodo que nos permite cancelar una inscripcion de un asistente a un campamento
     * @param id_asistente Id del asistente al cual se le quiere cancelar la inscripcion
     * @param id_campamento Id del campamento al que el asistente estaba inscrito y se le quiere borrar
     * @return True- si la inscripcion ha sido cancelada con exito, False- si no se ha podido cancelar
     */
    public boolean cancelarInscripcion(int id_asistente,int id_campamento){

        InscripcionDAO ins = new InscripcionDAO();
        Inscripcion inscripcion = ins.getInscripcion(id_asistente,id_campamento);
        if(inscripcion.getTipoInscripcion() == TipoInscripcion.Temprana){
            ins.cancelarInscripcion(inscripcion);
            return true;
        }

        return false;
    }
    /**
     * Metodo que permite consultar los campamentos disponibles
     * @throws RuntimeException Si ocurre cualquier error durante la consulta o impresion de la informacion
     */
    public void consultarCampamentosDisponibles() {
        try {
            CampamentoDAO camp = new CampamentoDAO();

            ArrayList<Campamento> listaCampamentos = camp.getCampamentosInscribibles();
            for (Campamento campamento : listaCampamentos) {
                System.out.println("ID: " + campamento.getIdCampamento());
                System.out.println("Fecha Inicio: " + campamento.getFechaInicio());
                System.out.println("Fecha Fin: " + campamento.getFechaFinal());
                System.out.println("Nivel Educativo: " + (campamento.getNivelEducativo().toString()));
                System.out.println("Asistentes máximos: " + campamento.getMaxAsistentes());
                //FALTA MONITOR RESPONSABLE Y ESPECIAL//
                System.out.println("--------------------------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
