package co.edu.smarthome.model.state;

public final class EstadoEncendido implements EstadoDispositivo {
    @Override public void encender(ContextoLuz luz) { }
    @Override public void apagar(ContextoLuz luz) { luz.cambiarEstado(new EstadoApagado()); }
    @Override public String obtenerEtiqueta() { return "Encendida"; }
}
