package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.Dispositivo;

/** Creador abstracto del patrón Factory Method para instanciar dispositivos del hogar. */
public abstract class CreadorDispositivo {
    public abstract Dispositivo crear(String nombre, String habitacion);
}
