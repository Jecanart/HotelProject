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
public class Hotel {
    
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;
    public static ArrayList<Hotel> listaHotel=new ArrayList<>();

    public Hotel(String nombre, String ciudad, String direccion, String telefono) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public static void cargarHoteles(){//metodo para cargar los hoteles creados
        try{
            List<String> lineas= Files.readAllLines(Paths.get("archivos/Hoteles.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                try{
                    String[]separado=linea.split(";");
                    Hotel ha=new Hotel(separado[0],separado[1], separado[2], separado[3]);
                    listaHotel.add(ha);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.err.println("error en linea: "+linea);
                }
            }
        } catch (IOException ex) {
        System.out.println("No leyo el archivo: "+ ex);
    }
    }
    
}
