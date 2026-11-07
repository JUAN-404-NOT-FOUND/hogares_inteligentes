package co.edu.smarthome.repository;

import co.edu.smarthome.model.automation.ReglaAutomatizacion;

import java.util.List;
import java.util.Optional;

/** Contrato de persistencia para las reglas de automatización. */
public interface RepositorioReglas {
    List<ReglaAutomatizacion> listarTodas();
    Optional<ReglaAutomatizacion> buscarPorId(String id);
    void guardar(ReglaAutomatizacion regla);
    void eliminar(String id);
}
