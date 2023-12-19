package Business;

public class mensajeExcepcion extends RuntimeException {
    public mensajeExcepcion(String mensaje) {
        super(mensaje);
    }
}
