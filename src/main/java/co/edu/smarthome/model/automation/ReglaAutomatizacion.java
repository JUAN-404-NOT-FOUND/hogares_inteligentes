package co.edu.smarthome.model.automation;

import co.edu.smarthome.strategy.EstrategiaAutomatizacion;

import java.util.UUID;

/** Regla configurable que asocia una habitación con una estrategia para movimiento. */
public final class ReglaAutomatizacion {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final String habitacion;
    private EstrategiaAutomatizacion estrategia;
    private boolean activa = true;

    public ReglaAutomatizacion(String nombre, String habitacion, EstrategiaAutomatizacion estrategia) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre de la regla es obligatorio.");
        if (habitacion == null || habitacion.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        this.nombre = nombre;
        this.habitacion = habitacion;
        cambiarEstrategia(estrategia);
    }

    public String obtenerId() { return id; }
    public String obtenerNombre() { return nombre; }
    public String obtenerHabitacion() { return habitacion; }
    public EstrategiaAutomatizacion obtenerEstrategia() { return estrategia; }
    public void cambiarEstrategia(EstrategiaAutomatizacion estrategia) {
        if (estrategia == null) throw new IllegalArgumentException("La estrategia es obligatoria.");
        this.estrategia = estrategia;
    }
    public boolean estaActiva() { return activa; }
    public void activar() { activa = true; }
    public void desactivar() { activa = false; }
}
