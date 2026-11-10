package co.edu.smarthome.controller;

import co.edu.smarthome.model.event.EventoHogar;
import co.edu.smarthome.observer.ObservadorEventosHogar;
import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Muestra eventos recientes y observa cambios mientras esta vista está abierta. */
public final class ControladorHistorial extends ControladorNavegable implements ObservadorEventosHogar {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");
    @FXML private ListView<String> listaEventos;
    @FXML private Label etiquetaMensaje;

    public ControladorHistorial(ContextoAplicacion contexto, NavegadorHogar navegador) {
        super(contexto, navegador);
    }

    @FXML
    private void initialize() {
        contexto.obtenerServicioHistorial().agregarObservador(this);
        actualizarLista();
    }

    @Override public void alOcurrirEvento(EventoHogar evento) { actualizarLista(); }

    @Override
    public void alOcultar() {
        contexto.obtenerServicioHistorial().quitarObservador(this);
    }

    private void actualizarLista() {
        var eventos = contexto.obtenerServicioHistorial().listarRecientes(100);
        listaEventos.getItems().setAll(eventos.stream().map(evento ->
                evento.fecha().format(FORMATO) + " — " + evento.descripcion()
                        + " [" + evento.tipo().name().replace('_', ' ').toLowerCase(Locale.ROOT) + "]"
        ).toList());
        etiquetaMensaje.setText("Eventos recientes: " + eventos.size());
    }
}