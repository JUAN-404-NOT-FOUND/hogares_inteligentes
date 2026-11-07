package co.edu.smarthome.repository;

import co.edu.smarthome.model.event.EventoHogar;

import java.util.List;

/** Define el almacenamiento y consulta del historial de actividad. */
public interface RepositorioEventos {
    List<EventoHogar> listarRecientes(int limite);
    void guardar(EventoHogar evento);
    void limpiar();
}
