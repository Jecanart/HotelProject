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
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);  //Enviar mensaje de aletar cuando la fecha es menor a la actual
            alerta1.setTitle("Error de registro de Fecha");
            alerta1.setHeaderText(null);
            alerta1.setContentText("La fecha seleccionada debe ser apartir del: "+fechaActual);
            alerta1.showAndWait();
        }
    }
    
    @FXML
    public void verificarFechaSalida(ActionEvent event){ //Metodo que llegar a verifciar que la fecha de Salida no sea igual a la fecha de entrada
        LocalDate inicio = fechaEntrada.getValue();
        LocalDate salida = fechaSalida.getValue();
        if(salida.isAfter(inicio)){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION); //Fecha ingresada de forma correcta
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("¡La fecha ingresada correctamente!");
            alerta.showAndWait();  
        }
        else{
            Alert alerta1 = new Alert(Alert.AlertType.ERROR); //Me
            alerta1.setTitle("Error de registro de Fecha");
            alerta1.setHeaderText(null);
            alerta1.setContentText("La fecha seleccionada debe ser después del: "+inicio);
            alerta1.showAndWait();
        }
    }
    
    @FXML
    public void verificar(ActionEvent event){ //Metodo que se encarga de verificar el rango de fechas seleccionado por el usuario no este reservada con anticipacion la habitacion
        LocalDate inicio = fechaEntrada.getValue();  //Fecha de entrada por el usuario
        LocalDate salida = fechaSalida.getValue();   //Fecha de salida por el usuario
        for(Reservas r : reservas){                 //Recorre lista estatica de las reservas que estan guardados en un archivo
            r.getNhabitacion();
            if(lblNhab.getText().equals(r.getNhabitacion())){ //Se vefica la habitacion sean la misma
                LocalDate inicioReserva = r.getIngreso();     //Fecha de entrada de la habitacion reservada
                LocalDate salidaReserva = r.getSalida();      //Fecha de salida de la habitacion reservada
                if((inicio.isBefore(inicioReserva) && salida.isBefore(inicioReserva))|| (inicio.isAfter(salidaReserva) && salida.isAfter(salidaReserva))){ //Se realiza la conficones para que las fechas ingresadas no se intefieran con las fechas reservadas
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Información");
                    alerta.setHeaderText(null);
                    alerta.setContentText("No hay reservaciones durante las fechas seleccionadas!");                        //El rango de fecha no se encuentra reservada la habitación
                    alerta.showAndWait();  
                }
                else{
                    Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                    alerta1.setTitle("Error de registro de Fecha");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("La habitación estará ocupada desde "+inicioReserva+" hasta el "+salidaReserva);  //El rango de fecha ingresado interfiere con el reservado
                    alerta1.showAndWait();
                }
            } 
        }
    }
    

    
    public void mostrarHabitacion(ArrayList<Habitacion> listaHabitaciones){ //Se muestran las habitaciones de acuerdo al ArrayList de Habitaciones recibidas
        for(Habitacion h : listaHabitaciones){                              //Recorre las habitaciones
           Image img = new Image("imagenes/"+h.getCategoria()+".JPG");      //Se consigue la imagen de la habitacion
           ImageView imgView = new ImageView(img);
           imgView.setFitHeight(210);                                       //Dimension vertical de la imagen 
           imgView.setFitWidth(350);                                        //Dimension horizontal de la imag
           hpane1.getChildren().add(imgView);
           imgView.setOnMouseClicked(                                       //Se crea una clase implicita
                new EventHandler<MouseEvent>() {                            //A traves de un MouseEvent, quiere decir que al seleccionar una imagen se realizar aquella accion 
                    @Override                   
                    public void handle(MouseEvent arg0) {                   //Con este metodo se extraera toda la informacion de la imagen que se selecciono
                        Text precio = new Text("$"+Integer.toString(h.getPrecio()));  //Precio de habitacion
                        Text titulo = new Text("Habitación tipo:"+h.getCategoria()+" N°"+h.getnHabitacion()); //Categoria y numero de la habitacion
                        Text estadoHabitacion = new Text(h.getEstado());        //Estado de la habitación
                        Text serviciosHabitacion = new Text(h.getServicios());  //Servicio que incluye
                        lblNhab.setText(h.getnHabitacion());                    //Se guarda el numero de la Habitación para utilizarlo en el metodo verificar
                        titulo.setWrappingWidth(70);
                        titulo.setTextAlignment(TextAlignment.JUSTIFY);         //Justifica texto
                        serviciosHabitacion.setWrappingWidth(70);               //Dimensión del texto es de 70
                        serviciosHabitacion.setTextAlignment(TextAlignment.JUSTIFY);//Justifica texto
                        estadoHabitacion.setWrappingWidth(70);
                        estadoHabitacion.setTextAlignment(TextAlignment.CENTER);  //Texto Centrado
                        precio.setWrappingWidth(70);
                        precio.setTextAlignment(TextAlignment.CENTER);            //Texto Centrado
                        
                        hpane2.getChildren().clear();                           //Se limpia el hpane2 para mostrar la informacion de la imagene seleccionada
                        hpane2.getChildren().add(titulo);                       //Titulo: Num y Categoria de la habitacion agregados al al hpane2
                        hpane2.getChildren().add(serviciosHabitacion);  
                        hpane2.getChildren().add(precio);
                        hpane2.getChildren().add(estadoHabitacion);

                        }
                    }
                );
        }  
    }
    @FXML
    public void cerrarVentana(ActionEvent event) { //Metodo que cerrara la ventana 
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
    @FXML
 public void guardarReserva(){      //Se va a guardar las reservas al momento de aplastar la botonera de guardar
     LocalDate hoy=LocalDate.now();
     if(!nombre.getText().equals("")&&!identificacion.getText().equals("")&&!paisOrigen.getText().equals("")&&cbFormaPago.getValue()!=null&&fechaEntrada.getValue()!=null&&fechaSalida.getValue()!=null&&!lblNhab.getText().equals("")){
        Reservas r= new Reservas(nombre.getText(),identificacion.getText(), paisOrigen.getText(), cbFormaPago.getValue(), fechaEntrada.getValue(),fechaSalida.getValue(), lblNhab.getText());
        reservas.add(r);            //Se va verificar que la reserva se haya llenado todos los campos
        escrituraReservas(r);       //Se escribe en el archivo de reservas
        for(Habitacion h: habitaciones){    //Se necesita saber cual es la habitacion 
            if(h.getnHabitacion().equals(lblNhab.getText())){
                if(h.getEstado().equals("Ocupada")||h.getEstado().equals("Reservada")){
                   Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                    alerta1.setTitle("Error de registro");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("La habitacion no se encuentra disponible");
                    alerta1.showAndWait(); 
                    break;
                }
                
                if(r.getIngreso().compareTo(hoy)==0){  //Condicion para saber si esta reservada la habitaciones
                   h.setEstado("Reservada");
                   escrituraHabitaciones(habitaciones); 
                }
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informacion");
                alerta.setHeaderText(null);             
                alerta.setContentText("La habiatacion n° "+h.getnHabitacion()+" ha sido reservada");//Verifica que la habitación fue ingresada  fue reservada con exito
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
