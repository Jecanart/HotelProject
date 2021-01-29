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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
    @FXML
    private Button btEditar;
    @FXML
    private Button btBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbCategoria.getItems().addAll(App.categorias);
        teclasnum(txtNumeroH);
        teclasnum(txtPrecioD);
        txtPrecioD.setDisable(true);
        tfServicio.setDisable(true);
        cbCategoria.setDisable(true);
        btEditar.setDisable(true);
        btGuardar.setDisable(true);
        
        
    }
  
    @FXML
    public void guardarHabitacion(){
    String numero=txtNumeroH.getText();  
    boolean exist=false;
    int counter=-1;          
    for(Habitacion h: Habitaciones){
     counter++;
     if(!numero.equals(h.getnHabitacion())){     
      }else{
        exist=true;
         break;}
         }
                    
          if(exist==false){     
            String number=txtNumeroH.getText();  
            if(cbCategoria.getValue()!=null&&!txtPrecioD.getText().equals("")&&!tfServicio.getText().equals("")){
            int precio=Integer.parseInt(txtPrecioD.getText());
            Habitacion ha=new Habitacion(number,precio, tfServicio.getText(), cbCategoria.getValue());
            Habitaciones.add(ha);
            escrituraHabitaciones(ha);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("La habiatacion n° "+number+" ha sido guardada con exito");
            alert.showAndWait();
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de registro");
                alert.setHeaderText(null);
                alert.setContentText("Aun quedan campos sin llenar!");
                alert.showAndWait();
            }  
              }   
                if(exist==true){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Desea sobrescribir la habitacion n°?"+numero);
                ButtonType si = new ButtonType("Si");
                ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(si,no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == si){
                    if(cbCategoria.getValue()!=null&&!txtPrecioD.getText().equals("")&&!tfServicio.getText().equals("")){
                         int precio=Integer.parseInt(txtPrecioD.getText());
                         Habitacion ha=new Habitacion(numero,precio, tfServicio.getText(), cbCategoria.getValue());
                         Habitaciones.add(ha);
                         Habitaciones.remove(counter);
                         escrituraHabitaciones(Habitaciones);
                         Alert alerta = new Alert(AlertType.INFORMATION);
                         alerta.setTitle("Informacion");
                         alerta.setHeaderText(null);
                         alerta.setContentText("La habiatacion n° "+numero+" ha sido guardada con exito");
                         alerta.showAndWait();
            }else{
                Alert alerta1 = new Alert(AlertType.ERROR);
                alerta1.setTitle("Error de registro");
                alerta1.setHeaderText(null);
                alerta1.setContentText("Aun quedan campos sin llenar!");
                alerta1.showAndWait();
            }
                } 
                }
        txtPrecioD.setDisable(true);
        tfServicio.setDisable(true);
        cbCategoria.setDisable(true);
        btEditar.setDisable(false);
        btGuardar.setDisable(true);
    }
    
    @FXML
    public void buscarHabitacion(){
        btEditar.setDisable(true);
        txtPrecioD.clear();
        tfServicio.clear();
        String numero=txtNumeroH.getText();
        boolean existe=false;
        for(Habitacion h: Habitaciones){
            if(!numero.equals(h.getnHabitacion())){
                
            }else{
                existe=true;
                txtPrecioD.setText(String.valueOf(h.getPrecio()));
                cbCategoria.setValue(h.getCategoria());
                tfServicio.setText(h.getServicios());
                btEditar.setDisable(false);
                break;
            }
        }
        if(existe==false){
             Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("La habitacion solicitada no existe, desea registrarla?");
                ButtonType si = new ButtonType("Si");
                ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(si,no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == si){
                    txtPrecioD.setDisable(false);
                    tfServicio.setDisable(false);
                    cbCategoria.setDisable(false);
                    btGuardar.setDisable(false);
                } else{
                    
                }
            
        }
}
    
    @FXML
    public void editarHabitacion(){
        txtPrecioD.setDisable(false);
        tfServicio.setDisable(false);
        cbCategoria.setDisable(false);
        btGuardar.setDisable(false);
    }
    
    public static void teclasnum(TextField t){
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
    
    public static void escrituraHabitaciones(ArrayList<Habitacion> list){
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",false));){
            escritor.write("Numero;Categoria;Precio;Servicios"+"\n");
            for(Habitacion h: Habitaciones){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+"\n");
             }
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
        
    }

    

}
