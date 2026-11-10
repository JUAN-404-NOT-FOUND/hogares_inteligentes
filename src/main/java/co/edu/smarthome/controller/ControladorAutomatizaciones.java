package co.edu.smarthome.controller;

import co.edu.smarthome.model.automation.ReglaAutomatizacion;
import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.service.ContextoAplicacion;
import co.edu.smarthome.strategy.EstrategiaAutomatizacion;
import co.edu.smarthome.strategy.EstrategiaEncenderLuzPorMovimiento;
import co.edu.smarthome.strategy.EstrategiaSeguirMovimiento;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public final class ControladorAutomatizaciones
        extends ControladorNavegable {

    private static final String ESTRATEGIA_ENCENDER =
            "Encender con movimiento";
    private static final String ESTRATEGIA_SEGUIR =
            "Encender y apagar según movimiento";

    @FXML private ListView<ReglaAutomatizacion> listaReglas;
    @FXML private Label etiquetaMensaje;

    public ControladorAutomatizaciones(
            ContextoAplicacion contexto,
            NavegadorHogar navegador
    ) {
        super(contexto, navegador);
    }

    @FXML
    private void initialize() {
        listaReglas.setCellFactory(ignorado -> new ListCell<>() {
            @Override
            protected void updateItem(ReglaAutomatizacion regla, boolean vacia) {
                super.updateItem(regla, vacia);

                if (vacia || regla == null) {
                    setText(null);
                } else {
                    String estado = regla.estaActiva() ? "Activa" : "Inactiva";
                    setText(regla.obtenerNombre()
                            + " — " + regla.obtenerHabitacion()
                            + " — " + nombreEstrategia(regla)
                            + " — " + estado);
                }
            }
        });

        actualizarLista();
        etiquetaMensaje.setText(
                "Reglas registradas: " + listaReglas.getItems().size()
        );
    }

    @FXML
    private void crearRegla() {
        List<String> habitacionesConLuces = contexto
                .obtenerServicioDispositivos()
                .listar()
                .stream()
                .filter(Luz.class::isInstance)
                .map(Dispositivo::obtenerHabitacion)
                .distinct()
                .toList();

        if (habitacionesConLuces.isEmpty()) {
            etiquetaMensaje.setText(
                    "Agrega primero una luz desde la pantalla Dispositivos."
            );
            return;
        }

        ChoiceDialog<String> selectorHabitacion = new ChoiceDialog<>(
                habitacionesConLuces.get(0),
                habitacionesConLuces
        );
        selectorHabitacion.setTitle("Crear automatización");
        selectorHabitacion.setHeaderText("Elige la habitación");
        selectorHabitacion.setContentText("Habitación:");

        Optional<String> habitacion = selectorHabitacion.showAndWait();
        if (habitacion.isEmpty()) {
            return;
        }

        TextInputDialog dialogoNombre = new TextInputDialog();
        dialogoNombre.setTitle("Crear automatización");
        dialogoNombre.setHeaderText(null);
        dialogoNombre.setContentText("Nombre de la regla:");

        Optional<String> nombreIngresado = dialogoNombre.showAndWait();
        if (nombreIngresado.isEmpty()) {
            return;
        }

        String nombre = nombreIngresado.get().trim();
        if (nombre.isBlank()) {
            etiquetaMensaje.setText("El nombre de la regla es obligatorio.");
            return;
        }

        Optional<String> estrategiaElegida = seleccionarEstrategia();
        if (estrategiaElegida.isEmpty()) {
            return;
        }

        EstrategiaAutomatizacion estrategia =
                crearEstrategia(estrategiaElegida.get());

        ReglaAutomatizacion regla = new ReglaAutomatizacion(
                nombre,
                habitacion.get(),
                estrategia
        );

        contexto.obtenerServicioAutomatizaciones().registrar(regla);
        contexto.obtenerMediadorHogar().registrarRegla(regla);

        actualizarLista();
        etiquetaMensaje.setText("Se creó la regla " + nombre + ".");
    }

    @FXML
    private void cambiarEstrategia() {
        ReglaAutomatizacion seleccionada =
                listaReglas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            etiquetaMensaje.setText(
                    "Selecciona una regla para cambiar su estrategia."
            );
            return;
        }

        Optional<String> estrategiaElegida = seleccionarEstrategia();
        if (estrategiaElegida.isEmpty()) {
            return;
        }

        seleccionada.cambiarEstrategia(
                crearEstrategia(estrategiaElegida.get())
        );

        actualizarLista();
        etiquetaMensaje.setText(
                "Se cambió la estrategia de "
                        + seleccionada.obtenerNombre() + "."
        );
    }

    @FXML
    private void cambiarEstadoRegla() {
        ReglaAutomatizacion seleccionada =
                listaReglas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            etiquetaMensaje.setText(
                    "Selecciona una regla para activarla o desactivarla."
            );
            return;
        }

        if (seleccionada.estaActiva()) {
            seleccionada.desactivar();
            etiquetaMensaje.setText(
                    "Se desactivó " + seleccionada.obtenerNombre() + "."
            );
        } else {
            seleccionada.activar();
            etiquetaMensaje.setText(
                    "Se activó " + seleccionada.obtenerNombre() + "."
            );
        }

        actualizarLista();
    }

    private Optional<String> seleccionarEstrategia() {
        ChoiceDialog<String> selector = new ChoiceDialog<>(
                ESTRATEGIA_ENCENDER,
                ESTRATEGIA_ENCENDER,
                ESTRATEGIA_SEGUIR
        );
        selector.setTitle("Estrategia de automatización");
        selector.setHeaderText("Elige cómo reaccionará la luz");
        selector.setContentText("Estrategia:");

        return selector.showAndWait();
    }

    private EstrategiaAutomatizacion crearEstrategia(String nombre) {
        return switch (nombre) {
            case ESTRATEGIA_ENCENDER ->
                    new EstrategiaEncenderLuzPorMovimiento();
            case ESTRATEGIA_SEGUIR ->
                    new EstrategiaSeguirMovimiento();
            default -> throw new IllegalArgumentException(
                    "Estrategia no reconocida: " + nombre
            );
        };
    }

    private String nombreEstrategia(ReglaAutomatizacion regla) {
        return regla.obtenerEstrategia()
                instanceof EstrategiaSeguirMovimiento
                ? ESTRATEGIA_SEGUIR
                : ESTRATEGIA_ENCENDER;
    }

    private void actualizarLista() {
        listaReglas.getItems().setAll(
                contexto.obtenerServicioAutomatizaciones().listar()
        );
    }
}