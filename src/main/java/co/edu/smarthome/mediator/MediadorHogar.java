package co.edu.smarthome.mediator;

import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.strategy.EstrategiaAutomatizacion;

public final class MediadorHogar {
    private final EstrategiaAutomatizacion estrategiaAutomatizacion;

    public MediadorHogar(EstrategiaAutomatizacion estrategiaAutomatizacion) {
        this.estrategiaAutomatizacion = estrategiaAutomatizacion;
    }

    public void alDetectarMovimiento(String habitacion, Luz luz) {
        if (luz.obtenerHabitacion().equalsIgnoreCase(habitacion)) estrategiaAutomatizacion.alDetectarMovimiento(luz);
    }
}
