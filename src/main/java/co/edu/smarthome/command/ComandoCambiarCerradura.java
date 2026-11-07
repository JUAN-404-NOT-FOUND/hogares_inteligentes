package co.edu.smarthome.command;

import co.edu.smarthome.model.device.CerraduraInteligente;

/** Acción reversible para bloquear o desbloquear una cerradura. */
public final class ComandoCambiarCerradura implements Command {
    private final CerraduraInteligente cerradura;
    private final boolean bloquearDestino;
    private boolean estabaBloqueada;

    public ComandoCambiarCerradura(CerraduraInteligente cerradura, boolean bloquearDestino) {
        this.cerradura = cerradura;
        this.bloquearDestino = bloquearDestino;
    }

    @Override public void ejecutar() {
        estabaBloqueada = cerradura.estaBloqueada();
        if (bloquearDestino) cerradura.bloquear(); else cerradura.desbloquear();
    }

    @Override public void deshacer() {
        if (estabaBloqueada) cerradura.bloquear(); else cerradura.desbloquear();
    }
}
