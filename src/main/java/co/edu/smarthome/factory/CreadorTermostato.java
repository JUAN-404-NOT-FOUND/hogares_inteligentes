package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Termostato;

/** Factory Method para crear termostatos sin acoplar el cliente a su constructor. */
public final class CreadorTermostato extends CreadorDispositivo {
    @Override public Dispositivo crear(String nombre, String habitacion) {
        return new Termostato(nombre, habitacion);
    }
}
