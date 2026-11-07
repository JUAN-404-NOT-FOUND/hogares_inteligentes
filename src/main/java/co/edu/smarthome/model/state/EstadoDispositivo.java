package co.edu.smarthome.model.state;

public interface EstadoDispositivo {
    void encender(ContextoLuz luz);
    void apagar(ContextoLuz luz);
    String obtenerEtiqueta();
}
