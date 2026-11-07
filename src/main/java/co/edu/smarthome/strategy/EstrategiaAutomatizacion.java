package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

@FunctionalInterface
public interface EstrategiaAutomatizacion {
    void alCambiarMovimiento(Luz luz, boolean hayMovimiento);
}
