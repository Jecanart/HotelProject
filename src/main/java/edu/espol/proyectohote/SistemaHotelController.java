/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    /**metodo que abre el panel de registros con el boton registrar habitacion*/
    @FXML
    public void startRegistro(ActionEvent event) throws IOException{
        Scene secondScene = new Scene(loadFXML("RegistroHabitacion"), 677, 430);
        //Stage newWindow = new Stage();
        Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Registro de habitaciones");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    /**metodo que abre el panel de recepcion con el boton recepcion*/
    @FXML
    public void startRecepcion(ActionEvent event) throws IOException{
        Scene secondScene = new Scene(loadFXML("SistemaRecepcion"), 677, 430);
        //Stage newWindow = new Stage();
        Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Recepcion");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    /**metodo que abre el panel de hoteles con el boton registro hotel*/
    @FXML
    public void startHotel(ActionEvent event) throws IOException{
        Scene secondScene = new Scene(loadFXML("RegistroHotel"), 670, 430);
        //Stage newWindow = new Stage();
        Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Registro de Hoteles");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    /**metodo que abre el panel de reservas con el boton reservas*/
    @FXML
    public void startReservacion(ActionEvent event) throws IOException{
        Scene secondScene = new Scene(loadFXML("SistemaReservacion"), 820, 600);
        Stage newWindow = new Stage();
        newWindow.setTitle("Reservación de Habitación");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
//Stage newWindow3 = (Stage)((Node)e3.getSource()).getScene().getWindow();