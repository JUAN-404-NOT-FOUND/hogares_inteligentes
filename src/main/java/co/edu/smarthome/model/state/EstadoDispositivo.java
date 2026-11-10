package co.edu.smarthome.model.state;

/** Define el comportamiento de encendido y apagado que implementa cada estado de la luz. */
public interface EstadoDispositivo {
    /** Define la transición de encendido para este estado. */
    void encender(ContextoLuz luz);
    /** Define la transición de apagado para este estado. */
    void apagar(ContextoLuz luz);
    /** Devuelve el texto de estado que se presenta en la interfaz. */
    String obtenerEtiqueta();
}
