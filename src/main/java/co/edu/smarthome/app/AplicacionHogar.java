package co.edu.smarthome.app;

import co.edu.smarthome.controller.NavegadorHogar;
import co.edu.smarthome.service.ContextoAplicacion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Punto de entrada JavaFX: crea el contexto compartido y muestra la ventana principal. */
public class AplicacionHogar extends Application {
    @Override
    public void start(Stage stage) {
        ContextoAplicacion contexto = new ContextoAplicacion();
        NavegadorHogar navegador = new NavegadorHogar(stage, contexto);

        stage.setTitle("Hogares Inteligentes");
        stage.setMinWidth(720);
        stage.setMinHeight(480);

        navegador.mostrarInicio();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
