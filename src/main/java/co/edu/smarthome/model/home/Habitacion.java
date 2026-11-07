package co.edu.smarthome.model.home;

import co.edu.smarthome.model.device.Dispositivo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Agrupa los dispositivos instalados en una habitación. */
public final class Habitacion {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final List<Dispositivo> dispositivos = new ArrayList<>();

    public Habitacion(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre de la habitación es obligatorio.");
        this.nombre = nombre;
    }

    public String obtenerId() { return id; }
    public String obtenerNombre() { return nombre; }
    public List<Dispositivo> obtenerDispositivos() { return List.copyOf(dispositivos); }
    public void agregarDispositivo(Dispositivo dispositivo) { dispositivos.add(dispositivo); }
    public void quitarDispositivo(Dispositivo dispositivo) { dispositivos.remove(dispositivo); }
}
