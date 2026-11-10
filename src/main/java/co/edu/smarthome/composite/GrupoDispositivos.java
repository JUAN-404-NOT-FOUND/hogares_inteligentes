package co.edu.smarthome.composite;

import co.edu.smarthome.model.device.DispositivoControlable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** Composite de dispositivos controlables que permite operar grupos de luces. */
public final class GrupoDispositivos implements DispositivoControlable {
    private final String id = UUID.randomUUID().toString();
    private final String nombre;
    private final List<DispositivoControlable> miembros = new ArrayList<>();

    public GrupoDispositivos(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del grupo es obligatorio.");
        this.nombre = nombre.trim();
    }

    @Override public String obtenerId() { return id; }
    @Override public String obtenerNombre() { return nombre; }
    @Override public String obtenerHabitacion() { return "Varias habitaciones"; }

    @Override
    public String obtenerEtiquetaEstado() {
        return contarEncendidos() + " de " + contarMiembros() + " dispositivos encendidos";
    }

    @Override public void encender() { miembros.forEach(DispositivoControlable::encender); }
    @Override public void apagar() { miembros.forEach(DispositivoControlable::apagar); }

    public void agregar(DispositivoControlable miembro) {
        Objects.requireNonNull(miembro, "El dispositivo del grupo es obligatorio.");
        if (miembro == this || contieneGrupo(miembro, this)) {
            throw new IllegalArgumentException("Un grupo no puede contenerse a sí mismo.");
        }
        if (!miembros.contains(miembro)) miembros.add(miembro);
    }

    public void quitar(DispositivoControlable miembro) { miembros.remove(miembro); }
    public List<DispositivoControlable> obtenerMiembros() { return List.copyOf(miembros); }
    public boolean estaVacio() { return miembros.isEmpty(); }

    private int contarMiembros() {
        int total = 0;
        for (DispositivoControlable miembro : miembros) {
            total += miembro instanceof GrupoDispositivos grupo ? grupo.contarMiembros() : 1;
        }
        return total;
    }

    private int contarEncendidos() {
        int total = 0;
        for (DispositivoControlable miembro : miembros) {
            if (miembro instanceof GrupoDispositivos grupo) total += grupo.contarEncendidos();
            else if ("Encendida".equals(miembro.obtenerEtiquetaEstado())) total++;
        }
        return total;
    }

    private static boolean contieneGrupo(DispositivoControlable actual, GrupoDispositivos buscado) {
        if (actual == buscado) return true;
        return actual instanceof GrupoDispositivos grupo
                && grupo.miembros.stream().anyMatch(miembro -> contieneGrupo(miembro, buscado));
    }
}