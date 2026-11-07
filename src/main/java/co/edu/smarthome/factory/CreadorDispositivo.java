package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.Dispositivo;

public abstract class CreadorDispositivo {
    public abstract Dispositivo crear(String nombre, String habitacion);
}
