package co.edu.smarthome.model.device;

import java.util.UUID;

/** Dispositivo regulable para establecer la temperatura de una habitación. */
public final class Termostato implements Dispositivo {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final String habitacion;
    private boolean activo;
    private double temperaturaObjetivo = 21.0;

    public Termostato(String nombre, String habitacion) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio.");
        if (habitacion == null || habitacion.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        this.nombre = nombre;
        this.habitacion = habitacion;
    }

    @Override public String obtenerId() { return id; }
    @Override public String obtenerNombre() { return nombre; }
    @Override public String obtenerHabitacion() { return habitacion; }
    @Override public String obtenerEtiquetaEstado() { return activo ? temperaturaObjetivo + " °C" : "Apagado"; }
    public boolean estaActivo() { return activo; }
    public double obtenerTemperaturaObjetivo() { return temperaturaObjetivo; }
    public void activar() { activo = true; }
    public void desactivar() { activo = false; }
    public void establecerTemperaturaObjetivo(double temperatura) {
        if (temperatura < 5 || temperatura > 35) throw new IllegalArgumentException("La temperatura debe estar entre 5 y 35 °C.");
        temperaturaObjetivo = temperatura;
        activo = true;
    }
}
