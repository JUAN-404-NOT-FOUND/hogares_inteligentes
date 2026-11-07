package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

public final class EstrategiaEncenderLuzPorMovimiento implements EstrategiaAutomatizacion {
    @Override public void alCambiarMovimiento(Luz luz, boolean hayMovimiento) {
        if (hayMovimiento) luz.encender();
    }
}
