package co.edu.smarthome.model.home;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Representa la vivienda que se administra desde la aplicación. */
public final class Hogar {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final List<Habitacion> habitaciones = new ArrayList<>();

    public Hogar(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del hogar es obligatorio.");
        this.nombre = nombre;
    }

    public String obtenerId() { return id; }
    public String obtenerNombre() { return nombre; }
    public List<Habitacion> obtenerHabitaciones() { return List.copyOf(habitaciones); }
    public void agregarHabitacion(Habitacion habitacion) { habitaciones.add(habitacion); }
    public void quitarHabitacion(Habitacion habitacion) { habitaciones.remove(habitacion); }
}
