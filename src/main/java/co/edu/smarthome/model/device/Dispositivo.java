package co.edu.smarthome.model.device;

/** Contrato común de identidad, nombre, ubicación y estado visible de un dispositivo. */
public interface Dispositivo {
    /** Identificador estable del dispositivo. */
    String obtenerId();
    /** Nombre visible del dispositivo. */
    String obtenerNombre();
    /** Habitación asociada al dispositivo. */
    String obtenerHabitacion();
    /** Texto breve que representa el estado actual. */
    String obtenerEtiquetaEstado();
}
