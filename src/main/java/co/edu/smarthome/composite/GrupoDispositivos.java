package co.edu.smarthome.composite;

import co.edu.smarthome.model.device.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public final class GrupoDispositivos implements Dispositivo {
    private final String nombre;
    private final List<Dispositivo> dispositivos = new ArrayList<>();

    public GrupoDispositivos(String nombre) { this.nombre = nombre; }
    @Override public String obtenerId() { return "grupo-" + nombre.toLowerCase().replace(' ', '-'); }
    @Override public String obtenerNombre() { return nombre; }
    @Override public String obtenerHabitacion() { return "Grupo"; }
    @Override public String obtenerEtiquetaEstado() {
        long cantidadEncendida = dispositivos.stream().filter(d -> "Encendida".equals(d.obtenerEtiquetaEstado())).count();
        return cantidadEncendida + " de " + dispositivos.size() + " dispositivos encendidos";
    }
    public void agregar(Dispositivo dispositivo) { dispositivos.add(dispositivo); }
    public void quitar(Dispositivo dispositivo) { dispositivos.remove(dispositivo); }
    public List<Dispositivo> obtenerDispositivos() { return List.copyOf(dispositivos); }
}
