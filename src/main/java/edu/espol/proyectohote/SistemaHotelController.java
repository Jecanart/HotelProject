/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SistemaHotelController implements Initializable {

    @FXML
    private Button btRegistroH;
   
    @FXML
    private Button btRegistroHo;
    @FXML
    private Button btRecepcion;
    @FXML
    private Button btReserva;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
   
    @FXML
    public void startRegistro() throws IOException{
        Scene secondScene = new Scene(loadFXML("RegistroHabitacion"), 640, 480);
        Stage newWindow = new Stage();
        newWindow.setTitle("Registro de habitaciones");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    @FXML
    public void startHotel() throws IOException{
        Scene secondScene = new Scene(loadFXML("RegistroHotel"), 640, 480);
        Stage newWindow = new Stage();
        newWindow.setTitle("Registro de Hoteles");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    @FXML
    public void startRecepcion() throws IOException{
        Scene secondScene = new Scene(loadFXML("SistemaRecepcion"), 640, 480);
        Stage newWindow = new Stage();
        newWindow.setTitle("Recepcion");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
}
