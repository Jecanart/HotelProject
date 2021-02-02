/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import static edu.espol.models.Habitacion.habitaciones;
import static edu.espol.proyectohote.App.categorias;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SistemaReservacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btMenu;
    @FXML
    private HBox hpane1;
    @FXML
    private HBox hpane2;
    @FXML
    private DatePicker fechaEntrada;
    @FXML
    private DatePicker fechaSalida;
    @FXML
    private Button verificar;
    @FXML
    private ComboBox cboxCategoria;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<String> cbFormaPago;
    @FXML
    private TextField identificacion;
    @FXML
    private TextField paisOrigen;
    @FXML
    private Button btGuardarReserva;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String>listaCategorias = new ArrayList<>();
        listaCategorias.add("Matrimonial");listaCategorias.add("Suite");listaCategorias.add("Doble");listaCategorias.add("Triple");listaCategorias.add("Todas");
        lista.add("Efectivo");lista.add("Tarje Crédito/Débito");
        cboxCategoria.setItems(FXCollections.observableArrayList(listaCategorias));
        cbFormaPago.setItems(FXCollections.observableArrayList(lista));
        hpane1.setSpacing(20);
        hpane2.setSpacing(20);

    }

    
    @FXML
    private void cambiar(ActionEvent event){
        ComboBox cb=(ComboBox)event.getSource();
        String categoria = (String) cb.getValue();
        System.out.println("Llame a cambiar: "+categoria);
        System.out.println(cboxCategoria.getValue());
        
        Habitacion h1 = new Habitacion("17",75,"Desayuno","Doble");
        Habitacion h3 = new Habitacion("18",85,"Desayuno","Triple");
        Habitacion h4 = new Habitacion("19",95,"Comida incluida","Suite");
        Habitacion h5 = new Habitacion("20",150,"Todo esta incluido","Matrimonial");
        ArrayList<Habitacion> listaH = new ArrayList<>();
        listaH.add(h1);
        listaH.add(h3);
        listaH.add(h4);
        listaH.add(h5);
        //ArrayList<Habitacion> filtrarHabitaciones = Habitacion.filtrarHabitacion(habitaciones, categoria);
                //Pelicula.filtrarPeliculas(peliculas, categoria);
        ArrayList<Habitacion> filtrarHabitaciones = Habitacion.filtrarHabitacion(listaH, categoria);
        hpane1.getChildren().clear();
        hpane2.getChildren().clear();
        mostrarHabitacion(filtrarHabitaciones);
        
        
    }
    
    public void mostrarHabitacion(ArrayList<Habitacion> listaHabitaciones){
        for(Habitacion h : listaHabitaciones){
            System.out.println(h.getCategoria());
           Image img = new Image("imagenes/"+h.getCategoria()+".JPG");
           ImageView imgView = new ImageView(img);
           imgView.setFitHeight(180);
           imgView.setFitWidth(270);
           hpane1.getChildren().add(imgView);
           imgView.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        //String p = (String) h.getPrecio();
                        Text precio = new Text("$"+Integer.toString(h.getPrecio()));
                        Text titulo = new Text("Habitación tipo:"+h.getCategoria()+" N°"+h.getnHabitacion());
                        Text estadoHabitacion = new Text("Disponible");
                        //Text numHabitacion = new Text(h.getnHabitacion());
                        Text serviciosHabitacion = new Text(h.getServicios());
                        
                        titulo.setWrappingWidth(70);
                        titulo.setTextAlignment(TextAlignment.JUSTIFY);
                        serviciosHabitacion.setWrappingWidth(70);
                        serviciosHabitacion.setTextAlignment(TextAlignment.JUSTIFY);
                        estadoHabitacion.setWrappingWidth(70);
                        estadoHabitacion.setTextAlignment(TextAlignment.CENTER);
                        precio.setWrappingWidth(70);
                        precio.setTextAlignment(TextAlignment.CENTER);
                        
                        hpane2.getChildren().clear();
                        hpane2.getChildren().add(titulo);
                        hpane2.getChildren().add(serviciosHabitacion);
                        hpane2.getChildren().add(precio);
                        hpane2.getChildren().add(estadoHabitacion);

                        }
                    }
                );
        }  
    }
    @FXML
    public void cerrarVentana(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
 
    
}
