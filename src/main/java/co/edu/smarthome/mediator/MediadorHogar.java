package co.edu.smarthome.mediator;

import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.strategy.EstrategiaAutomatizacion;
import co.edu.smarthome.adapter.SensorMovimiento;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class MediadorHogar {
    private final EstrategiaAutomatizacion estrategiaAutomatizacion;
    private final Map<String, List<Luz>> lucesPorHabitacion = new ConcurrentHashMap<>();

    public MediadorHogar(EstrategiaAutomatizacion estrategiaAutomatizacion) {
        this.estrategiaAutomatizacion = estrategiaAutomatizacion;
    }

    public void alDetectarMovimiento(String habitacion, Luz luz) {
        if (luz.obtenerHabitacion().equalsIgnoreCase(habitacion)) estrategiaAutomatizacion.alDetectarMovimiento(luz);
    }

    public void registrarLuz(Luz luz) {
        lucesPorHabitacion.computeIfAbsent(luz.obtenerHabitacion().toLowerCase(), ignorada -> new CopyOnWriteArrayList<>())
                .add(luz);
    }

    /** Conecta la lectura del sensor con la estrategia de la habitación correspondiente. */
    public void procesarLectura(String habitacion, SensorMovimiento sensor) {
        if (!sensor.hayMovimiento()) return;
        lucesPorHabitacion.getOrDefault(habitacion.toLowerCase(), List.of())
                .forEach(luz -> alDetectarMovimiento(habitacion, luz));
    }
}
