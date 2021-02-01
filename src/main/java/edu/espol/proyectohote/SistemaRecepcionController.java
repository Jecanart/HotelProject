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
public class SistemaRecepcionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
