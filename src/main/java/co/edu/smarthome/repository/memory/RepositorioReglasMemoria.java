package co.edu.smarthome.repository.memory;

import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.repository.RepositorioReglas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Implementación temporal en memoria para reglas. */
public final class RepositorioReglasMemoria implements RepositorioReglas {
    private final Map<String, ReglaAutomatizacion> reglas = new LinkedHashMap<>();

    @Override public List<ReglaAutomatizacion> listarTodas() { return List.copyOf(reglas.values()); }
    @Override public Optional<ReglaAutomatizacion> buscarPorId(String id) { return Optional.ofNullable(reglas.get(id)); }
    @Override public void guardar(ReglaAutomatizacion regla) { reglas.put(regla.obtenerId(), regla); }
    @Override public void eliminar(String id) { reglas.remove(id); }
}
