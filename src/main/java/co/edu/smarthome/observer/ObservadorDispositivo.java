package co.edu.smarthome.observer;

/** Callback Observer para reaccionar a cambios visibles en el estado de un dispositivo. */
@FunctionalInterface
public interface ObservadorDispositivo {
    /** Recibe el nombre del dispositivo y la etiqueta de su nuevo estado. */
    void alCambiarEstadoDispositivo(String nombreDispositivo, String etiquetaEstado);
}
