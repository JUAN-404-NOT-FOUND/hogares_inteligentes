package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

/** Política intercambiable que decide cómo responde una luz a la presencia o ausencia de movimiento. */
@FunctionalInterface
public interface EstrategiaAutomatizacion {
    /** Aplica la política cuando cambia la lectura de movimiento. */
    void alCambiarMovimiento(Luz luz, boolean hayMovimiento);
}
