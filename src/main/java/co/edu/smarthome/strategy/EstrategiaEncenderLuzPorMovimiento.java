package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

/** Estrategia que enciende la luz cuando detecta movimiento y no modifica la luz al quedar vacía la sala. */
public final class EstrategiaEncenderLuzPorMovimiento implements EstrategiaAutomatizacion {
    @Override public void alCambiarMovimiento(Luz luz, boolean hayMovimiento) {
        if (hayMovimiento) luz.encender();
    }
}
