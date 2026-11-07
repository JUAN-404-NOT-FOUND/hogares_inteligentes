package co.edu.smarthome.command;

import java.util.ArrayDeque;
import java.util.Deque;

/** Ejecuta acciones y conserva las necesarias para permitir deshacerlas. */
public final class InvocadorComandos {
    private final Deque<Command> historial = new ArrayDeque<>();

    public void ejecutar(Command comando) {
        if (comando == null) throw new IllegalArgumentException("El comando es obligatorio.");
        comando.ejecutar();
        historial.push(comando);
    }

    public boolean puedeDeshacer() { return !historial.isEmpty(); }

    public void deshacerUltimo() {
        if (puedeDeshacer()) historial.pop().deshacer();
    }

    public int cantidadEnHistorial() { return historial.size(); }
}
