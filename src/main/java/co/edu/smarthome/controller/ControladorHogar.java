package co.edu.smarthome.controller;

import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.observer.ObservadorDispositivo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorHogar implements ObservadorDispositivo {
    @FXML private Label etiquetaEstado;
    @FXML private Label etiquetaMensaje;

    private final Luz luzSala = new Luz("Luz de la sala", "sala");

    @FXML
    private void initialize() {
        luzSala.agregarObservador(this);
        actualizarEstado();
    }

    @FXML
    private void encender() {
        luzSala.encender();
    }

    @FXML
    private void apagar() {
        luzSala.apagar();
    }

    private void actualizarEstado() {
        etiquetaEstado.setText(luzSala.obtenerNombre() + " · " + luzSala.obtenerEstado().obtenerEtiqueta());
    }

    @Override
    public void alCambiarEstadoDispositivo(String nombreDispositivo, String etiquetaActual) {
        actualizarEstado();
        etiquetaMensaje.setText(nombreDispositivo + " cambió a " + etiquetaActual.toLowerCase() + ".");
    }
}
