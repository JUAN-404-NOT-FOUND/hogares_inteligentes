package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

public final class EstrategiaEncenderLuzPorMovimiento implements EstrategiaAutomatizacion {
    @Override public void alDetectarMovimiento(Luz luz) { luz.encender(); }
}
