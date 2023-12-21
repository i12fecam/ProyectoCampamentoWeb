package Interface;


import java.time.LocalDate;
import java.util.Scanner;
import Business.*;
import Data.DTO.Actividad;
import Data.DTO.Asistente;
import Data.DTO.Campamento;
import Data.DTO.Monitor;
import Data.Horario;
import Data.NivelEducativo;

public class Main {


    private GestorAsistentes gestorAsistentes;
    private GestorCampamentos gestorCampamentos;
    private GestorInscripciones gestorInscripciones;

    public static void main(String[] args) {
        Main main = new Main();
        main.iniciar();
    }

    public void iniciar() {

        Scanner scanner = new Scanner(System.in);

        gestorAsistentes = new GestorAsistentes();
        gestorCampamentos = new GestorCampamentos();
        gestorInscripciones = new GestorInscripciones();

        boolean bucle =true;
        while (bucle) {
            System.out.println("Menu principal:");
            System.out.println("1. Gestionar asistentes");
            System.out.println("2. Gestionar campamentos");
            System.out.println("3. Gestionar inscripciones");
            System.out.println("4. Salir");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    gestionarAsistentes();
                    break;
                case "2":
                    gestionarCampamentos();
                    break;
                case "3":
                     gestionarInscripciones();
                    break;
                case "4":
                    salir();
                    bucle=false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    public void gestionarAsistentes() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Opciones para gestionar a los asistentes:");
            System.out.println("1. Dar de alta asistente");
            System.out.println("2. Modificar asistente");
            System.out.println("3. Listar asistentes");
            System.out.println("0. Volver al menu principal");

            String opcion2 = scanner.nextLine();

            switch (opcion2) {
                case "1":
                    // Recopilar la información del asistente
                    try {
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidos = scanner.nextLine();
                        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                        String fechaNacimientoStr = scanner.nextLine();
                        System.out.print("Atencion Especial (true/false): ");
                        boolean atencionEspecial = scanner.nextBoolean();
                        scanner.nextLine();
                        // Crear una instancia de Asistente con la información recopilada
                        // El Id se pone por defecto a 0 y la base de datos se encargará de dar su valor real
                        Asistente nuevoAsistente = new Asistente(0,nombre, apellidos, LocalDate.parse(fechaNacimientoStr), atencionEspecial);
                        // Dar de alta al asistente
                        gestorAsistentes.darAlta(nuevoAsistente);
                    }catch (Exception e){
                        System.out.println("Error al dar de alta el asistente: " + e.getMessage());
                    }
                    System.out.println("Asistente dado de alta con exito.");
                    break;
                case "2":
                    try {
                        gestorAsistentes.listar();
                        System.out.println("Ingrese el identificador del asistente que desea modificar: ");
                        int identificador = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        System.out.println("Ingrese el nuevo nombre del asistente: ");
                        String nom = scanner.nextLine();

                        System.out.println("Ingrese los apellidos del asistente: ");
                        String apellids = scanner.nextLine();

                        System.out.println("Ingrese la nueva fecha de nacimiento del asistente (formato: YYYY-MM-DD): ");
                        LocalDate fechaNac = LocalDate.parse(scanner.nextLine());

                        System.out.println("Ingrese si el asistente requiere atencion especial (true/false): ");
                        boolean atencionEsp = Boolean.parseBoolean(scanner.nextLine());

                        Asistente asistente = new Asistente(identificador, nom, apellids, fechaNac, atencionEsp);

                        // Modificar el asistente
                        gestorAsistentes.ModificarAsistente(asistente);
                    }catch (Exception e){
                        System.out.println("Error al modificar el asistente: " + e.getMessage());
                    }
                    // Volver a listar los asistentes para verificar los cambios
                    gestorAsistentes.listar();

                    break;
                case "3":
                    gestorAsistentes.listar();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcion invalida.");
            }
        }}

    public void gestionarCampamentos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Opciones para gestionar los campamentos");
            System.out.println("1. Crear campamento");
            System.out.println("2. Crear monitores");
            System.out.println("3. Crear actividades");
            System.out.println("4. Asociar monitores a actividades");
            System.out.println("5. Asociar monitor responsable a un campamento");
            System.out.println("6. Asociar monitor de atención especial a un campamento");
            System.out.println("7. Asociar actividad a campamento");
            System.out.println("8. Ver campamentos");
            System.out.println("9. Ver monitores");
            System.out.println("10. Ver actividades");
            System.out.println("0. Volver al menu principal");

            String opcion3 = scanner.nextLine();

