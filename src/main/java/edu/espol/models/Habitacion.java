/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    public static ArrayList<Habitacion> Habitaciones=new ArrayList<>();
    
    public Habitacion(String nHabitacion, int precio, String servicios, String categoria) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.servicios = servicios;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "nHabitacion=" + nHabitacion + ", precio=" + precio + ", servicios=" + servicios + ", categoria=" + categoria + '}';
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
    
        public static void cargarHabitaciones(){
        try{
            List<String> lineas= Files.readAllLines(Paths.get("archivos/Habitaciones.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                try{
                    String[]separado=linea.split(";");
                    Habitacion ha=new Habitacion(separado[0],Integer.parseInt(separado[2]), separado[3], separado[1]);
                    Habitaciones.add(ha);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.err.println("error en linea: "+linea);
                }
            }
        } catch (IOException ex) {
        System.out.println("No leyo el archivo: "+ ex);
    }
    }
}
