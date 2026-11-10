package co.edu.smarthome.adapter;

/** Contrato normalizado para consultar si un sensor detecta movimiento. */
public interface SensorMovimiento {
    /** Indica si la lectura actual detecta movimiento. */
    boolean hayMovimiento();
}
