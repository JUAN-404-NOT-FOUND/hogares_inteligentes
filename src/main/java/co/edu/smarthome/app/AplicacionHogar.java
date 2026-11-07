package co.edu.smarthome.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionHogar extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                AplicacionHogar.class.getResource("/fxml/vista-hogar.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        stage.setTitle("Hogares Inteligentes");
        stage.setMinWidth(720);
        stage.setMinHeight(480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
