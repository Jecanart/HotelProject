/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import static edu.espol.models.Habitacion.habitaciones;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SistemaRecepcionController implements Initializable {

    @FXML
    private FlowPane pHabitaciones;
    @FXML
    private Button btVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarHabitaciones();
    }    
    
    
    public void llenarHabitaciones(){    
        pHabitaciones.getChildren().clear();
        Collections.sort(habitaciones, (Habitacion h1, Habitacion h2) -> new Integer(h2.getintnHabitacion()).compareTo(new Integer(h1.getintnHabitacion())));
        for(Habitacion h: habitaciones){
            VBox room=new VBox();
            Label lblNumero= new Label(h.getnHabitacion());
            Label lblCategoria= new Label(h.getCategoria());
            Label lblEstado= new Label(h.getEstado());
            room.getChildren().addAll(lblNumero,lblCategoria,lblEstado);
            room.setStyle("-fx-border-color: black");
            pHabitaciones.getChildren().addAll(room);
            room.setOnMouseClicked((MouseEvent mouseEvent) -> {
                System.out.println("funciona");
            });
        }  
    
    }
    @FXML
        public void startMenu(ActionEvent event) throws IOException {
        Scene secondScene = new Scene(loadFXML("SistemaHotel"), 670, 430);
        //Stage newWindow = new Stage();
        Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        newWindow.setTitle("Sistema Hotel");
        newWindow.setScene(secondScene);
        newWindow.show();
    }
        private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
      
    }
    

