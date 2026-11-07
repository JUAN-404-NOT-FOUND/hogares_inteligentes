package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

/** Enciende la luz con movimiento y la apaga cuando la habitación queda vacía. */
public final class EstrategiaSeguirMovimiento implements EstrategiaAutomatizacion {
    @Override public void alCambiarMovimiento(Luz luz, boolean hayMovimiento) {
        if (hayMovimiento) luz.encender(); else luz.apagar();
    }
}
