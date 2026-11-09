package co.edu.smarthome.controller;

import co.edu.smarthome.service.ContextoAplicacion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** Carga las vistas y crea sus controladores con el contexto compartido. */
public final class NavegadorHogar {
    private static final String VISTA_INICIO = "/fxml/vista-hogar.fxml";
    private static final String VISTA_DISPOSITIVOS = "/fxml/vista-dispositivos.fxml";
    private static final String VISTA_AUTOMATIZACIONES ="/fxml/vista-automatizaciones.fxml";
    private static final String VISTA_HISTORIAL="/fxml/vista-historial.fxml";

    private final Stage stage;
    private final ContextoAplicacion contexto;

    public NavegadorHogar(Stage stage, ContextoAplicacion contexto) {
        this.stage = Objects.requireNonNull(stage);
        this.contexto = Objects.requireNonNull(contexto);
    }
    public void mostrarInicio() {
        mostrar(VISTA_INICIO);
    }

    public void mostrarDispositivos() {
        mostrar(VISTA_DISPOSITIVOS);
    }

    public void mostrarAutomatizaciones() {
        mostrar(VISTA_AUTOMATIZACIONES);
    }

    public void mostrarHistorial() {
        mostrar(VISTA_HISTORIAL);
    }

    private void mostrar(String recurso){
        var url = NavegadorHogar.class.getResource(recurso);
        if (url == null) {
            throw new IllegalArgumentException("No se encontro la vista" + recurso);
        }

        FXMLLoader cargador = new FXMLLoader(url);
        cargador.setControllerFactory(this::crearControlador);

        try{
            Parent raiz = cargador.load();
            if(stage.getScene() == null) {
                stage.setScene(new Scene(raiz, 900, 600));
            }else {
                stage.getScene().setRoot(raiz);
            }
        }catch (IOException error) {
            throw new IllegalArgumentException("No se pudo cargar la vista:" + recurso, error);
        }
    }

    private Object crearControlador(Class<?> tipo) {
        if (tipo == ControladorHogar.class) {
            return new ControladorHogar(contexto, this);
        }
        if (tipo == ControladorDispositivos.class){
            return new ControladorDispositivos(contexto, this);
        }
        if (tipo == ControladorAutomatizaciones.class){
            return new ControladorAutomatizaciones(contexto, this);
        }
        if (tipo == ControladorHistorial.class) {
            return new ControladorHistorial(contexto, this);
        }
        throw new IllegalArgumentException("No hay un controlador registrado para" + tipo.getName());
    }
}
