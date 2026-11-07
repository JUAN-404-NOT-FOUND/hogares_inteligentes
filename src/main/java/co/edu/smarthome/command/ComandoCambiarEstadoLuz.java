package co.edu.smarthome.command;

import co.edu.smarthome.model.device.Luz;

public final class ComandoCambiarEstadoLuz implements Command {
    private final Luz luz;
    private final boolean encenderDestino;
    private boolean estabaEncendida;

    public ComandoCambiarEstadoLuz(Luz luz, boolean encenderDestino) {
        this.luz = luz;
        this.encenderDestino = encenderDestino;
    }

    @Override
    public void ejecutar() {
        estabaEncendida = "Encendida".equals(luz.obtenerEtiquetaEstado());
        if (encenderDestino) luz.encender(); else luz.apagar();
    }

    @Override
    public void deshacer() {
        if (estabaEncendida) luz.encender(); else luz.apagar();
    }
}
