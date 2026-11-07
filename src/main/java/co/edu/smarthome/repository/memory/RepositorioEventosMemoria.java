package co.edu.smarthome.repository.memory;

import co.edu.smarthome.model.event.EventoHogar;
import co.edu.smarthome.repository.RepositorioEventos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** Historial temporal; los eventos más nuevos se consultan primero. */
public final class RepositorioEventosMemoria implements RepositorioEventos {
    private final Deque<EventoHogar> eventos = new ArrayDeque<>();

    @Override public List<EventoHogar> listarRecientes(int limite) {
        if (limite < 0) throw new IllegalArgumentException("El límite no puede ser negativo.");
        return eventos.stream().limit(limite).toList();
    }

    @Override public void guardar(EventoHogar evento) { eventos.addFirst(evento); }
    @Override public void limpiar() { eventos.clear(); }
}
