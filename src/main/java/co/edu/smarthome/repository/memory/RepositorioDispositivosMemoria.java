package co.edu.smarthome.repository.memory;

import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.repository.RepositorioDispositivos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Implementación temporal en memoria; el equipo puede reemplazarla por persistencia real. */
public final class RepositorioDispositivosMemoria implements RepositorioDispositivos {
    private final Map<String, Dispositivo> dispositivos = new LinkedHashMap<>();

    @Override public List<Dispositivo> listarTodos() { return List.copyOf(dispositivos.values()); }
    @Override public Optional<Dispositivo> buscarPorId(String id) { return Optional.ofNullable(dispositivos.get(id)); }
    @Override public void guardar(Dispositivo dispositivo) { dispositivos.put(dispositivo.obtenerId(), dispositivo); }
    @Override public void eliminar(String id) { dispositivos.remove(id); }
}
