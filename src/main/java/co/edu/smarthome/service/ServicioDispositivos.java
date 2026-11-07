package co.edu.smarthome.service;

import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.repository.RepositorioDispositivos;

import java.util.List;
import java.util.Optional;

/** Casos de uso básicos para administrar dispositivos del hogar. */
public final class ServicioDispositivos {
    private final RepositorioDispositivos repositorio;

    public ServicioDispositivos(RepositorioDispositivos repositorio) { this.repositorio = repositorio; }
    public List<Dispositivo> listar() { return repositorio.listarTodos(); }
    public Optional<Dispositivo> buscar(String id) { return repositorio.buscarPorId(id); }
    public void registrar(Dispositivo dispositivo) { repositorio.guardar(dispositivo); }
    public void eliminar(String id) { repositorio.eliminar(id); }
}
