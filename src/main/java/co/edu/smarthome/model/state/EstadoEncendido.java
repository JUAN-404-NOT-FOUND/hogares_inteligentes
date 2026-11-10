package co.edu.smarthome.model.state;

/** Estado de una luz encendida; evita transiciones redundantes y permite apagarla. */
public final class EstadoEncendido implements EstadoDispositivo {
    @Override public void encender(ContextoLuz luz) { }
    @Override public void apagar(ContextoLuz luz) { luz.cambiarEstado(new EstadoApagado()); }
    @Override public String obtenerEtiqueta() { return "Encendida"; }
}
