package co.edu.smarthome.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Punto de continuación para configurar y activar reglas. */
public final class ControladorAutomatizaciones {
    @FXML private ListView<String> listaReglas;
    @FXML private Label etiquetaMensaje;

    @FXML private void initialize() {
        etiquetaMensaje.setText("Conecta esta pantalla con ServicioAutomatizaciones.");
    }

    @FXML private void crearRegla() {
        etiquetaMensaje.setText("Pendiente: crear una regla y elegir su estrategia.");
    }

    @FXML private void cambiarEstadoRegla() {
        String seleccionada = listaReglas.getSelectionModel().getSelectedItem();
        etiquetaMensaje.setText(seleccionada == null
                ? "Selecciona una regla para continuar."
                : "Pendiente: activar o desactivar " + seleccionada + ".");
    }
}
