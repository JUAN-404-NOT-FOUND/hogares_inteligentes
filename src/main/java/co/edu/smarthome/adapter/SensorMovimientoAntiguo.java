package co.edu.smarthome.adapter;

/** Contrato del sensor heredado que informa el movimiento mediante un nivel numérico. */
public interface SensorMovimientoAntiguo {
    /** Devuelve el nivel numérico de movimiento informado por el sensor legado. */
    int leerNivelMovimiento();
}
