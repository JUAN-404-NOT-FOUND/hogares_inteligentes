package co.edu.smarthome.service;

import co.edu.smarthome.model.home.Hogar;
import co.edu.smarthome.repository.RepositorioHogares;

import java.util.List;
import java.util.Optional;

/** Casos de uso para crear y consultar hogares. */
public final class ServicioHogares {
    private final RepositorioHogares repositorio;

    public ServicioHogares(RepositorioHogares repositorio) { this.repositorio = repositorio; }
    public List<Hogar> listar() { return repositorio.listarTodos(); }
    public Optional<Hogar> buscar(String id) { return repositorio.buscarPorId(id); }
    public void registrar(Hogar hogar) { repositorio.guardar(hogar); }
    public void eliminar(String id) { repositorio.eliminar(id); }
}
