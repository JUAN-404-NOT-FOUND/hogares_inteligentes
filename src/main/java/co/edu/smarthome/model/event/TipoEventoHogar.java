package co.edu.smarthome.model.event;

/** Categorías de sucesos que pueden guardarse en el historial del hogar. */
public enum TipoEventoHogar {
    DISPOSITIVO_CREADO,
    DISPOSITIVO_ELIMINADO,
    ESTADO_DISPOSITIVO_CAMBIADO,
    MOVIMIENTO_DETECTADO,
    AUTOMATIZACION_EJECUTADA,
    AUTOMATIZACION_CONFIGURADA,
    GRUPO_CREADO,
    ACCION_GRUPO_EJECUTADA
}