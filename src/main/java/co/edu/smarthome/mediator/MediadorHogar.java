package co.edu.smarthome.mediator;

import co.edu.smarthome.adapter.SensorMovimiento;
import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.model.device.Luz;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Coordina sensores, reglas de automatización y luces, sin acoplarlos entre sí. */
public final class MediadorHogar {
    private final Map<String, List<Luz>> lucesPorHabitacion = new ConcurrentHashMap<>();
    private final Map<String, List<ReglaAutomatizacion>> reglasPorHabitacion = new ConcurrentHashMap<>();

    public void registrarLuz(Luz luz) {
        Objects.requireNonNull(luz, "La luz es obligatoria.");
        lucesPorHabitacion.computeIfAbsent(normalizar(luz.obtenerHabitacion()), ignorada -> new CopyOnWriteArrayList<>())
                .add(luz);
    }

    public void registrarRegla(ReglaAutomatizacion regla) {
        Objects.requireNonNull(regla, "La regla es obligatoria.");
        reglasPorHabitacion.computeIfAbsent(normalizar(regla.obtenerHabitacion()), ignorada -> new CopyOnWriteArrayList<>())
                .add(regla);
    }

    /** Lee el sensor y aplica las reglas activas solo a los dispositivos de esa habitación. */
    public void procesarLectura(String habitacion, SensorMovimiento sensor) {
        Objects.requireNonNull(sensor, "El sensor es obligatorio.");
        String clave = normalizar(habitacion);
        boolean hayMovimiento = sensor.hayMovimiento();
        List<Luz> luces = lucesPorHabitacion.getOrDefault(clave, List.of());
        List<ReglaAutomatizacion> reglas = reglasPorHabitacion.getOrDefault(clave, List.of());

        for (ReglaAutomatizacion regla : reglas) {
            if (!regla.estaActiva()) continue;
            for (Luz luz : luces) {
                regla.obtenerEstrategia().alCambiarMovimiento(luz, hayMovimiento);
            }
        }
    }

    private static String normalizar(String texto) {
        if (texto == null || texto.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        return texto.trim().toLowerCase(Locale.ROOT);
    }
}
