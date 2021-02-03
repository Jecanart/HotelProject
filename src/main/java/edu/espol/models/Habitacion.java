/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

import static edu.espol.models.Reservas.reservas;
import static edu.espol.proyectohote.RegistroHabitacionController.escrituraHabitaciones;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Habitacion {
    private String nHabitacion;
    private int precio;
    private String servicios;
    private String categoria;
    private String estado;
    
    public static ArrayList<Habitacion> habitaciones=new ArrayList<>();
    public Habitacion(String nHabitacion, int precio, String servicios, String categoria,String estado) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.servicios = servicios;
        this.categoria = categoria;
        this.estado=estado;
    }
    
    public Habitacion(String nHabitacion, int precio, String servicios, String categoria) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.servicios = servicios;
        this.categoria = categoria;
        this.estado="Disponible";
    }

    @Override
    public String toString() {
        return "Habitacion{" + "nHabitacion=" + nHabitacion + ", precio=" + precio + ", servicios=" + servicios + ", categoria=" + categoria + '}';
    }
    public int getintnHabitacion(){
        int numero=Integer.parseInt(nHabitacion);
        return numero;
    }
    public String getnHabitacion() {
        return nHabitacion;
    }

    public int getPrecio() {
        return precio;
    }

    public String getServicios() {
        return servicios;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
        /**metodo para cargar las habitaciones del archivo*/
        public static void cargarHabitaciones(){
        try{
            List<String> lineas= Files.readAllLines(Paths.get("archivos/Habitaciones.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                try{
                    String[]separado=linea.split(";");
                    Habitacion ha=new Habitacion(separado[0],Integer.parseInt(separado[2]), separado[3], separado[1],separado[4]);
                    habitaciones.add(ha);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.err.println("error en linea: "+linea);
                }
            }
        } catch (IOException ex) {
        System.out.println("No leyo el archivo: "+ ex);
    }
    }
        /**metodo que filtra habitaciones segun su categoria*/
    public static ArrayList <Habitacion> filtrarHabitacion(ArrayList<Habitacion> habitacionesF,String categoria) 
     {
        ArrayList <Habitacion> habitacionesFiltradas = new ArrayList<>();
        for(Habitacion h: habitacionesF){
            if (h.getCategoria().equals(categoria))
                habitacionesFiltradas.add(h);
            else if(categoria.equals("Todas"))
                habitacionesFiltradas.add(h);
        }
        return habitacionesFiltradas;
     }
    /**metodo que verifica las fechas de reserva y actualiza el estado de las habitaciones correspondientes*/
    public static void verificarFechas(){
        LocalDate hoy=LocalDate.now();
        for(Reservas r: reservas){
            if(r.getIngreso().compareTo(hoy)<=0){
                String habitacion = r.getNhabitacion();
                for(Habitacion h: habitaciones){
                    if(h.getnHabitacion().equals(habitacion)&&h.getEstado().equals("Disponible")){
                        h.setEstado("Reservada");
                        escrituraHabitaciones(habitaciones);
                    }
                }
            }
            if(r.getSalida().compareTo(hoy)>0){
                String habitacion = r.getNhabitacion();
                for(Habitacion h: habitaciones){
                    if(h.getnHabitacion().equals(habitacion)&&h.getEstado().equals("Reservada")){
                        h.setEstado("Disponible");
                        escrituraHabitaciones(habitaciones);
                    }
                }
            }
            
        }
    }
     
}
