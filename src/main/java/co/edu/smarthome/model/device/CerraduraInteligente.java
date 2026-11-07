package co.edu.smarthome.model.device;

import java.util.UUID;

/** Cerradura que permite consultar y cambiar el bloqueo de una puerta. */
public final class CerraduraInteligente implements Dispositivo {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final String habitacion;
    private boolean bloqueada = true;

    public CerraduraInteligente(String nombre, String habitacion) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio.");
        if (habitacion == null || habitacion.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        this.nombre = nombre;
        this.habitacion = habitacion;
    }

    @Override public String obtenerId() { return id; }
    @Override public String obtenerNombre() { return nombre; }
    @Override public String obtenerHabitacion() { return habitacion; }
    @Override public String obtenerEtiquetaEstado() { return bloqueada ? "Bloqueada" : "Desbloqueada"; }
    public boolean estaBloqueada() { return bloqueada; }
    public void bloquear() { bloqueada = true; }
    public void desbloquear() { bloqueada = false; }
}
