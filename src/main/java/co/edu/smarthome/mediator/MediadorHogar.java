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

/** Coordina sensores, reglas y luces; mantiene una regla activa por habitación. */
public final class MediadorHogar {
    private final Map<String, List<Luz>> lucesPorHabitacion = new ConcurrentHashMap<>();
    private final Map<String, List<ReglaAutomatizacion>> reglasPorHabitacion = new ConcurrentHashMap<>();

    public void registrarLuz(Luz luz) {
        Objects.requireNonNull(luz, "La luz es obligatoria.");
        lucesPorHabitacion.computeIfAbsent(normalizar(luz.obtenerHabitacion()),
                ignorada -> new CopyOnWriteArrayList<>()).add(luz);
    }

    public void quitarLuz(Luz luz) {
        Objects.requireNonNull(luz, "La luz es obligatoria.");
        String clave = normalizar(luz.obtenerHabitacion());
        List<Luz> luces = lucesPorHabitacion.get(clave);
        if (luces == null) return;
        luces.remove(luz);
        if (luces.isEmpty()) lucesPorHabitacion.remove(clave, luces);
    }

    /** Si ya hay una regla activa, la nueva se registra inactiva. */
    public void registrarRegla(ReglaAutomatizacion regla) {
        Objects.requireNonNull(regla, "La regla es obligatoria.");
        List<ReglaAutomatizacion> reglas = reglasPorHabitacion.computeIfAbsent(
                normalizar(regla.obtenerHabitacion()), ignorada -> new CopyOnWriteArrayList<>());
        if (reglas.contains(regla)) return;
        if (regla.estaActiva() && reglas.stream().anyMatch(ReglaAutomatizacion::estaActiva)) {
            regla.desactivar();
        }
        reglas.add(regla);
    }

    /** Activa una regla y desactiva todas las demás de su habitación. */
    public void activarRegla(ReglaAutomatizacion regla) {
        Objects.requireNonNull(regla, "La regla es obligatoria.");
        List<ReglaAutomatizacion> reglas = reglasPorHabitacion.get(normalizar(regla.obtenerHabitacion()));
        if (reglas == null || !reglas.contains(regla)) {
            throw new IllegalArgumentException("La regla no está registrada en el mediador.");
        }
        reglas.forEach(otra -> { if (otra != regla) otra.desactivar(); });
        regla.activar();
    }

    public void procesarLectura(String habitacion, SensorMovimiento sensor) {
        Objects.requireNonNull(sensor, "El sensor es obligatorio.");
        String clave = normalizar(habitacion);
        boolean hayMovimiento = sensor.hayMovimiento();
        List<Luz> luces = lucesPorHabitacion.getOrDefault(clave, List.of());
        List<ReglaAutomatizacion> reglas = reglasPorHabitacion.getOrDefault(clave, List.of());
        for (ReglaAutomatizacion regla : reglas) {
            if (!regla.estaActiva()) continue;
            for (Luz luz : luces) regla.obtenerEstrategia().alCambiarMovimiento(luz, hayMovimiento);
        }
    }

    private static String normalizar(String texto) {
        if (texto == null || texto.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        return texto.trim().toLowerCase(Locale.ROOT);
    }
}