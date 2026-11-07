package co.edu.smarthome.observer;

import co.edu.smarthome.model.event.EventoHogar;

/** Contrato Observer para que interfaz e historial reaccionen a sucesos del hogar. */
@FunctionalInterface
public interface ObservadorEventosHogar {
    void alOcurrirEvento(EventoHogar evento);
}
