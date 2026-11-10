package co.edu.smarthome.service;

import co.edu.smarthome.command.InvocadorComandos;
import co.edu.smarthome.composite.GrupoDispositivos;
import co.edu.smarthome.mediator.MediadorHogar;
import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.model.event.EventoHogar;
import co.edu.smarthome.model.event.TipoEventoHogar;
import co.edu.smarthome.observer.ObservadorDispositivo;
import co.edu.smarthome.repository.memory.RepositorioDispositivosMemoria;
import co.edu.smarthome.repository.memory.RepositorioEventosMemoria;
import co.edu.smarthome.repository.memory.RepositorioHogaresMemoria;
import co.edu.smarthome.repository.memory.RepositorioReglasMemoria;
import co.edu.smarthome.strategy.EstrategiaEncenderLuzPorMovimiento;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Ensambla y comparte servicios, repositorios en memoria y coordinadores durante la ejecución. */
public final class ContextoAplicacion {
    private final ServicioHogares servicioHogares = new ServicioHogares(new RepositorioHogaresMemoria());
    private final ServicioDispositivos servicioDispositivos = new ServicioDispositivos(new RepositorioDispositivosMemoria());
    private final ServicioAutomatizaciones servicioAutomatizaciones = new ServicioAutomatizaciones(new RepositorioReglasMemoria());
    private final ServicioHistorial servicioHistorial = new ServicioHistorial(new RepositorioEventosMemoria());
    private final InvocadorComandos invocadorComandos = new InvocadorComandos();
    private final MediadorHogar mediadorHogar = new MediadorHogar();
    private final Map<String, ObservadorDispositivo> observadoresDeLuces = new HashMap<>();
    private final Luz luzSala = new Luz("Luz de la sala", "sala");
    private final ReglaAutomatizacion reglaSala = new ReglaAutomatizacion(
            "Control de luz de la sala", "sala", new EstrategiaEncenderLuzPorMovimiento());
    private GrupoDispositivos grupoLuces;

    public ContextoAplicacion() {
        registrarDispositivo(luzSala);
        servicioAutomatizaciones.registrar(reglaSala);
        mediadorHogar.registrarRegla(reglaSala);
    }

    public void registrarDispositivo(Dispositivo dispositivo) {
        Objects.requireNonNull(dispositivo, "El dispositivo es obligatorio.");
        servicioDispositivos.registrar(dispositivo);
        if (dispositivo instanceof Luz luz) {
            mediadorHogar.registrarLuz(luz);
            ObservadorDispositivo observador = (nombre, estado) -> registrarEvento(
                    TipoEventoHogar.ESTADO_DISPOSITIVO_CAMBIADO, luz.obtenerId(),
                    luz.obtenerHabitacion(), nombre + " cambió a " + estado + ".");
            observadoresDeLuces.put(luz.obtenerId(), observador);
            luz.agregarObservador(observador);
        }
        registrarEvento(TipoEventoHogar.DISPOSITIVO_CREADO, dispositivo.obtenerId(),
                dispositivo.obtenerHabitacion(), "Se agregó " + dispositivo.obtenerNombre() + ".");
    }

    public void eliminarDispositivo(Dispositivo dispositivo) {
        Objects.requireNonNull(dispositivo, "El dispositivo es obligatorio.");
        if (dispositivo instanceof Luz luz) {
            mediadorHogar.quitarLuz(luz);
            ObservadorDispositivo observador = observadoresDeLuces.remove(luz.obtenerId());
            if (observador != null) luz.eliminarObservador(observador);
            if (grupoLuces != null) {
                grupoLuces.quitar(luz);
                if (grupoLuces.estaVacio()) grupoLuces = null;
            }
        }
        servicioDispositivos.eliminar(dispositivo.obtenerId());
        registrarEvento(TipoEventoHogar.DISPOSITIVO_ELIMINADO, dispositivo.obtenerId(),
                dispositivo.obtenerHabitacion(), "Se eliminó " + dispositivo.obtenerNombre() + ".");
    }

    public void establecerGrupoLuces(GrupoDispositivos grupo) {
        grupoLuces = Objects.requireNonNull(grupo, "El grupo es obligatorio.");
        registrarEvento(TipoEventoHogar.GRUPO_CREADO, grupo.obtenerId(),
                grupo.obtenerHabitacion(), "Se creó " + grupo.obtenerNombre() + " con "
                        + grupo.obtenerMiembros().size() + " luces.");
    }

    public GrupoDispositivos obtenerGrupoLuces() { return grupoLuces; }

    public void registrarEvento(TipoEventoHogar tipo, String id, String habitacion, String descripcion) {
        servicioHistorial.alOcurrirEvento(EventoHogar.ahora(tipo, id, habitacion, descripcion));
    }

    public ServicioHogares obtenerServicioHogares() { return servicioHogares; }
    public ServicioDispositivos obtenerServicioDispositivos() { return servicioDispositivos; }
    public ServicioAutomatizaciones obtenerServicioAutomatizaciones() { return servicioAutomatizaciones; }
    public ServicioHistorial obtenerServicioHistorial() { return servicioHistorial; }
    public InvocadorComandos obtenerInvocadorComandos() { return invocadorComandos; }
    public MediadorHogar obtenerMediadorHogar() { return mediadorHogar; }
    public Luz obtenerLuzSala() { return luzSala; }
    public ReglaAutomatizacion obtenerReglaSala() { return reglaSala; }
}