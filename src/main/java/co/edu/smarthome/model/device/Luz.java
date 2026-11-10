package co.edu.smarthome.model.device;

import co.edu.smarthome.model.state.EstadoDispositivo;
import co.edu.smarthome.model.state.ContextoLuz;
import co.edu.smarthome.model.state.EstadoApagado;
import co.edu.smarthome.observer.ObservadorDispositivo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Luz implements DispositivoControlable, ContextoLuz {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final String habitacion;
    private final List<ObservadorDispositivo> observadores = new CopyOnWriteArrayList<>();
    private EstadoDispositivo estado = new EstadoApagado();

    public Luz(String nombre, String habitacion) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio.");
        if (habitacion == null || habitacion.isBlank()) throw new IllegalArgumentException("La habitación es obligatoria.");
        this.nombre = nombre;
        this.habitacion = habitacion;
    }

    @Override public String obtenerId() { return id; }
    @Override public String obtenerNombre() { return nombre; }
    @Override public String obtenerHabitacion() { return habitacion; }
    @Override public String obtenerEtiquetaEstado() { return estado.obtenerEtiqueta(); }
    public EstadoDispositivo obtenerEstado() { return estado; }

    public void agregarObservador(ObservadorDispositivo observador) { observadores.add(observador); }
    public void eliminarObservador(ObservadorDispositivo observador) { observadores.remove(observador); }

    @Override public void encender() { estado.encender(this); }
    @Override public void apagar() { estado.apagar(this); }

    @Override
    public void cambiarEstado(EstadoDispositivo nuevoEstado) {
        if (estado.getClass() == nuevoEstado.getClass()) return;
        estado = nuevoEstado;
        observadores.forEach(observador -> observador.alCambiarEstadoDispositivo(nombre, estado.obtenerEtiqueta()));
    }
}
