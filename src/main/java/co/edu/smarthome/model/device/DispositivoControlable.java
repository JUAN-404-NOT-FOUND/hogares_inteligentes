package co.edu.smarthome.model.device;

/** Extiende el contrato de dispositivo con operaciones de encendido y apagado para Composite. */
public interface DispositivoControlable extends Dispositivo {
    /** Enciende el dispositivo. */
    void encender();
    /** Apaga el dispositivo. */
    void apagar();
}
