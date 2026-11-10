package co.edu.smarthome.model.state;

/** Estado de una luz apagada; permite encenderla y conserva la etiqueta visible del estado. */
public final class EstadoApagado implements EstadoDispositivo {
    @Override public void encender(ContextoLuz luz) { luz.cambiarEstado(new EstadoEncendido()); }
    @Override public void apagar(ContextoLuz luz) { }
    @Override public String obtenerEtiqueta() { return "Apagada"; }
}
