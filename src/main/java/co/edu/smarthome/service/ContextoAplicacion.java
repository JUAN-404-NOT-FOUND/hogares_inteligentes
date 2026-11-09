package co.edu.smarthome.service;

import co.edu.smarthome.command.InvocadorComandos;
import co.edu.smarthome.mediator.MediadorHogar;
import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.repository.memory.RepositorioDispositivosMemoria;
import co.edu.smarthome.repository.memory.RepositorioEventosMemoria;
import co.edu.smarthome.repository.memory.RepositorioHogaresMemoria;
import co.edu.smarthome.repository.memory.RepositorioReglasMemoria;
import co.edu.smarthome.strategy.EstrategiaEncenderLuzPorMovimiento;

/**Comparte los servicios y objetos del hogar durante toda la ejecucion.*/
public final class ContextoAplicacion {
    private final ServicioHogares servicioHogares;
    private final ServicioDispositivos servicioDispositivos;
    private final ServicioAutomatizaciones servicioAutomatizaciones;
    private final ServicioHistorial servicioHistorial;
    private final InvocadorComandos invocadorComandos;
    private final MediadorHogar mediadorHogar;

    private final Luz luzSala;
    private final ReglaAutomatizacion reglaSala;

    public ContextoAplicacion() {
        servicioHogares = new ServicioHogares(new RepositorioHogaresMemoria());
        servicioDispositivos = new ServicioDispositivos(new RepositorioDispositivosMemoria());
        servicioAutomatizaciones = new ServicioAutomatizaciones(new RepositorioReglasMemoria());
        servicioHistorial = new ServicioHistorial(new RepositorioEventosMemoria());

        invocadorComandos = new InvocadorComandos();
        mediadorHogar = new MediadorHogar();

        luzSala = new Luz("Luz de la sala", "sala");
        reglaSala = new ReglaAutomatizacion(
                "Control de luz de la sala",
                "sala",
                new EstrategiaEncenderLuzPorMovimiento()
        );

        servicioDispositivos.registrar(luzSala);
        servicioAutomatizaciones.registrar(reglaSala);
        mediadorHogar.registrarLuz(luzSala);
        mediadorHogar.registrarRegla(reglaSala);
    }

    public ServicioHogares obtenerServicioHogares() {
        return servicioHogares;
    }

    public ServicioDispositivos obtenerServicioDispositivos() {
        return servicioDispositivos;
    }

    public ServicioAutomatizaciones obtenerServicioAutomatizaciones() {
        return servicioAutomatizaciones;
    }

    public ServicioHistorial obtenerServicioHistorial() {
        return servicioHistorial;
    }

    public InvocadorComandos obtenerInvocadorComandos() {
        return invocadorComandos;
    }

    public MediadorHogar obtenerMediadorHogar() {
        return mediadorHogar;
    }

    public Luz obtenerLuzSala() {
        return luzSala;
    }

    public ReglaAutomatizacion obtenerReglaSala() {
        return reglaSala;
    }
}
