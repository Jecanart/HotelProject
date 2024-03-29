/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import static edu.espol.models.Habitacion.habitaciones;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    /**Metodo que guarda las habitaciones al momento se aplastar la botonera*/
    @FXML
    public void guardarHabitacion(){  
    String numero=txtNumeroH.getText();  
    boolean exist=false;
    int counter=-1;          
    for(Habitacion h: habitaciones){
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
            habitaciones.add(ha);
            escrituraHabitaciones(ha);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("La habiatacion n° "+number+" ha sido guardada con exito");
            alert.showAndWait();
            }else{/**si quedan campos si llenar se infroma al usuario*/
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
                         habitaciones.add(ha);
                         habitaciones.remove(counter);
                         escrituraHabitaciones(habitaciones);
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
    /**Metodo que busca si una habitacion existe asignado al boton buscar */
    public void buscarHabitacion(){
        btEditar.setDisable(true);
        txtPrecioD.clear();
        tfServicio.clear();
        String numero=txtNumeroH.getText();
        boolean existe=false;
        for(Habitacion h: habitaciones){
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
    /**metodo que permite editar los campos de una habitacion, asignado al boton editar*/
    @FXML
    public void editarHabitacion(){
        txtPrecioD.setDisable(false);
        tfServicio.setDisable(false);
        cbCategoria.setDisable(false);
        btGuardar.setDisable(false);
    }
    /**metodo para permitir solo ingreso de numeros en un campo*/
    public static void teclasnum(TextField t){
        t.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            t.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    /**metodo para verificar si una cadena puede ser convertida a integer*/
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
     /**metodo que añade habitaciones al archivo  */
    public static void escrituraHabitaciones(Habitacion h){                                                 
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",true));){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+";"+h.getEstado()+"\n");
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
    }
    /**metodo que sobrescribe el archivo de habitaciones actualizandolas en el proceso*/
    public static void escrituraHabitaciones(ArrayList<Habitacion> list){ 
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",false));){
            escritor.write("Numero;Categoria;Precio;Servicios;Estado"+"\n");
            for(Habitacion h: habitaciones){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+";"+h.getEstado()+"\n");
             }
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
        
    }
    /**metodo asignado al boton volver para regresar al menu principal*/
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
