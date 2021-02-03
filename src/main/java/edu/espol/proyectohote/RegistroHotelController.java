/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;


import edu.espol.models.Hotel;
import static edu.espol.models.Hotel.listaHotel;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegistroHotelController implements Initializable {

    @FXML
    private TextField txthNombre;
    @FXML
    private TextField txthCiudad;
    @FXML
    private TextField txthDireccion;
    @FXML
    private TextField txthTelefono;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuardar;
    @FXML
    private Button btConsultaC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegistroHabitacionController.teclasnum(txthTelefono);
        txthCiudad.setDisable(true);
        txthTelefono.setDisable(true);
        txthDireccion.setDisable(true);
        btGuardar.setDisable(true);
    }    
    /**metodo que verifica si el hotel existe buscandolo en la lista, se usa con el boton crear/consultar*/
    @FXML
    public void crearConsultar(){
        String nombre=txthNombre.getText();     
        boolean existe= false;
        for(Hotel hh:listaHotel){
            if(!nombre.equals(hh.getNombre())){
                
            }else{//if que se ejecuta si el hotel existe
                txthCiudad.setText(hh.getCiudad());
                txthTelefono.setText(hh.getTelefono());
                txthDireccion.setText(hh.getDireccion());         
                existe=true;
                break;
            }
        }
        if(existe==false){
            if(listaHotel.size()>0){/**if que permite poner un limite maximo a los hoteles creados, si el limite es alcanzado no se permitira crear mas hoteles*/
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El limite de hoteles ha sido alcanzado!");
                    alert.showAndWait();    
                }else{
                    txthCiudad.setDisable(false);
                    txthTelefono.setDisable(false);
                    txthDireccion.setDisable(false);
                    btGuardar.setDisable(false);     
                }
        }
        
    }
    /**metodo que guarda el hotel al archivo usado con el boton guardar hotel*/
    @FXML
    public void guardarHotel(){
        if(!txthNombre.getText().equals("")&&!txthCiudad.getText().equals("")&&!txthDireccion.getText().equals("")&&!txthTelefono.getText().equals("")){//if que verifica que no queden campos vacios
            Hotel ho=new Hotel(txthNombre.getText(),txthCiudad.getText(),txthDireccion.getText(),txthTelefono.getText());
            listaHotel.add(ho);
            escrituraHotel(ho);
        }else{
            Alert alerta1 = new Alert(AlertType.ERROR);
            alerta1.setTitle("Error de registro");
            alerta1.setHeaderText(null);
            alerta1.setContentText("Aun quedan campos sin llenar!");
            alerta1.showAndWait();
        }
        txthCiudad.setDisable(true);
        txthTelefono.setDisable(true);
        txthDireccion.setDisable(true);
        btGuardar.setDisable(true);  
        
    }
     /**meotod que escribe hoteles en el archivo */
    public static void escrituraHotel(Hotel h){                                                 
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Hoteles.csv",true));){
             escritor.write(h.getNombre()+";"+h.getCiudad()+";"+h.getDireccion()+";"+h.getTelefono()+"\n");
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
    }
    /**metodo que permite volver al menu principal */
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
