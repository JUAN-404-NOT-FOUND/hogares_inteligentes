package co.edu.smarthome.service;

import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.repository.RepositorioReglas;

import java.util.List;
import java.util.Optional;

/** Casos de uso para guardar, consultar y activar reglas de automatización. */
public final class ServicioAutomatizaciones {
    private final RepositorioReglas repositorio;

    public ServicioAutomatizaciones(RepositorioReglas repositorio) { this.repositorio = repositorio; }
    public List<ReglaAutomatizacion> listar() { return repositorio.listarTodas(); }
    public Optional<ReglaAutomatizacion> buscar(String id) { return repositorio.buscarPorId(id); }
    public void registrar(ReglaAutomatizacion regla) { repositorio.guardar(regla); }
    public void eliminar(String id) { repositorio.eliminar(id); }
}
