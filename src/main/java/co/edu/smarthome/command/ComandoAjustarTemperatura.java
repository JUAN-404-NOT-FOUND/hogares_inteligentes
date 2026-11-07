package co.edu.smarthome.command;

import co.edu.smarthome.model.device.Termostato;

/** Acción reversible para cambiar el objetivo de un termostato. */
public final class ComandoAjustarTemperatura implements Command {
    private final Termostato termostato;
    private final double temperaturaDestino;
    private double temperaturaAnterior;
    private boolean estabaActivo;

    public ComandoAjustarTemperatura(Termostato termostato, double temperaturaDestino) {
        this.termostato = termostato;
        this.temperaturaDestino = temperaturaDestino;
    }

    @Override public void ejecutar() {
        temperaturaAnterior = termostato.obtenerTemperaturaObjetivo();
        estabaActivo = termostato.estaActivo();
        termostato.establecerTemperaturaObjetivo(temperaturaDestino);
    }

    @Override public void deshacer() {
        termostato.establecerTemperaturaObjetivo(temperaturaAnterior);
        if (!estabaActivo) termostato.desactivar();
    }
}
