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
  
    @FXML
    public void guardarHabitacion(){  //Metodo que guarda las habitaciones al momento se aplastar la botonera
    String numero=txtNumeroH.getText();  //se obtiene el numero de habitacion ingresado
    boolean exist=false;//boolean para verificar la existencia de la habitacion
    int counter=-1;          
    for(Habitacion h: habitaciones){//se busca si la habitacion existe
     counter++;
     if(!numero.equals(h.getnHabitacion())){     // if que cambia el boolean exist si la habitacion existe
      }else{
        exist=true;
         break;}
         }
                    
          if(exist==false){     //if que comprueba si la habitacion existe
            String number=txtNumeroH.getText();  //se obtiene el numero de habitacion
            if(cbCategoria.getValue()!=null&&!txtPrecioD.getText().equals("")&&!tfServicio.getText().equals("")){//se verifica que no queden campos vacios
            int precio=Integer.parseInt(txtPrecioD.getText());
            Habitacion ha=new Habitacion(number,precio, tfServicio.getText(), cbCategoria.getValue()); //se crea un objeto habitcion con los datos ingresados
            habitaciones.add(ha);//se añade la habitacion y luego se actualiza el archivo
            escrituraHabitaciones(ha);
            Alert alert = new Alert(AlertType.INFORMATION);//se informa que se guardo la habitacion con un aviso
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("La habiatacion n° "+number+" ha sido guardada con exito");
            alert.showAndWait();
            }else{//si quedan campos si llenar se infroma al usuario
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de registro");
                alert.setHeaderText(null);
                alert.setContentText("Aun quedan campos sin llenar!");
                alert.showAndWait();
            }  
              }   
                if(exist==true){//if que se ejecuta si la habitacion existe
                Alert alert = new Alert(AlertType.CONFIRMATION);//se pregunta si se desera sobreescribir la habitacion
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Desea sobrescribir la habitacion n°?"+numero);
                ButtonType si = new ButtonType("Si");
                ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(si,no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == si){//si se responde si se procede a crear una habitacion con los datos nuevos y se soobrescribe la habitacion seleccionada
                    if(cbCategoria.getValue()!=null&&!txtPrecioD.getText().equals("")&&!tfServicio.getText().equals("")){
                         int precio=Integer.parseInt(txtPrecioD.getText());
                         Habitacion ha=new Habitacion(numero,precio, tfServicio.getText(), cbCategoria.getValue());
                         habitaciones.add(ha);//se añade la habitacion actualizada y se elimina la anterior
                         habitaciones.remove(counter);
                         escrituraHabitaciones(habitaciones);
                         Alert alerta = new Alert(AlertType.INFORMATION);//se informa que se guardo la habitacion
                         alerta.setTitle("Informacion");
                         alerta.setHeaderText(null);
                         alerta.setContentText("La habiatacion n° "+numero+" ha sido guardada con exito");
                         alerta.showAndWait();
            }else{//si quedan campos si llenar se infroma al usuario
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
    public void buscarHabitacion(){//metodo que se ejecuta al precionar buscar
        btEditar.setDisable(true);
        txtPrecioD.clear();
        tfServicio.clear();
        String numero=txtNumeroH.getText();//se obtiene el numero de habitacion
        boolean existe=false;
        for(Habitacion h: habitaciones){//for par abuscar el numero de habitacion
            if(!numero.equals(h.getnHabitacion())){
                
            }else{//si existe se llenan los campos con los datos
                existe=true;
                txtPrecioD.setText(String.valueOf(h.getPrecio()));
                cbCategoria.setValue(h.getCategoria());
                tfServicio.setText(h.getServicios());
                btEditar.setDisable(false);
                break;
            }
        }
        if(existe==false){//si no existe se informa y pregunta si se desea crear la habitacion
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
    public void editarHabitacion(){//metodo que permite editar los campos de una habitacion, asignado al boton editar
        txtPrecioD.setDisable(false);
        tfServicio.setDisable(false);
        cbCategoria.setDisable(false);
        btGuardar.setDisable(false);
    }
    
    public static void teclasnum(TextField t){//metodo para permitir solo ingreso de numeros en un campo
        t.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            t.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }
    
    public static boolean isNumeric(String cadena) {//metodo para verificar si una cadena puede ser convertida a integer
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
     
    public static void escrituraHabitaciones(Habitacion h){     //metodo que añade habitaciones al archivo                                              
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",true));){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+";"+h.getEstado()+"\n");
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
    }
    
    public static void escrituraHabitaciones(ArrayList<Habitacion> list){ //metodo que sobrescribe el archivo de habitaciones actualizandolas en el proceso
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Habitaciones.csv",false));){
            escritor.write("Numero;Categoria;Precio;Servicios;Estado"+"\n");
            for(Habitacion h: habitaciones){
             escritor.write(h.getnHabitacion()+";"+h.getCategoria()+";"+String.valueOf(h.getPrecio())+";"+h.getServicios()+";"+h.getEstado()+"\n");
             }
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
        
    }
    
    @FXML
    public void startMenu(ActionEvent event) throws IOException {//metodo asignado al boton volver para regresar al menu principal
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
