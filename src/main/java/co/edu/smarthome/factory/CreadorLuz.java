package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Luz;

/** Creador concreto que construye luces para una habitación. */
public final class CreadorLuz extends CreadorDispositivo {
    @Override
    public Dispositivo crear(String nombre, String habitacion) {
        return new Luz(nombre, habitacion);
    }
}
