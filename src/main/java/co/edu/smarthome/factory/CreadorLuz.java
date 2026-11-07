package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Luz;

public final class CreadorLuz extends CreadorDispositivo {
    @Override
    public Dispositivo crear(String nombre, String habitacion) {
        return new Luz(nombre, habitacion);
    }
}
