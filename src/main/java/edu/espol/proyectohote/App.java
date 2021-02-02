package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import edu.espol.models.Hotel;
import edu.espol.models.Reservas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String[] categorias ={"Matrimonial","Suite", "Doble", "Triple"};

    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("SistemaHotel"), 670, 430);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Hotel.cargarHoteles();
        Habitacion.cargarHabitaciones();
        Reservas.cargarReservas();
        launch();
    }

}