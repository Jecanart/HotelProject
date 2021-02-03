/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectohote;

import edu.espol.models.Habitacion;
import static edu.espol.models.Habitacion.habitaciones;
import edu.espol.models.Reservas;
import static edu.espol.models.Reservas.reservas;
import static edu.espol.proyectohote.App.categorias;
import static edu.espol.proyectohote.RegistroHabitacionController.escrituraHabitaciones;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private Label lblNhab;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String>listaCategorias = new ArrayList<>();
        listaCategorias.add("Matrimonial");listaCategorias.add("Suite");listaCategorias.add("Doble");listaCategorias.add("Triple");listaCategorias.add("Todas");
        lista.add("Efectivo");lista.add("Credito/Debito");
        cboxCategoria.setItems(FXCollections.observableArrayList(listaCategorias));
        cbFormaPago.setItems(FXCollections.observableArrayList(lista));
        hpane1.setSpacing(20);
        hpane2.setSpacing(20);
        RegistroHabitacionController.teclasnum(identificacion);
    }
    
    @FXML
    private void cambiar(ActionEvent event){    //Metodo que cambia las habitaciones de acuerdo a su categoria seleccionada en el comboBox
        ComboBox cb=(ComboBox)event.getSource();
        String categoria = (String) cb.getValue();
        ArrayList<Habitacion> filtrarHabitaciones = Habitacion.filtrarHabitacion(habitaciones, categoria);
        hpane1.getChildren().clear();
        hpane2.getChildren().clear();
        mostrarHabitacion(filtrarHabitaciones);
    }
    
    @FXML
    public void verificarFechaEntrada(ActionEvent event){  //Metodo que llegar a verifciar que la fecha de Ingreso no sea días antes a la fecha actual del computador
        LocalDate fechaActual = LocalDate.now();
        LocalDate inicio = fechaEntrada.getValue();
        if(inicio.isAfter(fechaActual) || inicio.isEqual(fechaActual)){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("¡La fecha ingresada correctamente!");
            alerta.showAndWait();  
        }
        else{
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error de registro de Fecha");
            alerta1.setHeaderText(null);
            alerta1.setContentText("La fecha seleccionada debe ser apartir del: "+fechaActual);
            alerta1.showAndWait();
        }
    }
    
    @FXML
    public void verificarFechaSalida(ActionEvent event){
        LocalDate inicio = fechaEntrada.getValue();
        LocalDate salida = fechaSalida.getValue();
        if(salida.isAfter(inicio)){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("¡La fecha ingresada correctamente!");
            alerta.showAndWait();  
        }
        else{
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error de registro de Fecha");
            alerta1.setHeaderText(null);
            alerta1.setContentText("La fecha seleccionada debe ser después del: "+inicio);
            alerta1.showAndWait();
        }
    }
    
    @FXML
    public void verificar(ActionEvent event){
        System.out.println(lblNhab.getText());
        LocalDate inicio = fechaEntrada.getValue();
        LocalDate salida = fechaSalida.getValue();
        for(Reservas r : reservas){
            r.getNhabitacion();
            if(lblNhab.getText().equals(r.getNhabitacion())){
                System.out.println("Entre al IF");
                LocalDate inicioReserva = r.getIngreso();
                LocalDate salidaReserva = r.getSalida();
                if((inicio.isBefore(inicioReserva) && salida.isBefore(inicioReserva))|| (inicio.isAfter(salidaReserva) && salida.isAfter(salidaReserva))){ //Fecha No Incluida
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Información");
                    alerta.setHeaderText(null);
                    alerta.setContentText("No hay reservaciones durante las fechas seleccionadas!");
                    alerta.showAndWait();  
                }
                else{
                    Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                    alerta1.setTitle("Error de registro de Fecha");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("La habitación estará ocupada desde "+inicioReserva+" hasta el "+salidaReserva);
                    alerta1.showAndWait();
                }
            }
            
            
        }
        System.out.println("Salir del For");
    }
    

    
    public void mostrarHabitacion(ArrayList<Habitacion> listaHabitaciones){
        for(Habitacion h : listaHabitaciones){
            System.out.println(h.getCategoria());
           Image img = new Image("imagenes/"+h.getCategoria()+".JPG");
           ImageView imgView = new ImageView(img);
           imgView.setFitHeight(210);
           imgView.setFitWidth(350);
           hpane1.getChildren().add(imgView);
           imgView.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        //String p = (String) h.getPrecio();
                        Text precio = new Text("$"+Integer.toString(h.getPrecio()));
                        Text titulo = new Text("Habitación tipo:"+h.getCategoria()+" N°"+h.getnHabitacion());
                        Text estadoHabitacion = new Text(h.getEstado());
                        //Text numHabitacion = new Text(h.getnHabitacion());
                        Text serviciosHabitacion = new Text(h.getServicios());
                        lblNhab.setText(h.getnHabitacion());
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
    @FXML
 public void guardarReserva(){
     LocalDate hoy=LocalDate.now();
     if(!nombre.getText().equals("")&&!identificacion.getText().equals("")&&!paisOrigen.getText().equals("")&&cbFormaPago.getValue()!=null&&fechaEntrada.getValue()!=null&&fechaSalida.getValue()!=null&&!lblNhab.getText().equals("")){
        Reservas r= new Reservas(nombre.getText(),identificacion.getText(), paisOrigen.getText(), cbFormaPago.getValue(), fechaEntrada.getValue(),fechaSalida.getValue(), lblNhab.getText());
        reservas.add(r);
        escrituraReservas(r);
        for(Habitacion h: habitaciones){
            if(h.getnHabitacion().equals(lblNhab.getText())){
                if(h.getEstado().equals("Ocupada")||h.getEstado().equals("Reservada")){
                   Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                    alerta1.setTitle("Error de registro");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("La habitacion no se encuentra disponible");
                    alerta1.showAndWait(); 
                    break;
                }
                
                if(r.getIngreso().compareTo(hoy)==0){
                   h.setEstado("Reservada");
                   escrituraHabitaciones(habitaciones); 
                }
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion");
                alerta.setHeaderText(null);
                alerta.setContentText("La habiatacion n° "+h.getnHabitacion()+" ha sido reservada");
                alerta.showAndWait();
            }
        }
     }else{
         Alert alerta1 = new Alert(Alert.AlertType.ERROR);
         alerta1.setTitle("Error de registro");
         alerta1.setHeaderText(null);
         alerta1.setContentText("Aun quedan campos sin llenar!");
         alerta1.showAndWait();
     }
        
 }
 

 
 
 public static void escrituraReservas(Reservas r){                                                   
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Reservas.csv",true));){
             escritor.write(r.getNombre()+";"+r.getIdentificacion()+";"+r.getOrigen()+";"+r.getPago()+";"+String.valueOf(r.getIngreso())+";"+String.valueOf(r.getSalida())+";"+r.getNhabitacion()+"\n");
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
    }
    public static void escrituraReservas(ArrayList<Reservas> list){
        try(BufferedWriter escritor=new BufferedWriter(new FileWriter("archivos/Reservas.csv",false));){
            escritor.write("Nombre;Identificacion;Pais de Origen;Fecha de entrada;Fecha de Salida;Numero de habitacion"+"\n");
            for(Reservas r: reservas){
             escritor.write(r.getNombre()+";"+r.getIdentificacion()+";"+r.getOrigen()+";"+r.getPago()+";"+String.valueOf(r.getIngreso())+";"+String.valueOf(r.getSalida())+";"+r.getNhabitacion()+"\n");
             }
        }catch(IOException e){
            System.err.println("Error de escritura: "+e);  
        }
        
    }
}
