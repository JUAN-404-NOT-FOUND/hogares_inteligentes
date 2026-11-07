package co.edu.smarthome.model.event;

import java.time.LocalDateTime;

/** Información común para notificaciones e historial de actividad. */
public record EventoHogar(TipoEventoHogar tipo, String idDispositivo, String habitacion,
                          String descripcion, LocalDateTime fecha) {
    public EventoHogar {
        if (tipo == null) throw new IllegalArgumentException("El tipo de evento es obligatorio.");
        if (fecha == null) fecha = LocalDateTime.now();
    }

    public static EventoHogar ahora(TipoEventoHogar tipo, String idDispositivo, String habitacion, String descripcion) {
        return new EventoHogar(tipo, idDispositivo, habitacion, descripcion, LocalDateTime.now());
    }
}
