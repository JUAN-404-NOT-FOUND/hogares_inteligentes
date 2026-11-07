package co.edu.smarthome.repository;

import co.edu.smarthome.model.home.Hogar;

import java.util.List;
import java.util.Optional;

/** Contrato de persistencia para las viviendas registradas. */
public interface RepositorioHogares {
    List<Hogar> listarTodos();
    Optional<Hogar> buscarPorId(String id);
    void guardar(Hogar hogar);
    void eliminar(String id);
}
