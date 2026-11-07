package co.edu.smarthome.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Punto de continuación para mostrar los eventos recientes del hogar. */
public final class ControladorHistorial {
    @FXML private ListView<String> listaEventos;
    @FXML private Label etiquetaMensaje;

    @FXML private void initialize() {
        etiquetaMensaje.setText("Conecta esta pantalla con ServicioHistorial.");
    }
}
