package co.edu.smarthome.repository.memory;

import co.edu.smarthome.model.home.Hogar;
import co.edu.smarthome.repository.RepositorioHogares;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Implementación temporal en memoria para hogares. */
public final class RepositorioHogaresMemoria implements RepositorioHogares {
    private final Map<String, Hogar> hogares = new LinkedHashMap<>();

    @Override public List<Hogar> listarTodos() { return List.copyOf(hogares.values()); }
    @Override public Optional<Hogar> buscarPorId(String id) { return Optional.ofNullable(hogares.get(id)); }
    @Override public void guardar(Hogar hogar) { hogares.put(hogar.obtenerId(), hogar); }
    @Override public void eliminar(String id) { hogares.remove(id); }
}
