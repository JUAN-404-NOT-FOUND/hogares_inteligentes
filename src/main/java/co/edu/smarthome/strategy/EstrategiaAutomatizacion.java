package co.edu.smarthome.strategy;

import co.edu.smarthome.model.device.Luz;

@FunctionalInterface
public interface EstrategiaAutomatizacion {
    void alDetectarMovimiento(Luz luz);
}
