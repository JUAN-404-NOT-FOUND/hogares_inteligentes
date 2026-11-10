package co.edu.smarthome.controller;

import co.edu.smarthome.command.Command;
import co.edu.smarthome.command.ComandoAjustarTemperatura;
import co.edu.smarthome.command.ComandoCambiarCerradura;
import co.edu.smarthome.command.ComandoCambiarEstadoLuz;
import co.edu.smarthome.factory.CreadorCerradura;
import co.edu.smarthome.factory.CreadorDispositivo;
import co.edu.smarthome.factory.CreadorLuz;
import co.edu.smarthome.factory.CreadorTermostato;
import co.edu.smarthome.model.device.CerraduraInteligente;
import co.edu.smarthome.model.device.Dispositivo;
import co.edu.smarthome.model.device.Luz;
import co.edu.smarthome.model.device.Termostato;
import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public final class ControladorDispositivos extends ControladorNavegable {
    @FXML private ListView<Dispositivo> listaDispositivos;
    @FXML private Label etiquetaMensaje;

    public ControladorDispositivos(
            ContextoAplicacion contexto,
            NavegadorHogar navegador
    ) {
        super(contexto, navegador);
    }

    @FXML
    private void initialize() {
        listaDispositivos.setCellFactory(ignorado -> new ListCell<>() {
            @Override
            protected void updateItem(Dispositivo dispositivo, boolean vacio) {
                super.updateItem(dispositivo, vacio);

                if (vacio || dispositivo == null) {
                    setText(null);
                } else {
                    setText(dispositivo.obtenerNombre()
                            + " — " + dispositivo.obtenerHabitacion()
                            + " — " + dispositivo.obtenerEtiquetaEstado());
                }
            }
        });

        actualizarLista();
        etiquetaMensaje.setText(
                "Dispositivos registrados: "
                        + listaDispositivos.getItems().size()
        );
    }

    @FXML
    private void agregarDispositivo() {
        ChoiceDialog<String> selectorTipo = new ChoiceDialog<>(
                "Luz",
                "Luz",
                "Termostato",
                "Cerradura inteligente"
        );
        selectorTipo.setTitle("Agregar dispositivo");
        selectorTipo.setHeaderText("Elige el tipo de dispositivo");
        selectorTipo.setContentText("Tipo:");

        Optional<String> tipoElegido = selectorTipo.showAndWait();
        if (tipoElegido.isEmpty()) {
            return;
        }

        Optional<String> nombreIngresado = solicitarTexto(
                "Nombre del dispositivo",
                "Escribe un nombre para el dispositivo:"
        );
        if (nombreIngresado.isEmpty()) {
            return;
        }

        Optional<String> habitacionIngresada = solicitarTexto(
                "Habitación",
                "Escribe la habitación donde estará:"
        );
        if (habitacionIngresada.isEmpty()) {
            return;
        }

        String nombre = nombreIngresado.get().trim();
        String habitacion = habitacionIngresada.get().trim();

        if (nombre.isBlank() || habitacion.isBlank()) {
            etiquetaMensaje.setText(
                    "El nombre y la habitación son obligatorios."
            );
            return;
        }

        CreadorDispositivo creador = switch (tipoElegido.get()) {
            case "Luz" -> new CreadorLuz();
            case "Termostato" -> new CreadorTermostato();
            case "Cerradura inteligente" -> new CreadorCerradura();
            default -> throw new IllegalStateException(
                    "Tipo de dispositivo no reconocido."
            );
        };

        Dispositivo dispositivo = creador.crear(nombre, habitacion);
        contexto.obtenerServicioDispositivos().registrar(dispositivo);

        if (dispositivo instanceof Luz luz) {
            contexto.obtenerMediadorHogar().registrarLuz(luz);
        }

        actualizarLista();
        etiquetaMensaje.setText(
                "Se agregó " + dispositivo.obtenerNombre() + "."
        );
    }

    @FXML
    private void eliminarDispositivo() {
        Dispositivo seleccionado =
                listaDispositivos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            etiquetaMensaje.setText(
                    "Selecciona un dispositivo para eliminarlo."
            );
            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Quieres eliminar " + seleccionado.obtenerNombre() + "?",
                ButtonType.OK,
                ButtonType.CANCEL
        );
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("Eliminar dispositivo");

        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.isEmpty() || respuesta.get() != ButtonType.OK) {
            return;
        }

        if (seleccionado instanceof Luz luz) {
            contexto.obtenerMediadorHogar().quitarLuz(luz);
        }

        contexto.obtenerServicioDispositivos()
                .eliminar(seleccionado.obtenerId());

        actualizarLista();
        etiquetaMensaje.setText(
                "Se eliminó " + seleccionado.obtenerNombre() + "."
        );
    }

    @FXML
    private void ejecutarAccion() {
        Dispositivo seleccionado =
                listaDispositivos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            etiquetaMensaje.setText(
                    "Selecciona un dispositivo para ejecutar una acción."
            );
            return;
        }

        Command comando;
        String descripcion;

        if (seleccionado instanceof Luz luz) {
            boolean encender =
                    !"Encendida".equals(luz.obtenerEtiquetaEstado());

            comando = new ComandoCambiarEstadoLuz(luz, encender);
            descripcion = encender ? "Luz encendida." : "Luz apagada.";

        } else if (seleccionado instanceof CerraduraInteligente cerradura) {
            boolean bloquear = !cerradura.estaBloqueada();

            comando = new ComandoCambiarCerradura(cerradura, bloquear);
            descripcion = bloquear
                    ? "Cerradura bloqueada."
                    : "Cerradura desbloqueada.";

        } else if (seleccionado instanceof Termostato termostato) {
            TextInputDialog dialogo = new TextInputDialog(
                    String.valueOf(termostato.obtenerTemperaturaObjetivo())
            );
            dialogo.setTitle("Ajustar termostato");
            dialogo.setHeaderText(
                    "Ingresa una temperatura entre 5 y 35 °C"
            );
            dialogo.setContentText("Temperatura:");

            Optional<String> valor = dialogo.showAndWait();
            if (valor.isEmpty()) {
                return;
            }

            double temperatura;
            try {
                temperatura = Double.parseDouble(valor.get().trim());
            } catch (NumberFormatException error) {
                etiquetaMensaje.setText(
                        "Escribe una temperatura numérica válida."
                );
                return;
            }

            if (!Double.isFinite(temperatura)
                    || temperatura < 5
                    || temperatura > 35) {
                etiquetaMensaje.setText(
                        "La temperatura debe estar entre 5 y 35 °C."
                );
                return;
            }

            comando = new ComandoAjustarTemperatura(
                    termostato,
                    temperatura
            );
            descripcion = "Temperatura ajustada a "
                    + temperatura + " °C.";

        } else {
            etiquetaMensaje.setText(
                    "Este tipo de dispositivo no tiene acciones disponibles."
            );
            return;
        }

        contexto.obtenerInvocadorComandos().ejecutar(comando);
        actualizarLista();
        etiquetaMensaje.setText(descripcion);
    }

    @FXML
    private void deshacerUltimaAccion() {
        if (!contexto.obtenerInvocadorComandos().puedeDeshacer()) {
            etiquetaMensaje.setText(
                    "Todavía no hay acciones para deshacer."
            );
            return;
        }

        contexto.obtenerInvocadorComandos().deshacerUltimo();
        actualizarLista();
        etiquetaMensaje.setText("Se deshizo la última acción.");
    }

    private Optional<String> solicitarTexto(
            String titulo,
            String indicacion
    ) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(indicacion);
        return dialogo.showAndWait();
    }

    private void actualizarLista() {
        listaDispositivos.getItems().setAll(
                contexto.obtenerServicioDispositivos().listar()
        );
    }
}