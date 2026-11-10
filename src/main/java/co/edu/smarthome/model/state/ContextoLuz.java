package co.edu.smarthome.model.state;

/** Contrato del objeto cuyo estado interno puede ser reemplazado por una implementación State. */
public interface ContextoLuz {
    /** Reemplaza el estado actual de la luz. */
    void cambiarEstado(EstadoDispositivo estado);
}
