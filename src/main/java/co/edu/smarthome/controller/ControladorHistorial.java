package co.edu.smarthome.controller;

import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Punto de continuación para mostrar los eventos recientes del hogar. */
public final class ControladorHistorial extends ControladorNavegable {
    @FXML private ListView<String> listaEventos;
    @FXML private Label etiquetaMensaje;

    public ControladorHistorial(ContextoAplicacion contexto, NavegadorHogar navegador){
        super(contexto, navegador);
    }

    @FXML private void initialize() {
        etiquetaMensaje.setText("Conecta esta pantalla con ServicioHistorial.");
    }
}
