package co.edu.smarthome.factory;

import co.edu.smarthome.model.device.CerraduraInteligente;
import co.edu.smarthome.model.device.Dispositivo;

/** Factory Method para crear cerraduras inteligentes. */
public final class CreadorCerradura extends CreadorDispositivo {
    @Override public Dispositivo crear(String nombre, String habitacion) {
        return new CerraduraInteligente(nombre, habitacion);
    }
}
