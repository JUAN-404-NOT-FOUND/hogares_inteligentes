package co.edu.smarthome.service;

import co.edu.smarthome.model.event.EventoHogar;
import co.edu.smarthome.observer.ObservadorEventosHogar;
import co.edu.smarthome.repository.RepositorioEventos;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Recibe eventos del hogar, los guarda y avisa a sus observadores. */
public final class ServicioHistorial implements ObservadorEventosHogar {
    private final RepositorioEventos repositorio;
    private final List<ObservadorEventosHogar> observadores = new CopyOnWriteArrayList<>();

    public ServicioHistorial(RepositorioEventos repositorio) { this.repositorio = repositorio; }
    public void agregarObservador(ObservadorEventosHogar observador) { observadores.add(observador); }
    public void quitarObservador(ObservadorEventosHogar observador) { observadores.remove(observador); }
    public List<EventoHogar> listarRecientes(int limite) { return repositorio.listarRecientes(limite); }
    @Override public void alOcurrirEvento(EventoHogar evento) {
        repositorio.guardar(evento);
        observadores.forEach(observador -> observador.alOcurrirEvento(evento));
    }
}
