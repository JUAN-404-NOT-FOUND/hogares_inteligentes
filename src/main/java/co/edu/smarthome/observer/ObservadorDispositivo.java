package co.edu.smarthome.observer;

@FunctionalInterface
public interface ObservadorDispositivo {
    void alCambiarEstadoDispositivo(String nombreDispositivo, String etiquetaEstado);
}