            switch (opcion3) {
                case "1":
                    // Lógica para crear campamento
                    try {
                        System.out.print("Fecha de inicio (YYYY-MM-DD):");
                        String fechaIncioStr = scanner.nextLine();
                        System.out.print("Fecha de finalizacion (YYYY-MM-DD):");
                        String fechaFinalStr = scanner.nextLine();
                        System.out.print("Nivel educativo (Infantil, Juvenil, Adolescente):");
                        String nivelEducativoStr = scanner.nextLine();
                        NivelEducativo nivelEducativo;
                        if (nivelEducativoStr.equals("Infantil") ) {
                            nivelEducativo = NivelEducativo.Infantil;
                        } else if (nivelEducativoStr.equals("Juvenil")) {
                            nivelEducativo = NivelEducativo.Juvenil;
                        } else if (nivelEducativoStr.equals("Adolescente")) {
                            nivelEducativo = NivelEducativo.Adolescente;
                        } else {
                            throw new RuntimeException("Error al capturar el nivel educativo");
                        }
                        System.out.print("Numero maximo de Asistentes:");
                        int maxAsistentes = scanner.nextInt();
                        scanner.nextLine();
                        Campamento campamento = new Campamento(0,LocalDate.parse(fechaIncioStr), LocalDate.parse(fechaFinalStr), nivelEducativo, maxAsistentes);
                        gestorCampamentos.crearCampamento(campamento);
                        System.out.println("El campamento se ha creado correctamente");
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Hubo un problema creando el campamento: " + e.getMessage());
                    }

                    break;
                case "2":
                    // Lógica para crear monitores
                    try {
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidos = scanner.nextLine();
                        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                        String fechaNacimiento = scanner.nextLine();
                        System.out.print("Atencion Especial (true/false): ");
                        boolean atencionEspecial = scanner.nextBoolean();
                        scanner.nextLine();
                        Monitor monitor = new Monitor(0,nombre,apellidos,LocalDate.parse(fechaNacimiento),atencionEspecial);

                        // Dar de alta al monitor
                        gestorCampamentos.crearMonitor(monitor);
                        System.out.println("Monitor dado de alta con exito.");
                    }catch (Exception e){
                        System.out.println("Se produjo un error creando el monitor: "+ e.getMessage());
                    }
                    break;
                case "3":
                    // Lógica para crear actividades
                    try {
                        System.out.println("Nombre de la actividad:");
                        String nombre = scanner.nextLine();
                        System.out.println("Nivel educativo (Infantil, Juvenil, Adolescente):");
                        String nivelEducativoStr = scanner.nextLine();
                        NivelEducativo nivelEducativo;
                        if (nivelEducativoStr.equals("Infantil")) {
                            nivelEducativo = NivelEducativo.Infantil;
                        } else if (nivelEducativoStr.equals("Juvenil")) {
                            nivelEducativo = NivelEducativo.Juvenil;
                        } else if (nivelEducativoStr.equals("Adolescente")) {
                            nivelEducativo = NivelEducativo.Adolescente;
                        } else {
                            throw new RuntimeException("Error al capturar el nivel educativo");
                        }
                        System.out.println("Horario (Parcial,Completo):");
                        String horarioStr = scanner.nextLine();
                        Horario horario;
                        if (horarioStr.equals("Parcial")) {
                            horario = Horario.Parcial;
                        } else if (horarioStr.equals("Completo")) {
                            horario = Horario.Completo;
                        } else {
                            throw new RuntimeException("Error al capturar el horario");
                        }

                        System.out.println("Numero maximo de participantes");
                        int maxParticipantes = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Numero de monitores necesarios");
                        int monitoresNecesarios = scanner.nextInt();
                        scanner.nextLine();

                        Actividad actividad = new Actividad(nombre,0,nivelEducativo,horario,maxParticipantes,monitoresNecesarios);

                        gestorCampamentos.crearActividad(actividad);
                    }catch (Exception e){
                        System.out.println("Se produjo un error creando la actividad: " + e.getMessage());
                    }
                    break;
                case "4":
                    // Lógica para asociar monitores a actividades
                    try {
                        System.out.println("Elija unos de los monitores por su id");
                        gestorCampamentos.toStringMonitores();
                        int idMonitor = scanner.nextInt();
                        System.out.println("Elija unos de los actividades por su id");
                        gestorCampamentos.toStringActividades();
                        int idActividad = scanner.nextInt();
                        scanner.nextLine();
                        gestorCampamentos.asociarMonitorActividad(idMonitor, idActividad);
                    }catch (Exception e){
                        System.out.println("Se produjo un error asociando al monitor con la actividad: " + e.getMessage());
                    }
                    break;
                case "5":
                    // Lógica para asociar monitor responsable a un campamento
                    try {
                        System.out.println("Elija unos de los monitores por su id:");
                        gestorCampamentos.toStringMonitores();
                        int idMonitor = scanner.nextInt();
                        System.out.println("Elija unos de los campamentos por su id:");
                        gestorCampamentos.toStringCampamentos();
                        int idCampamento = scanner.nextInt();
                        scanner.nextLine();
                        gestorCampamentos.asociarMonitorResponsableCampamento(idMonitor, idCampamento);
                    }catch (Exception e){
                        System.out.println("Se produjo un error asociando al monitor con el campamento: " + e.getMessage() );
                    }
                    break;
                case "6":
                    // Lógica para asociar monitores de atención especial a un campamento
                    try {
                        System.out.println("Elija unos de los monitores por su id(compruebe que sea de atención especial):");
                        gestorCampamentos.toStringMonitores();
                        int idMonitor = scanner.nextInt();
                        System.out.println("Elija unos de los campamentos por su id:");
                        gestorCampamentos.toStringCampamentos();
                        int idCampamento = scanner.nextInt();
                        scanner.nextLine();
                        gestorCampamentos.asociarMonitorEspecialCampamento(idMonitor,idCampamento);
                    }catch (Exception e){
                        System.out.println("Se produjo un error asociando al monitor con el campamento" + e.getMessage() );
                    }
                    break;
                case "7":
                    //Logica de asociar actividad a campamento
                    try {
                        System.out.println("Elija unos de los actividades por su id:");
                        gestorCampamentos.toStringActividades();
                        int idActividad = scanner.nextInt();
                        System.out.println("Elija unos de los campamentos por su id:");
                        gestorCampamentos.toStringCampamentos();
                        int idCampamento = scanner.nextInt();
                        scanner.nextLine();
                        gestorCampamentos.asociarActividadCampamento(idCampamento,idActividad);
                    }catch (Exception e){
                        System.out.println("Se produjo un error asociando la actividad con el campamento: " + e.getMessage());
                    }
                    break;
                case "8":
                    //Logica de ver Campamentos
                    gestorCampamentos.toStringCampamentos();
                    break;
                case "9":
                    //Logica de ver Monitores
                    gestorCampamentos.toStringMonitores();
                    break;
                case "10":
                    //Logica de ver Actividades
                    gestorCampamentos.toStringActividades();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
    public void gestionarInscripciones() {
        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Opciones para gestionar las inscripciones:");
            System.out.println("1.Crear inscripcion");
            System.out.println("2.Cancelar inscripcion");
            System.out.println("3.Consultar campamentos no comenzados");
            System.out.println("0.Volver al menu principal");

            String opcion4= scanner.nextLine();

            switch (opcion4){
                case "1":
                    try {
                        gestorAsistentes.listar();
                        System.out.println("Introduzca el identificador del asistente: ");
                        int idAsistente = scanner.nextInt();
                        gestorInscripciones.consultarCampamentosDisponibles();
                        System.out.println("Introduzca el identificador del campamento: ");
                        int idCampamento = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduzca la fecha de inscripcion(YYYY-MM-DD): ");
                        String inscrip = scanner.nextLine();
                        System.out.println("Introduzca el horario (Parcial, Completa): ");
                        String horarioStr = scanner.nextLine();
                        Horario horario;
                        if (horarioStr.equals("Parcial")) {
                            horario = Horario.Parcial;
                        } else if (horarioStr.equals("Completa")) {
                            horario = Horario.Completo;
                        } else {
                            throw new RuntimeException("Error al capturar el horario");
                        }
                        float precio = gestorInscripciones.calcularPrecio(idCampamento,horario);
                        gestorInscripciones.crearInscripcion( idAsistente,idCampamento, LocalDate.parse(inscrip), horario, precio);
                        System.out.println("Inscripcion creada con exito");
                    }catch (Exception e){
                        System.out.println("Error al crear la inscripcion: " + e.getMessage());
                    }
                    break;
                case "2":
                    try{
                        System.out.println("Introduzca el identificador del asistente: ");
                        gestorAsistentes.listar();
                        int ident = scanner.nextInt();

                        System.out.println("Introduzca el identificador del campamento: ");
                        gestorInscripciones.consultarCampamentosDisponibles();
                        int idcamp = scanner.nextInt();

                        if(gestorInscripciones.cancelarInscripcion(ident, idcamp)){
                            System.out.println("Inscripcion cancelada con exito");
                        }
                        else{
                            System.out.println("Error al cancelar la inscripcion (compruebe si es temprana)");
                        }
                    }catch(Exception e){
                        System.out.println("Error al cancelar la inscripcion: "+ e.getMessage());

                }

                    break;
                case "3":
                    gestorInscripciones.consultarCampamentosDisponibles();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcion invalida");
                
            }
        }

    }
    public void salir(){


    }

}

