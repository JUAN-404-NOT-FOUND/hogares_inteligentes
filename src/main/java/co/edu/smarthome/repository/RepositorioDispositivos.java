package co.edu.smarthome.repository;

import co.edu.smarthome.model.device.Dispositivo;

import java.util.List;
import java.util.Optional;

/** Define cómo consultar y guardar dispositivos, sin fijar todavía una base de datos. */
public interface RepositorioDispositivos {
    List<Dispositivo> listarTodos();
    Optional<Dispositivo> buscarPorId(String id);
    void guardar(Dispositivo dispositivo);
    void eliminar(String id);
}
