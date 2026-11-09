package co.edu.smarthome.controller;

import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/** Punto de continuación para la pantalla de administración de dispositivos. */
public final class ControladorDispositivos extends ControladorNavegable {
    @FXML private ListView<String> listaDispositivos;
    @FXML private Label etiquetaMensaje;

    public ControladorDispositivos(ContextoAplicacion contexto, NavegadorHogar navegador){
        super(contexto, navegador);
    }

    @FXML private void initialize() {
        etiquetaMensaje.setText("Conecta esta pantalla con ServicioDispositivos.");
    }

    @FXML private void agregarDispositivo() {
        etiquetaMensaje.setText("Pendiente: abrir el formulario para crear un dispositivo.");
    }

    @FXML private void eliminarDispositivo() {
        String seleccionado = listaDispositivos.getSelectionModel().getSelectedItem();
        etiquetaMensaje.setText(seleccionado == null
                ? "Selecciona un dispositivo para continuar."
                : "Pendiente: eliminar " + seleccionado + " desde ServicioDispositivos.");
    }
}
