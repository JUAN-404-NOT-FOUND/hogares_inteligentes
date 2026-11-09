package co.edu.smarthome.controller;

import co.edu.smarthome.adapter.AdaptadorSensorMovimiento;
import co.edu.smarthome.adapter.SensorMovimientoAntiguo;
import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.observer.ObservadorDispositivo;
import co.edu.smarthome.strategy.EstrategiaEncenderLuzPorMovimiento;
import co.edu.smarthome.strategy.EstrategiaSeguirMovimiento;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControladorHogar extends ControladorNavegable
        implements ObservadorDispositivo {

    private static final String ESTRATEGIA_ENCENDER = "Encender con movimiento";
    private static final String ESTRATEGIA_SEGUIR =
            "Encender y apagar según movimiento";

    @FXML private Label etiquetaEstado;
    @FXML private Label etiquetaMensaje;
    @FXML private ComboBox<String> selectorEstrategia;

    private boolean movimientoDetectado;
    private final Luz luzSala;
    private final SensorMovimientoAntiguo sensorAntiguo =
            () -> movimientoDetectado ? 1 : 0;
    private final AdaptadorSensorMovimiento sensorAdaptado =
            new AdaptadorSensorMovimiento(sensorAntiguo);

    public ControladorHogar(
            ContextoAplicacion contexto,
            NavegadorHogar navegador
    ) {
        super(contexto, navegador);
        luzSala = contexto.obtenerLuzSala();
    }

    @FXML
    private void initialize() {
        luzSala.agregarObservador(this);

        selectorEstrategia.getItems().setAll(
                ESTRATEGIA_ENCENDER,
                ESTRATEGIA_SEGUIR
        );
        selectorEstrategia.setValue(ESTRATEGIA_ENCENDER);
        selectorEstrategia.setOnAction(event -> cambiarEstrategia());

        actualizarEstado();
        etiquetaMensaje.setText(
                "Elige una estrategia y prueba los botones de movimiento."
        );
    }

    @FXML
    private void encender() {
        luzSala.encender();
    }

    @FXML
    private void apagar() {
        luzSala.apagar();
    }

    @FXML
    private void simularMovimiento() {
        movimientoDetectado = true;
        contexto.obtenerMediadorHogar()
                .procesarLectura("sala", sensorAdaptado);
        etiquetaMensaje.setText(
                "El mediador recibió movimiento en la sala y aplicó la estrategia elegida."
        );
    }

    @FXML
    private void simularAusencia() {
        movimientoDetectado = false;
        contexto.obtenerMediadorHogar()
                .procesarLectura("sala", sensorAdaptado);
        etiquetaMensaje.setText(
                "El mediador recibió que no hay movimiento y aplicó la estrategia elegida."
        );
    }

    private void cambiarEstrategia() {
        if (ESTRATEGIA_SEGUIR.equals(selectorEstrategia.getValue())) {
            contexto.obtenerReglaSala().cambiarEstrategia(
                    new EstrategiaSeguirMovimiento()
            );
        } else {
            contexto.obtenerReglaSala().cambiarEstrategia(
                    new EstrategiaEncenderLuzPorMovimiento()
            );
        }

        etiquetaMensaje.setText(
                "Estrategia activa: " + selectorEstrategia.getValue() + "."
        );
    }

    private void actualizarEstado() {
        etiquetaEstado.setText(
                luzSala.obtenerNombre()
                        + " · "
                        + luzSala.obtenerEstado().obtenerEtiqueta()
        );
    }

    @Override
    public void alCambiarEstadoDispositivo(
            String nombreDispositivo,
            String etiquetaActual
    ) {
        actualizarEstado();
        etiquetaMensaje.setText(
                nombreDispositivo + " cambió a "
                        + etiquetaActual.toLowerCase() + "."
        );
    }
}