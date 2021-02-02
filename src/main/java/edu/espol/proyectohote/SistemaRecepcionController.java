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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
            lblNumero.setStyle("-fx-text-fill: white");
            Label lblCategoria= new Label(h.getCategoria());
            lblCategoria.setStyle("-fx-text-fill: white");
            Label lblEstado= new Label(h.getEstado());
            lblEstado.setStyle("-fx-text-fill: white");
            room.getChildren().addAll(lblNumero,lblCategoria,lblEstado);
            pHabitaciones.getChildren().addAll(room);
            switch(h.getEstado()){
                case "Disponible":
                    Image img = new Image("imagenes/"+h.getCategoria()+".JPG");
                    ImageView imgView = new ImageView(img);
                    imgView.setFitHeight(105);
                    imgView.setFitWidth(135);
                    room.getChildren().add(imgView);
                    room.setStyle("-fx-background-color: green;"+"-fx-border-color: black");
                    room.setOnMouseClicked((MouseEvent mouseEvent) -> {
                try {
                    Scene secondScene = new Scene(loadFXML("SistemaReservacion"), 820, 600);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Reservación de Habitación");
                    newWindow.setScene(secondScene);
                    newWindow.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
                    break;
                case "Reservada":
                    Image img2 = new Image("imagenes/"+h.getCategoria()+".JPG");
                    ImageView imgView2 = new ImageView(img2);
                    imgView2.setFitHeight(105);
                    imgView2.setFitWidth(135);
                    room.getChildren().add(imgView2);
                    room.setStyle("-fx-background-color: orange;"+"-fx-border-color: black");
                    break;
                case "Ocupada":
                    Image img3 = new Image("imagenes/"+h.getCategoria()+".JPG");
                    ImageView imgView3 = new ImageView(img3);
                    imgView3.setFitHeight(105);
                    imgView3.setFitWidth(135);
                    room.getChildren().add(imgView3);
                    room.setStyle("-fx-background-color: darkred;"+"-fx-border-color: black");
                    break;
                default:
                    System.out.println("Error de lectura");
            }
        
            
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
    

