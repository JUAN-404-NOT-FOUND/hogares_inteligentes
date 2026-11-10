package co.edu.smarthome.command;

/** Contrato de una acción del hogar que puede ejecutarse y, cuando es posible, revertirse. */
public interface Command {
    /** Ejecuta la acción encapsulada. */
    void ejecutar();
    /** Revierte la acción usando el estado previo que conserva el comando. */
    void deshacer();
}
