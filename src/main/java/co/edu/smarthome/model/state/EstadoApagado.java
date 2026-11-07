package co.edu.smarthome.model.state;

public final class EstadoApagado implements EstadoDispositivo {
    @Override public void encender(ContextoLuz luz) { luz.cambiarEstado(new EstadoEncendido()); }
    @Override public void apagar(ContextoLuz luz) { }
    @Override public String obtenerEtiqueta() { return "Apagada"; }
}
