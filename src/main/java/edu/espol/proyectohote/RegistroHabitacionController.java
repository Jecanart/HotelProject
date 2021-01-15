/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import static edu.espol.models.Habitacion.Habitaciones;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegistroHabitacionController implements Initializable {


    @FXML
    private TextField txtNumeroH;
    @FXML
    private TextField txtPrecioD;
    @FXML
    private ComboBox<String> cbCategoria;
    @FXML
    private TextArea tfServicio;
    @FXML
    private Button btGuardar;
    @FXML
    private Button btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbCategoria.getItems().addAll(App.categorias);
        teclasnum(txtNumeroH);
        teclasnum(txtPrecioD);
    }
  
    @FXML
    public void guardarHabitacion(){
        boolean repetido=false;
        String numero=txtNumeroH.getText();
        for(Habitacion h: Habitaciones){
            if(!numero.equals(h.getnHabitacion())){
                repetido=false; 
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de registro");
                alert.setHeaderText(null);
                alert.setContentText("El numero de Habitacion ya se encuentra registrado!");
                alert.showAndWait();
                repetido=true;
                break;
            }
        }
        if(repetido==false){
            int precio=Integer.parseInt(txtPrecioD.getText());
            Habitacion ha=new Habitacion(numero,precio, tfServicio.getText(), cbCategoria.getValue());
            Habitaciones.add(ha);
            escrituraHabitaciones(ha);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("La habiatacion n° "+numero+" ha sido guardada con exito");
            alert.showAndWait();
            
        }
    }
    
    public void teclasnum(TextField t){
        t.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            t.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    
     public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
     public static void escrituraHabitaciones(Habitacion h){                                                           
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",true));){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+"\n");
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
    }

}
